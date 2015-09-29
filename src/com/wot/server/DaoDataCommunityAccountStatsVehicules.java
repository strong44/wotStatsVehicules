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
	  private String localized_name;
	
	@Persistent
	  private String  name;
	
	@Persistent
	  private int level;
	
	@Persistent
	  private int battle_count;
	
	@Persistent
	  private String  nation;
	
	@Persistent
	  private String  image_url;
	

	@Persistent
	  private Integer  win_count;

	public Integer getWin_count() {
		return win_count;
	}

	public void setWin_count(Integer win_count) {
		this.win_count = win_count;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBattle_count() {
		return battle_count;
	}

	public void setBattle_count(int battle_count) {
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
	

	
}
