package com.wot.shared;

import java.io.Serializable;
import java.util.Map;

public class TankEncyclopedia implements Serializable{

	
//	public CommunityAccount(String idUser, String nameAccount) {
//		
//		this.idUser = idUser;
//		this.nameAccount = nameAccount;
//	}
	/* http://api.worldoftanks.eu/2.0/encyclopedia/tanks/?application_id=d0a293dc77667c9328783d489c8cef73
	 * {
{"status":"ok","count":336,"data":{ 
"3073":{ 
"nation_i18n":"U.S.S.R.","name":"#ussr_vehicles:T-46","level":3,"nation":"ussr","is_premium":false,"name_i18n":"T-46","type":"lightTank","tank_id":3073} 
,"6417":{ 
"nation_i18n":"Germany","name":"#germany_vehicles:PzIII_IV","level":5,"nation":"germany","is_premium":false,"name_i18n":"Pz.Kpfw. III\/IV","type":"mediumTank","tank_id":6417} 
,"17":{ 
"nation_i18n":"Germany","name":"#germany_vehicles:PzIV","level":5,"nation":"germany","is_premium":false,"name_i18n":"Pz.Kpfw. IV","type":"mediumTank","tank_id":17} 
,"3137":{ 
"nation_i18n":"France","name":"#france_vehicles:AMX_50_100","level":8,"nation":"france","is_premium":false,"name_i18n":"AMX 50 100","type":"heavyTank","tank_id":3137} 
,"15361":{ 
"nation_i18n":"U.S.S.R.","name":"#ussr_vehicles:T-60","level":2,"nation":"ussr","is_premium":false,"name_i18n":"T-60","type":"lightTank","tank_id":15361} 
,"55313":{ 
"nation_i18n":"Germany","name":"#germany_vehicles:JagdTiger_SdKfz_185","level":8,"nation":"germany","is_premium":true,"name_i18n":"8,8 cm Pak 43 Jagdtiger","type":"AT-SPG","tank_id":55313} 
,"16657":{ 
"nation_i18n":"Germany","name":"#germany_vehicles:RhB_Waffentrager","level":8,"nation":"germany","is_premium":false,"name_i18n":"Rhm.-Borsig Waffenträger","type":"AT-SPG","tank_id":16657} 
	 */

    /**
	 * 
	 */
	private static final long serialVersionUID = -5042002415647520582L;
	
	
	private String status;
	private Integer count;
	
	Map<String, DataTankEncyclopedia> data;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	

	public Map<String, DataTankEncyclopedia> getData() {
		return data;
	}

	public void setData(Map<String, DataTankEncyclopedia> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	


	
}

