package com.wot.server;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoDataCommunityAccountStatsVehicules implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2957925798230220934L;


	/**
	 * 
	 */
	


	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;


	/**
	 * {
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
      },  */
	
	
	@Persistent
	  private int battle_count;
	
	@Persistent
	  private Integer  win_count;

	@Persistent
	private int mark_of_mastery;

	
	//======== getter setter ===============
	public Integer getWin_count() {
		return win_count;
	}

	public void setWin_count(Integer win_count) {
		this.win_count = win_count;
	}


	public int getBattle_count() {
		return battle_count;
	}

	public void setBattle_count(int battle_count) {
		this.battle_count = battle_count;
	}


	public int getMark_of_mastery() {
		return mark_of_mastery;
	}

	public void setMark_of_mastery(int mark_of_mastery) {
		this.mark_of_mastery = mark_of_mastery;
	}
	

	
}
