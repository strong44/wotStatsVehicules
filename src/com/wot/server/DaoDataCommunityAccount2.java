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
	List<DaoDataCommunityAccountStatsVehicules> statsVehicules;
	
	
	public List<DaoDataCommunityAccountStatsVehicules> getStatsVehicules() {
		return statsVehicules;
	}

	public void setStatsVehicules(
			List<DaoDataCommunityAccountStatsVehicules> statsVehicules) {
		this.statsVehicules = statsVehicules;
	}

}
