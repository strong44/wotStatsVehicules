package com.wot.shared;

import java.io.Serializable;
import java.util.List;

public class DataStatPlayerVehicles implements Serializable {


	private static final long serialVersionUID = 3802008736901937953L;
	
	
	//stat
		//achievements
		//...
		//ratings
		// ...
		//statistics
			//clan
				// ...
			//all
				// ...
			//company
				//...
			//max_xp
	
		//account_id
		//
		//vehicles
			//{"wins":7,"mark_of_mastery":0,"battles":21,"tank_id":769}
			
	
	String account_id ; 
	



	List<DataStatVehicle> vehicles;


	public String getAccount_id() {
		return account_id;
	}


	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	public List<DataStatVehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(List<DataStatVehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}
