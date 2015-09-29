package com.wot.shared;

import java.io.Serializable;

public class DataPlayerTankRatings implements Serializable{

	/**
	 * {
		"achievements":{ 
		"medal_dumitru":0,"invader":0,"medal_lehvaslaiho":0,"warrior":0,"medal_halonen":0,"medal_pascucci":0,"medal_orlik":0,"medal_brothers_in_arms":0,"mousebane":0,"medal_bruno_pietro":0,"medal_delanglade":0,"lucky_devil":0,"defender":0,"armor_piercer":0,"medal_kay":0,"supporter":0,"steelwall":0,"max_sniper_series":0,"medal_knispel":0,"medal_boelter":0,"medal_ekins":0,"medal_heroes_of_rassenay":0,"medal_tamada_yoshio":0,"max_piercing_series":0,"medal_radley_walters":0,"kamikaze":0,"sinai":0,"sniper":0,"medal_tarczay":0,"scout":0,"medal_oskin":0,"medal_burda":0,"medal_billotte":0,"huntsman":0,"hand_of_death":0,"medal_fadin":0,"medal_lafayette_pool":0,"max_killing_series":0,"medal_lavrinenko":0,"medal_kolobanov":0,"patton_valley":0,"bombardier":0,"medal_abrams":0,"max_invincible_series":0,"medal_poppel":0,"medal_crucial_contribution":0,"raider":0,"max_diehard_series":0,"invincible":0,"lumberjack":0,"sturdy":0,"title_sniper":0,"iron_man":0,"diehard":0,"medal_carius":0,"medal_le_clerc":0,"beasthunter":0,"evileye":0,"medal_nikolas":0
		} 
		,"statistics":{ 
		"clan"
		:{ 
		"spotted":0,"hits":0,"battle_avg_xp":0,"draws":0,"wins":0,"losses":0,"capture_points":0,"battles":0,"damage_dealt":0,"hits_percents":0,"damage_received":0,"shots":0,"xp":0,"frags":0,"survived_battles":0,"dropped_capture_points":0
		} 
		,"all"
		:{ 
		"spotted":0,"hits":0,"battle_avg_xp":0,"draws":0,"wins":816,"losses":0,"capture_points":0,"battles":1482,"damage_dealt":0,"hits_percents":0,"damage_received":0,"shots":0,"xp":0,"frags":0,"survived_battles":0,"dropped_capture_points":0
		} 
		,"max_xp":0,"wins":816,"company"
		:{ 
		"spotted":0,"hits":0,"battle_avg_xp":0,"draws":0,"wins":0,"losses":0,"capture_points":0,"battles":0,"damage_dealt":0,"hits_percents":0,"damage_received":0,"shots":0,"xp":0,"frags":0,"survived_battles":0,"dropped_capture_points":0
		} 
		,"battles":1482,"max_frags":0,"win_and_survived":0
		} 
		,"last_battle_time":0,"mark_of_mastery":4,"in_garage":0,"tank_id":2817
		} 
		
		nouveau :
		
		nouveau 

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
	
	DataPlayerTankRatingsStatistics statistics ; //{"wins":567,"battles":1123},"mark_of_mastery":4,"tank_id":2849}
	
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

	int tank_id;
}
