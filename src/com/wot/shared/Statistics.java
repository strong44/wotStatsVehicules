package com.wot.shared;

import java.io.Serializable;

public class Statistics implements Serializable {

	/**
	 * "statistics": {
                "clan": {
                    "spotted": 0,
                    "hits": 11,
                    "battle_avg_xp": 184,
                    "draws": 0,
                    "wins": 0,
                    "losses": 2,
                    "capture_points": 0,
                    "battles": 2,
                    "damage_dealt": 2213,
                    "hits_percents": 100,
                    "damage_received": 2500,
                    "shots": 11,
                    "xp": 367,
                    "frags": 1,
                    "survived_battles": 0,
                    "dropped_capture_points": 0
                },
                "all": {
                    "spotted": 11211,
                    "hits": 47611,
                    "battle_avg_xp": 450,
                    "draws": 119,
                    "wins": 4628,
                    "losses": 4127,
                    "capture_points": 10525,
                    "battles": 8874,
                    "damage_dealt": 5461529,
                    "hits_percents": 60,
                    "damage_received": 4402832,
                    "shots": 79378,
                    "xp": 3992558,
                    "frags": 8188,
                    "survived_battles": 2925,
                    "dropped_capture_points": 8229
                },
                "company": {
                    "spotted": 442,
                    "hits": 1627,
                    "battle_avg_xp": 451,
                    "draws": 8,
                    "wins": 263,
                    "losses": 235,
                    "capture_points": 932,
                    "battles": 506,
                    "damage_dealt": 340958,
                    "hits_percents": 73,
                    "damage_received": 315231,
                    "shots": 2239,
                    "xp": 228455,
                    "frags": 358,
                    "survived_battles": 191,
                    "dropped_capture_points": 706
                },
                "max_xp": 2386
            },
	 */
	private static final long serialVersionUID = -6362758642864353167L;
	
	private AllStatistics all;

	public AllStatistics getAllStatistics() {
		return all;
	}

	public void setAllStatistics(AllStatistics allStatistics) {
		this.all = allStatistics;
	}
	
	

}
