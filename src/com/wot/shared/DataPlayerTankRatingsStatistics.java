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

	
	////////////////////////////
	public int getWins() {
		return wins;
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

	
	
	
	
	
}
