package com.wot.server;

import java.io.Serializable;
import java.util.Map;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoDataCommunityClan implements Serializable {

	private static final long serialVersionUID = -4458053189613899172L;
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
	Map<String, DaoDataCommunityClanMembers> members; //données du joueur

	public Map<String, DaoDataCommunityClanMembers> getMembers() {
		return members;
	}

	public void setMembers(Map<String, DaoDataCommunityClanMembers> members) {
		this.members = members;
	}

}
