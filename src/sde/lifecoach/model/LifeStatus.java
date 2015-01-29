package sde.lifecoach.model;


import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the "LifeStatus" database table.
 * 
 */

public class LifeStatus implements Serializable {
	private static final long serialVersionUID = 1L;


	
	private int idMeasure;

	private String value;
	
	private MeasureDefinition measureDefinition;
	
	private Person person;
	
	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public LifeStatus() {
	}

	public int getIdMeasure() {
		return this.idMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		this.idMeasure = idMeasure;
	}

	// we make this transient for JAXB to avoid and infinite loop on serialization

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}	

}
