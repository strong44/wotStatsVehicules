package com.wot.shared;

import java.io.Serializable;
import java.util.List;

public class DataCommunityAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2305944422816143878L;

	String name ;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	DataCommunityAccountAchievements achievements; //m�dailles du joueur
	
	/**
	 * "stats": {
      "spotted": 6205, 
      "dropped_ctf_points": 3852, 
      "battle_avg_xp": 470, 
      "battles": 5334, 
      "damage_dealt": 2986570, 
      "battle_avg_performance": 52, 
      "integrated_rating": 13, 
      "frags": 4338, 
      "xp": 2505988, 
      "ctf_points": 6895, 
      "battle_wins": 2753
    }, 

	 * @return
	 */

	DataPlayerInfos ratings;
	
	/**
	 * "vehicles": [
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
      {
        "spotted": 0, 
        "localized_name": "KV-1", 
        "name": "KV1", 
        "level": 5, 
        "damageDealt": 0, 
        "survivedBattles": 0, 
        "battle_count": 915, 
        "nation": "ussr", 
        "image_url": "/static/2.7.0/encyclopedia/tankopedia/vehicle/small/ussr-kv1.png", 
        "frags": 0, 
        "win_count": 464, 
        "class": "heavyTank"
      }, 
	 * @return
	 */
	
	List<DataCommunityAccountVehicules> vehicles;
	
	
	public List<DataCommunityAccountVehicules> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<DataCommunityAccountVehicules> vehicles) {
		this.vehicles = vehicles;
	}

	public DataPlayerInfos getStats() {
		return ratings;
	}

	public void setStats(DataPlayerInfos stats) {
		this.ratings = stats;
	}

	public DataCommunityAccountAchievements getAchievements() {
		return achievements;
	}

	public void setAchievements(DataCommunityAccountAchievements achievements) {
		this.achievements = achievements;
	}

}
