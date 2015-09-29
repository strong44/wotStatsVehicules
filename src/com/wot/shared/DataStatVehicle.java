package com.wot.shared;

import java.io.Serializable;

public class DataStatVehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 191089905090275504L;


	//{"wins":7,"mark_of_mastery":0,"battles":21,"tank_id":769}
	Integer wins;
	Integer mark_of_mastery;
	Integer battles;
	Integer tank_id;
	String tank_name ;
	
	public Integer getWins() {
		return wins;
	}
	public Integer getMark_of_mastery() {
		return mark_of_mastery;
	}
	public Integer getBattles() {
		return battles;
	}
	public Integer getTank_id() {
		return tank_id;
	}
	public String getTank_name() {
		return tank_name;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public void setMark_of_mastery(Integer mark_of_mastery) {
		this.mark_of_mastery = mark_of_mastery;
	}
	public void setBattles(Integer battles) {
		this.battles = battles;
	}
	public void setTank_id(Integer tank_id) {
		this.tank_id = tank_id;
	}
	public void setTank_name(String tank_name) {
		this.tank_name = tank_name;
	}
}
