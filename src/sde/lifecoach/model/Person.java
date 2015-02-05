package sde.lifecoach.model;



import java.io.Serializable;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Person" database table.
 * 
 */
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888564417005646353L;

	private Long idPerson;

	private String lastname;

	private String name;

	private String username;
	
	private String birthdate;
	
	private String email;

	private List<LifeStatus> lifeStatus;
	
	public Person() {
	}
	
	public String getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<LifeStatus> getLifeStatus() {
	    return lifeStatus;
	}

	public void setLifeStatus(List<LifeStatus> param) {
	    this.lifeStatus = param;
	}

}
