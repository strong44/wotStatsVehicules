package com.wot.shared;

import java.io.Serializable;

public class DataCommunityAccountRatingsElement implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 66785523555079298L;
	
	 private String place;
	
	
	 private Double value;
	 
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
