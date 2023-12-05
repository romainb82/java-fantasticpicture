package service;

public interface IFileEdi {
	public void writeNote(String texte, String currentImage, String user);
	
	public String readNote(String person, String picture);
}
