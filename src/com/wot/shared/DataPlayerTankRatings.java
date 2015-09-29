package com.wot.shared;

import java.io.Serializable;

public class DataPlayerTankRatings implements Serializable{

	/**
	 * 
{"status":"ok","count":36,"data":{"503294210":
[{"statistics":{"wins":567,"battles":1123},"mark_of_mastery":4,"tank_id":2849},
{"statistics":{"wins":279,"battles":540},"mark_of_mastery":4,"tank_id":3649},
{"statistics":{"wins":290,"battles":533},"mark_of_mastery":4,"tank_id":5137},
{"statistics":{"wins":259,"battles":508},"mark_of_mastery":4,"tank_id":5697},
{"statistics":{"wins":238,"battles":485},"mark_of_mastery":4,"tank_id":14865},
{"statistics":{"wins":219,"battles":439},"mark_of_mastery":3,"tank_id":4929},
{"statistics":{"wins":239,"battles":433},"mark_of_mastery":4,"tank_id":9745},


	 */
	private static final long serialVersionUID = 7479748986919619834L;

	//achievements
	
	DataPlayerTankRatingsStatistics statistics ; //{"wins":567,"battles":1123},
	int tank_id;
	int mark_of_mastery ;
	
	public int getMark_of_mastery() {
		return mark_of_mastery;
	}

	public void setMark_of_mastery(int mark_of_mastery) {
		this.mark_of_mastery = mark_of_mastery;
	}

	public DataPlayerTankRatingsStatistics getStatistics() {
		return statistics;
	}

	public int getTank_id() {
		return tank_id;
	}

	public void setStatistics(DataPlayerTankRatingsStatistics statistics) {
		this.statistics = statistics;
	}

	public void setTank_id(int tank_id) {
		this.tank_id = tank_id;
	}

	
}
