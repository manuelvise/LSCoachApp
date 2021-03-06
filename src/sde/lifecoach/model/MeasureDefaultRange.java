package sde.lifecoach.model;


import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the "MeasureDefaultRange" database table.
 * 
 */

public class MeasureDefaultRange implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idRange;
	private String rangeName;
	private String startValue;
	private String alarmLevel;
	private String endValue;
	private MeasureDefinition measureDefinition;

	public MeasureDefaultRange() {
	}

	public String getAlarmLevel() {
		return this.alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getEndValue() {
		return this.endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public int getIdRange() {
		return this.idRange;
	}

	public void setIdRange(int idRange) {
		this.idRange = idRange;
	}

	public String getRangeName() {
		return this.rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public String getStartValue() {
		return this.startValue;
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}

	public MeasureDefinition getMeasureDefinition() {
	    return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition param) {
	    this.measureDefinition = param;
	}
	
}
