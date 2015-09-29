package com.wot.server;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoCommunityAccount2 implements Serializable{

	private static final long serialVersionUID = -5612196058981975635L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	

	
	@Persistent
	String name ;

	
	@Persistent
	private String idUser;
	
	@Persistent
	private Date dateCommunityAccount;
	
	@Persistent
	private DaoDataCommunityAccount2 data;
	
	
	
	///////////////////////////////
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Date getDateCommunityAccount() {
		return dateCommunityAccount;
	}

	public void setDateCommunityAccount(Date dateCommunityAccount) {
		this.dateCommunityAccount = dateCommunityAccount;
	}

    public DaoDataCommunityAccount2 getData() {
		return data;
	}

	public void setData(DaoDataCommunityAccount2 data) {
		this.data = data;
	}

	
}

