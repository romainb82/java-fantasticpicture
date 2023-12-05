package entite.impl;

import entite.IImageBean;
import entite.INoteBean;
import entite.IPersonBean;

public class NoteBean implements INoteBean{
	private String idNote;
	private IPersonBean idPerson;
	private IImageBean idImage;
	private int note;
	
	public NoteBean(String idNote, IPersonBean idPerson, IImageBean idImage, int note) {
		this.idNote = idNote;
		this.idPerson = idPerson;
		this.idImage = idImage;
		this.note = note;
	}
	
	@Override
	public String getIdNote() {
		return idNote;
	}
	
	@Override
	public void setIdNote(String idNote) {
		this.idNote = idNote;
	}
	
	@Override
	public IPersonBean getIdPerson() {
		return idPerson;
	}
	
	@Override
	public void setIdPerson(IPersonBean idPerson) {
		this.idPerson = idPerson;
	}
	
	@Override
	public IImageBean getIdImage() {
		return idImage;
	}
	
	@Override
	public void setIdImage(IImageBean idImage) {
		this.idImage = idImage;
	}
	
	@Override
	public int getNote() {
		return note;
	}
	
	@Override
	public void setNote(int note) {
		this.note = note;
	}
}
