package com.wot.server;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoDataClan implements Serializable {
	

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	private static final long serialVersionUID = -3833805721188019694L;
	
	@Persistent
	List<DaoItemsDataClan> items; //données du clan 

	public List<DaoItemsDataClan> getItems() {
		return items;
	}

	public void setItems(List<DaoItemsDataClan> items) {
		this.items = items;
	}

	

}
