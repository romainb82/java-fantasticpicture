package entite.impl;

import entite.IImageBean;

public class ImageBean implements IImageBean {
	private String idImage;
	private String nom;
	private String url;
	
	public ImageBean(String idImage, String nom, String url) {
		this.idImage = idImage;
		this.nom = nom;
		this.url = url;
	}
	
	@Override
	public String getIdImage() {
		return idImage;
	}

	@Override
	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
		
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
