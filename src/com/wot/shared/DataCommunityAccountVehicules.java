package com.wot.shared;

import java.io.Serializable;

public class DataCommunityAccountVehicules implements Comparable<DataCommunityAccountVehicules>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4463541285341798056L;
	/**
	 * 
	 */
	
	/**
	 {
        "spotted": 0, 
        "localized_name": "KV-1S", 
        "name": "KV-1s", 
        "level": 6, 
        "damageDealt": 0, 
        "survivedBattles": 0, 
        "battle_count": 1400, 
        "nation": "ussr", 
        "image_url": "/static/2.7.0/encyclopedia/tankopedia/vehicle/small/ussr-kv-1s.png", 
        "frags": 0, 
        "win_count": 773, 
        "class": "heavyTank"
      }, 
    }, */
	
	  //private Integer spotted ;
	  private String localized_name;
	  private String name;
	  private Integer level;
	  //private Integer damageDealt;
	  //private Integer survivedBattles;
	  private Integer battle_count;
	  private String nation;
	  private String image_url;
	  //private String frags ;
	  private Integer win_count;
	  //private String class;
	  
	  private Integer countBattleSincePreviousDay;

	private int winCountBattleSincePreviousDay;
	  
	public int getWinCountBattleSincePreviousDay() {
		return winCountBattleSincePreviousDay;
	}
	public Integer getCountBattleSincePreviousDay() {
		return countBattleSincePreviousDay;
	}
	public void setCountBattleSincePreviousDay(Integer countBattleSincePreviousDay) {
		this.countBattleSincePreviousDay = countBattleSincePreviousDay;
	}
	public String getLocalized_name() {
		return localized_name;
	}
	public void setLocalized_name(String localized_name) {
		this.localized_name = localized_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getBattle_count() {
		return battle_count;
	}
	public void setBattle_count(Integer battle_count) {
		this.battle_count = battle_count;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Integer getWin_count() {
		return win_count;
	}
	public void setWin_count(Integer win_count) {
		this.win_count = win_count;
	}
	@Override
	public int compareTo(DataCommunityAccountVehicules veh2) {
		// TODO Auto-generated method stub
		if (veh2 != null && veh2.getCountBattleSincePreviousDay() != null && this.getCountBattleSincePreviousDay()!= null) {
			if (this.getCountBattleSincePreviousDay() < veh2.getCountBattleSincePreviousDay())
				return 1;
			else
				return 0;
		}else
			return 0;
	}
	public void setWinCountBattleSincePreviousDay(int i) {
		this.winCountBattleSincePreviousDay = i;
		
	}
	
	
}
