package com.wot.shared;

import java.io.Serializable;

public class DataPlayerVehicles implements Serializable {

	private static final long serialVersionUID = -2971688685548082247L;

	Integer date;
	Integer hours_ago;
	DataStatPlayerVehicles stat;
	
	
	public Integer getDate() {
		return date;
	}
	public Integer getHours_ago() {
		return hours_ago;
	}
	public DataStatPlayerVehicles getStat() {
		return stat;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public void setHours_ago(Integer hours_ago) {
		this.hours_ago = hours_ago;
	}
	public void setStat(DataStatPlayerVehicles stat) {
		this.stat = stat;
	}
	
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
			
	
	
}
