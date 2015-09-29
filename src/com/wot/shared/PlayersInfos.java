package com.wot.shared;

import java.io.Serializable;
import java.util.Map;

public class PlayersInfos implements Serializable{

	
	/* https://api.worldoftanks.eu/wot/account/info/?application_id=d0a293dc77667c9328783d489c8cef73&account_id=506486576
	 * {
    "status": "ok",
    "count": 1,
    "data": {
        "506486576": {
            "clan": {
                "role_i18n": "Soldier",
                "clan_id": 500006074,
                "role": "private",
                "since": 1367338177
            },
            "achievements": {
                "tank_expert_uk": 0,
                "medal_dumitru": 0,
                "invader": 11,
                "medal_lehvaslaiho": 1,
                "warrior": 43,
                "medal_halonen": 0,
                "medal_pascucci": 4,
                "medal_orlik": 0,
                "medal_brothers_in_arms": 5,
                "mousebane": 0,
                "tank_expert_france": 0,
                "tank_expert_japan": 0,
                "mechanic_engineer_ussr": 0,
                "medal_bruno_pietro": 0,
                "medal_delanglade": 1,
                "lucky_devil": 3,
                "defender": 19,
                "armor_piercer": 1,
                "medal_kay": 2,
                "supporter": 43,
                "mechanic_engineer": 0,
                "steelwall": 62,
                "max_sniper_series": 125,
                "medal_knispel": 2,
                "medal_boelter": 0,
                "medal_ekins": 2,
                "medal_heroes_of_rassenay": 0,
                "medal_tamada_yoshio": 0,
                "tank_expert_usa": 0,
                "mechanic_engineer_germany": 0,
                "max_piercing_series": 54,
                "tank_expert": 0,
                "iron_man": 21,
                "medal_radley_walters": 4,
                "kamikaze": 7,
                "tank_expert_germany": 0,
                "beasthunter": 2,
                "sniper": 166,
                "medal_tarczay": 0,
                "medal_lavrinenko": 2,
                "main_gun": 0,
                "medal_oskin": 0,
                "medal_burda": 0,
                "medal_billotte": 0,
                "huntsman": 2,
                "hand_of_death": 1,
                "medal_fadin": 0,
                "medal_lafayette_pool": 0,
                "max_killing_series": 5,
                "tank_expert_china": 0,
                "mechanic_engineer_usa": 0,
                "medal_kolobanov": 0,
                "patton_valley": 0,
                "bombardier": 0,
                "mechanic_engineer_france": 0,
                "sniper2": 0,
                "medal_abrams": 2,
                "max_invincible_series": 4,
                "medal_poppel": 2,
                "medal_crucial_contribution": 0,
                "raider": 0,
                "max_diehard_series": 7,
                "mechanic_engineer_uk": 0,
                "invincible": 0,
                "lumberjack": 0,
                "sturdy": 30,
                "title_sniper": 1,
                "sinai": 1,
                "diehard": 0,
                "medal_carius": 2,
                "medal_le_clerc": 2,
                "tank_expert_ussr": 0,
                "evileye": 27,
                "mechanic_engineer_japan": 0,
                "mechanic_engineer_china": 0,
                "medal_nikolas": 0,
                "scout": 25
            },
            "statistics": {
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
            "account_id": 506486576,
            "created_at": 1353858845,
            "updated_at": 1392670034,
            "private": null,
            "nickname": "strong44"
        }
    }
}
	 */


	/**
	 * 
	 */
	private static final long serialVersionUID = -1393222615715486990L;
	private String status;
	private Integer count;
	

	Map<String, DataPlayerInfos> data;


	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Map<String, DataPlayerInfos> getData() {
		return data;
	}

	public void setData(Map<String, DataPlayerInfos> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
}

