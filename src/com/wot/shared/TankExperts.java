package com.wot.shared;

import java.io.Serializable;

public class TankExperts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9097773272110572878L;
	
	private String usa ;
	private String france ;
	private String ussr ;
	private String china ;
	private String uk ;
	private String germany ;
	public String getUsa() {
		return usa;
	}
	public void setUsa(String usa) {
		this.usa = usa;
	}
	public String getFrance() {
		return france;
	}
	public void setFrance(String france) {
		this.france = france;
	}
	public String getUssr() {
		return ussr;
	}
	public void setUssr(String ussr) {
		this.ussr = ussr;
	}
	public String getChina() {
		return china;
	}
	public void setChina(String china) {
		this.china = china;
	}
	public String getUk() {
		return uk;
	}
	public void setUk(String uk) {
		this.uk = uk;
	}
	public String getGermany() {
		return germany;
	}
	public void setGermany(String germany) {
		this.germany = germany;
	}

}
