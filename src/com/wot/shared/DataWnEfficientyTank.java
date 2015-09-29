package com.wot.shared;

import java.io.Serializable;

public class DataWnEfficientyTank implements Serializable {


	/**url : http://www.wnefficiency.net/exp/expected_tank_values_latest.json
	 * {"header":{"version":14},
	 * "data":
	 * [{"IDNum":"3089","expFrag":"2.11","expDamage":"278.00","expSpot":"2.35","expDef":"1.84","expWinRate":"59.54"},
	 * {"IDNum":"3329","expFrag":"2.10","expDamage":"270.00","expSpot":"1.55","expDef":"1.81","expWinRate":"60.46"},
	 */
	private static final long serialVersionUID = -3518633389379538794L;
	/**
	 * 
	 */
	

	/**
	 * 
	 */
	private String 	IDNum; //id tank 
	private String 	expFrag ; //expected frag 
	private String 	expDamage; //expected dlg
	private String 	expSpot; //expected spot
	private String  expDef; //expected spot
	private String 	expWinRate; //expected wr
	
	public String getIDNum() {
		return IDNum;
	}
	public void setIDNum(String iDNum) {
		IDNum = iDNum;
	}
	public String getExpFrag() {
		return expFrag;
	}
	public void setExpFrag(String expFrag) {
		this.expFrag = expFrag;
	}
	public String getExpDamage() {
		return expDamage;
	}
	public void setExpDamage(String expDamage) {
		this.expDamage = expDamage;
	}
	public String getExpSpot() {
		return expSpot;
	}
	public void setExpSpot(String expSpot) {
		this.expSpot = expSpot;
	}
	public String getExpDef() {
		return expDef;
	}
	public void setExpDef(String expDef) {
		this.expDef = expDef;
	}
	public String getExpWinRate() {
		return expWinRate;
	}
	public void setExpWinRate(String expWinRate) {
		this.expWinRate = expWinRate;
	}
	
	
	
	
	

	
	
}
