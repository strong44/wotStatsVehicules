package com.wot.shared;

import java.io.Serializable;

public class DataPlayerTankRatingsStatistics implements Serializable{

	/**
	 * "statistics":{"wins":567,"battles":1123},"mark_of_mastery":4,"tank_id":2849}
	 */
	private static final long serialVersionUID = 6100888880210621451L;

	// wins with this tank
	int wins ;
	int battles;

	int	mark_of_mastery;

	private int tank_id;

	private String tankName;
	
	////////////////////////////
	
	
	public int getWins() {
		return wins;
	}

	public int getTank_id() {
		return tank_id;
	}
	public void setTank_id(int tank_id) {
		this.tank_id = tank_id;
	}
	public int getMark_of_mastery() {
		return mark_of_mastery;
	}
	public void setMark_of_mastery(int mark_of_mastery) {
		this.mark_of_mastery = mark_of_mastery;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getBattles() {
		return battles;
	}
	public void setBattles(int battles) {
		this.battles = battles;
	}
	public void setTankName(String name) {
		this.tankName = name;
		
	}
	public String getTankName() {
		return tankName;
	}

	
	
	
	
	
}
