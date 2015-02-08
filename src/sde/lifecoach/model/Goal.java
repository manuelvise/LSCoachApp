package sde.lifecoach.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Goal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5691839847646011672L;

	private int idGoal;
	
	private MeasureDefinition measureDefinition;

	private String value;

	private Long deadline;

	private Person person;

	public int getIdGoal() {
		return idGoal;
	}

	public void setIdGoal(int idGoal) {
		this.idGoal = idGoal;
	}

	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getDeadline() {
		return deadline;
	}

	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
}
