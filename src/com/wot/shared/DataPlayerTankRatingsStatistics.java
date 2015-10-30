package com.wot.shared;

import java.io.Serializable;

public class DataPlayerTankRatingsStatistics implements Serializable, Comparable<DataPlayerTankRatingsStatistics>{

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

	private int countBattleSincePreviousDay;

	private int winCountBattleSincePreviousDay;

	private String nation;

	private String type;

	private int level;
	
	////////////////////////////
	
	
	public int getWins() {
		return wins;
	}

	public int getLevel() {
		return level;
	}

	public String getType() {
		return type;
	}

	public String getNation() {
		return nation;
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

	public void setCountBattleSincePreviousDay(int battle ) {
		countBattleSincePreviousDay = battle; 
	}

	public int getCountBattleSincePreviousDay() {
		return countBattleSincePreviousDay;
	}

	public void setWinCountBattleSincePreviousDay(int winBattle) {
		winCountBattleSincePreviousDay = winBattle ;
		
	}

	public int getWinCountBattleSincePreviousDay() {
		return winCountBattleSincePreviousDay;
	}

	

	@Override
	public int compareTo(DataPlayerTankRatingsStatistics o) {
			if (o == null || o.getTank_id() == 0)
				return -1 ;
			else
				return Integer.valueOf(o.getCountBattleSincePreviousDay()).compareTo(Integer.valueOf(getCountBattleSincePreviousDay()));
		
	}

	@Override
    public boolean equals(Object o) {
      if (o instanceof DataPlayerTankRatingsStatistics) {
        return Integer.valueOf(getTank_id()).equals(Integer.valueOf(((DataPlayerTankRatingsStatistics) o).getTank_id()));
      }
      return false;
    }

	public void setNation(String nation_i18n) {
		nation = nation_i18n;
		
	}

	public void setType(String type) {
		this.type = type;
		
	}

	public void setLevel(int level) {
		this.level = level;
		
	}

	
	
	
}
