package com.wot.shared;

import java.io.Serializable;

public class DataTankEncyclopedia implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6403382723599823213L;

	/**
	 * 
	 */
	private String 	nation_i18n;
	private String 	name ;
	private int 	level;
	private String 	nation;
	private Boolean is_premium;
	private String 	name_i18n;
	private String 	type;
	private int 	tank_id;
	
	
	public String getNation_i18n() {
		return nation_i18n;
	}
	public String getName() {
		return name;
	}
	public int getLevel() {
		return level;
	}
	public String getNation() {
		return nation;
	}
	public Boolean getIs_premium() {
		return is_premium;
	}
	public String getName_i18n() {
		return name_i18n;
	}
	public String getType() {
		return type;
	}
	public int getTank_id() {
		return tank_id;
	}
	public void setNation_i18n(String nation_i18n) {
		this.nation_i18n = nation_i18n;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public void setIs_premium(Boolean is_premium) {
		this.is_premium = is_premium;
	}
	public void setName_i18n(String name_i18n) {
		this.name_i18n = name_i18n;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTank_id(int tank_id) {
		this.tank_id = tank_id;
	}
	
	

	
	
}
