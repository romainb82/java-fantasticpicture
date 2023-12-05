package service.impl;

import java.util.HashMap;
import java.util.Map;

import entite.IPersonBean;
import entite.IImageBean;
import entite.impl.ImageBean;
import entite.impl.PersonBean;

import service.IPictureService;
import service.IFileEdi;

public class PictureService implements IPictureService{

	@Override
	public HashMap<String, IPersonBean> getPersonBean() {
		HashMap<String, IPersonBean> personBeanArray = new HashMap<String, IPersonBean>();
		
		IPersonBean bob = new PersonBean("1", "bob", "bob");
		IPersonBean romain = new PersonBean("2", "romain", "romain");
		IPersonBean clement = new PersonBean("3", "clement", "clement");

		personBeanArray.put("bob", bob);
		personBeanArray.put("romain", romain);
		personBeanArray.put("clement", clement);
		
		return personBeanArray;
	}

	@Override
	public HashMap<String, IImageBean> getImageBean() {
		HashMap<String, IImageBean> imageBeanArray = new HashMap<String, IImageBean>();
		
		IImageBean paris = new ImageBean("1", "paris", "/assets/paris.jpeg");
		IImageBean bretagne = new ImageBean("2", "bretagne", "/assets/bretagne.jpeg");
		IImageBean cellule = new ImageBean("3", "cellule", "/assets/cellule.jpeg");
		IImageBean espace = new ImageBean("4", "paris", "/assets/espace.jpeg");
		IImageBean gaudi = new ImageBean("5", "paris", "/assets/gaudi.jpeg");
		
		imageBeanArray.put("paris", paris);
		imageBeanArray.put("bretagne", bretagne);
		imageBeanArray.put("cellule", cellule);
		imageBeanArray.put("espace", espace);
		imageBeanArray.put("gaudi", gaudi);

		return imageBeanArray;
	}

	@Override
	public void setNote(String user, String image, int note) {
		IFileEdi fileEdi = new FileEdi();
		String texte = user + ";" + image + ";" + note;
		
		fileEdi.writeNote(texte, image, user);
	}
	
	@Override
	public String readNoteAtConnection(String user, String image) {
		IFileEdi fileEdi = new FileEdi();
		
		return fileEdi.readNote(user, image);
	}

	@Override
	public Boolean checkUser(String user, String mdp) {
		Boolean testUser = false;
		HashMap<String, IPersonBean> personArray = this.getPersonBean();

		for (Map.Entry person : personArray.entrySet()) {
			String userMap = ((IPersonBean) person.getValue()).getLogin();
			String passwordMap = ((IPersonBean) person.getValue()).getPassword();
			
			if(user.equals(userMap) & mdp.equals(passwordMap)) {
				testUser = true;
			}
			
		}
		return testUser;
	}

}
