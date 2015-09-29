package com.wot.shared;

import java.io.Serializable;
import java.util.List;

public class DataClan implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5281655363063088636L;
	List<ItemsDataClan> items; //données du clan 

	public List<ItemsDataClan> getItems() {
		return items;
	}

	public void setItems(List<ItemsDataClan> items) {
		this.items = items;
	}

	

}
