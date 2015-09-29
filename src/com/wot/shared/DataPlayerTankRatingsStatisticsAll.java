package com.wot.shared;

import java.io.Serializable;

public class DataPlayerTankRatingsStatisticsAll implements Serializable{

	/**
	 * "all"
		:{ 
		"spotted":0,"hits":0,"battle_avg_xp":0,"draws":0,"wins":816,"losses":0,"capture_points":0,"battles":1482,"damage_dealt":0,
		"hits_percents":0,"damage_received":0,"shots":0,"xp":0,"frags":0,"survived_battles":0,"dropped_capture_points":0
		} 
	 */
	private static final long serialVersionUID = 4192068084907090756L;

	int wins ;
	int battles;
	
	////////////
	Double wrCalc;

	public int getWins() {
		return wins;
	}

	public int getBattles() {
		return battles;
	}

	public Double getWr() {
		return wrCalc;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setBattles(int battles) {
		this.battles = battles;
	}

	public void setWr(Double wr) {
		this.wrCalc = wr;
	}
	
	
}
