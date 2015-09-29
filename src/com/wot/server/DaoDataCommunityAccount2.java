package com.wot.server;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoDataCommunityAccount2 implements Serializable {
	
	private static final long serialVersionUID = -2305944422816143878L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	@Persistent
	String name ;
	
	@Persistent
	DaoDataCommunityAccountRatings2 stats;

	//@Persistent
	DaoDataCommunityAccountAchievements achievements; //m�dailles du joueur
	
	//@Persistent
	List<DaoDataCommunityAccountStatsVehicules> statsVehicules;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<DaoDataCommunityAccountStatsVehicules> getStatsVehicules() {
		return statsVehicules;
	}

	public void setStatsVehicules(
			List<DaoDataCommunityAccountStatsVehicules> statsVehicules) {
		this.statsVehicules = statsVehicules;
	}

	public DaoDataCommunityAccountRatings2 getStats() {
		return stats;
	}

	public void setStats(DaoDataCommunityAccountRatings2 daoDataCommunityAccountRatings) {
		this.stats = daoDataCommunityAccountRatings;
	}

	public DaoDataCommunityAccountAchievements getAchievements() {
		return achievements;
	}

	public void setAchievements(DaoDataCommunityAccountAchievements achievements) {
		this.achievements = achievements;
	}

}
