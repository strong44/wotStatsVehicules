package com.wot.shared.dossiertojson;

import java.io.Serializable;

public class TankToJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6377301940120234997L;
	
	
	
	/**
	 * 			{"tankid": 0, "countryid": 3, "compDescr": 49, "active": 1, "type": 2, "type_name": "MT", "tier": 8, "premium": 1, "title": "Type 59", "icon": "ch01_type59", "icon_orig": "Ch01_Type59"},
				{"tankid": 1, "countryid": 3, "compDescr": 305, "active": 1, "type": 1, "type_name": "LT", "tier": 7, "premium": 1, "title": "Type 62", "icon": "ch02_type62", "icon_orig": "Ch02_Type62"},

	 */
	
	private int tankid ; 
	private int countryid ; 
	private int compDescr ; 
	private int active ; 
	private int type ; 
	private String type_name; 
	private int tier ; 
	private int premium ; 
	private String title ; 
	private String icon; 
	private String icon_orig;
	
	public int getTankid() {
		return tankid;
	}
	public int getCountryid() {
		return countryid;
	}
	public int getCompDescr() {
		return compDescr;
	}
	public int getActive() {
		return active;
	}
	public int getType() {
		return type;
	}
	public String getType_name() {
		return type_name;
	}
	public int getTier() {
		return tier;
	}
	public int getPremium() {
		return premium;
	}
	public String getTitle() {
		return title;
	}
	public String getIcon() {
		return icon;
	}
	public String getIcon_orig() {
		return icon_orig;
	}
	public void setTankid(int tankid) {
		this.tankid = tankid;
	}
	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}
	public void setCompDescr(int compDescr) {
		this.compDescr = compDescr;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public void setTier(int tier) {
		this.tier = tier;
	}
	public void setPremium(int premium) {
		this.premium = premium;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setIcon_orig(String icon_orig) {
		this.icon_orig = icon_orig;
	}

}
