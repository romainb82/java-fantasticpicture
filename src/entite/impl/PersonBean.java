package entite.impl;

import entite.IPersonBean;

public class PersonBean implements IPersonBean{
	private String idPerson;
	private String login;
	private String password;
	
	public PersonBean(String idPerson, String login, String password) {
		this.idPerson = idPerson;
		this.login = login;
		this.password = password;
	}
	
	@Override
	public String getIdPerson() {
		return idPerson;
	}
	
	@Override
	public void setIdPerson(String idPerson) {
		this.idPerson = idPerson;
	}
	
	@Override
	public String getLogin() {
		return login;
	}
	
	@Override
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	

}
