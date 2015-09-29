package com.wot.server;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoClan implements Serializable{

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	private static final long serialVersionUID = 1957641052913871761L;
	private String status;
	private String status_code;


	
//	@Persistent
//	private DaoDataClan daoDataClan;
//	
//    public DaoDataClan getData() {
//		return daoDataClan;
//	}
//
//	public void setData(DaoDataClan data) {
//		this.daoDataClan = data;
//	}

	@Persistent
	List<DaoItemsDataClan> data; //données du clan 

	public List<DaoItemsDataClan> getItems() {
		return data;
	}

	public void setItems(List<DaoItemsDataClan> list) {
		this.data = list;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	
	
	
	
	
	
	
	
	
}

