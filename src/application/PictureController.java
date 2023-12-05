package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import entite.IImageBean;
import entite.IPersonBean;
import exception.NoteException;
import exception.UserException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import service.IPictureService;
import service.impl.PictureService;

public class PictureController implements Initializable {

	@FXML
	private ListView<String> cityChoice;

	String[] city;

	String currentCity;

	@FXML
	private Label errNote;

	@FXML
	private ImageView imagePicked;

	@FXML
	private TextField login;

	@FXML
	private TextField noteImage;

	@FXML
	private TextField password;

	@FXML
	private Button validateLogin;

	@FXML
	private Button validateNote;

	private IPictureService pictureService = new PictureService();
	private HashMap<String, IImageBean> imageArray = pictureService.getImageBean();
	private HashMap<String, IPersonBean> personArray = pictureService.getPersonBean();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		noteImage.setDisable(true);
		validateNote.setDisable(true);
		
		for (String keyImage : imageArray.keySet()) {
			cityChoice.getItems().addAll(keyImage);
		}

		cityChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				currentCity = cityChoice.getSelectionModel().getSelectedItem();

				if (imageArray.containsKey(currentCity)) {
					IImageBean selectedImageBean = imageArray.get(currentCity);
					String imagePath = selectedImageBean.getUrl();
					Image image = new Image(imagePath);
					imagePicked.setImage(image);
					
					String currentImage = cityChoice.getSelectionModel().selectedItemProperty().getValue();
					String user = login.getText();

					checkNote(user, currentImage);
				}

			}
		});
	}

	@FXML
	private void onClickValidateNote(MouseEvent e) throws IOException {
		try {
			errNote.setText("");

			int note = Integer.parseInt(noteImage.getText());
			checkException(note, "note");
			
			String currentImage = cityChoice.getSelectionModel().selectedItemProperty().getValue();
			String user = login.getText();
			
			pictureService.setNote(user, currentImage, note);

			login.setText("");
			password.setText("");
			noteImage.setText("");
			noteImage.setDisable(true);
			validateNote.setDisable(true);
			
		} catch (NumberFormatException err) {
			showException(err);
		} catch (IllegalArgumentException err) {
			showException(err);
		}
	}

	@FXML
	private void onClickValidateUser(MouseEvent e) {
		errNote.setText("");

		String currentImage = cityChoice.getSelectionModel().selectedItemProperty().getValue();
		
		if(currentImage != "") {
			String user = login.getText();
			String mdp = password.getText();
			
			if(pictureService.checkUser(user, mdp)) {
				noteImage.setDisable(false);
				validateNote.setDisable(false);
				checkNote(user, currentImage);
			}
		} else {
			validateLogin.setDisable(false);
		}
		
	}

	public void checkException(Object saisie, String champs) {
		try {
			if (champs == "note") {
				Integer saisieNote = (Integer) saisie;
				if (saisieNote > 20) {
					throw new NoteException("La " + champs + " ne peut pas être supérieur à 20");
				}
				if (saisieNote < 0) {
					throw new NoteException("La " + champs + " ne peut pas être négative");
				}
			}
		} catch (NoteException err) {
			showException(err);
			throw new IllegalArgumentException("Champs Note incorrect");
		}
	}

	public void showException(Exception err) {
		if (err instanceof NoteException) {
			errNote.setText(err.getMessage());
		} else if (err instanceof UserException) {
			errNote.setText(err.getMessage());
		} else if (err instanceof IOException) {
			errNote.setText(err.getMessage());
		} else {
			errNote.setText("Seule les chiffres sont autorisés");
		}
	}
	
	public void checkNote(String user, String currentImage) {
		String checkNote = pictureService.readNoteAtConnection(user, currentImage);

		if (!checkNote.equals("nul")) {
            noteImage.setText(checkNote);
        } else {
            noteImage.setText("");
        }
	}
}
