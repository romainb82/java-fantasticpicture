package service;

import java.util.HashMap;

import entite.IImageBean;
import entite.IPersonBean;

public interface IPictureService {
	public HashMap<String, IPersonBean> getPersonBean();
	
	public HashMap<String, IImageBean> getImageBean();

	public void setNote(String user, String image, int note);
	
	public String readNoteAtConnection(String user, String image);

	public Boolean checkUser(String user, String mdp);
}
