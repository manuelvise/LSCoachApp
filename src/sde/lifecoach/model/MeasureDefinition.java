package sde.lifecoach.model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the "MeasureDefinition" database table.
 * 
 */

public class MeasureDefinition implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idMeasureDef;

	private String measureName;

	private String measureType;

	private List<MeasureDefaultRange> measureDefaultRange;

	public MeasureDefinition() {
	}

	public int getIdMeasureDef() {
		return this.idMeasureDef;
	}

	public void setIdMeasureDef(int idMeasureDef) {
		this.idMeasureDef = idMeasureDef;
	}

	public String getMeasureName() {
		return this.measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getMeasureType() {
		return this.measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public List<MeasureDefaultRange> getMeasureDefaultRange() {
	    return measureDefaultRange;
	}

	public void setMeasureDefaultRange(List<MeasureDefaultRange> param) {
	    this.measureDefaultRange = param;
	}

}
