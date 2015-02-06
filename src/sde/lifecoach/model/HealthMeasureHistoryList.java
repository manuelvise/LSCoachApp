package sde.lifecoach.model;

import java.io.Serializable;
import java.util.List;

public class HealthMeasureHistoryList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8071340177383169843L;
	
	private List<HealthMeasureHistory> history;

	public List<HealthMeasureHistory> getHistory() {
		return history;
	}

	public void setHistory(List<HealthMeasureHistory> history) {
		this.history = history;
	}
	
	
}
