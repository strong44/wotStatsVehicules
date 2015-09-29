package com.wot.shared;

import java.io.Serializable;
import java.util.List;


public class PlayerVehicles implements Serializable, Comparable<PlayerVehicles>{

	
//	public CommunityAccount(String idUser, String nameAccount) {
//		
//		this.idUser = idUser;
//		this.nameAccount = nameAccount;
//	}
	/* http://api.worldoftanks.eu/2.0/account/tanks/?application_id=d0a293dc77667c9328783d489c8cef73&account_id=506486576,502674600
	 * {
{
"status":"ok",
"count":1,
"data":[
{"date":1385898857,
"hours_ago":24,
"stat":{ 
"achievements":{ 
"tank_expert_uk":0,"medal_dumitru":0,"invader":26,"medal_lehvaslaiho":0,"warrior":116,"medal_halonen":0,"medal_pascucci":2,"medal_orlik":0,"medal_brothers_in_arms":1,"mousebane":2,"tank_expert_france":1,"mechanic_engineer_ussr":0,"medal_bruno_pietro":0,"medal_delanglade":0,"lucky_devil":1,"defender":26,"armor_piercer":1,"medal_kay":1,"supporter":145,"mechanic_engineer":0,"steelwall":232,"max_sniper_series":49,"medal_knispel":1,"medal_boelter":14,"medal_ekins":1,"medal_heroes_of_rassenay":0,"medal_tamada_yoshio":0,"tank_expert_usa":1,"mechanic_engineer_germany":0,"max_piercing_series":27,"tank_expert":1,"iron_man":7,"medal_radley_walters":2,"kamikaze":23,"tank_expert_germany":1,"beasthunter":15,"sniper":581,"medal_tarczay":0,"medal_lavrinenko":2,"mechanic_engineer_france":0,"medal_oskin":1,"medal_burda":0,"medal_billotte":5,"huntsman":0,"hand_of_death":1,"medal_fadin":1,"medal_lafayette_pool":0,"max_killing_series":5,"tank_expert_china":0,"mechanic_engineer_usa":0,"medal_kolobanov":0,"patton_valley":0,"bombardier":1,"medal_abrams":2,"max_invincible_series":3,"medal_poppel":1,"medal_crucial_contribution":0,"raider":0,"max_diehard_series":5,"mechanic_engineer_uk":0,"invincible":0,"lumberjack":0,"sturdy":12,"title_sniper":1,"sinai":18,"diehard":0,"medal_carius":1,"medal_le_clerc":2,"tank_expert_ussr":1,"evileye":4,"mechanic_engineer_china":0,"medal_nikolas":0,"scout":65},

"ratings":{ 
"spotted":{"place":97640,"value":28440},
"dropped_ctf_points":{ "place":360995,"value":12639},
"battle_avg_xp":{ "place":94606,"value":650},
"battles":{ "place":288113,"value":18370},
"damage_dealt":{ "place":254542,"value":18500952},
"frags":{ "place":209141,"value":18488},
"ctf_points":{ "place":873803,"value":14755},
"integrated_rating":{ "place":173662,"value":18},
"xp":{ "place":147710,"value":11943200},
"battle_avg_performance":{ "place":295870,"value":54},
"battle_wins":{ "place":237633,"value":9886}},

"statistics":{ 

"clan":{ 
"spotted":0,"hits":0,"battle_avg_xp":0,"draws":0,"wins":0,"losses":0,"capture_points":0,"battles":0,"damage_dealt":0,"hits_percents":0,"damage_received":0,"shots":0,"xp":0,"frags":0,"survived_battles":0,"dropped_capture_points":0},

"all":{ 
"spotted":28440,"hits":111093,"battle_avg_xp":650,"draws":313,"wins":9886,"losses":8171,"capture_points":14755,"battles":18370,"damage_dealt":18500952,"hits_percents":63,"damage_received":17508478,"shots":177682,"xp":11943200,"frags":18488,"survived_battles":3681,"dropped_capture_points":12639},

"company":{ 
"spotted":22,"hits":218,"battle_avg_xp":895,"draws":0,"wins":38,"losses":6,"capture_points":48,"battles":44,"damage_dealt":46343,"hits_percents":70,"damage_received":51900,"shots":313,"xp":39359,"frags":26,"survived_battles":19,"dropped_capture_points":1},

"max_xp":2400},

"account_id":461,

"vehicles":[{"wins":7,"mark_of_mastery":0,"battles":21,"tank_id":769},
{"wins":93,"mark_of_mastery":4,"battles":214,"tank_id":2849},
{"wins":26,"mark_of_mastery":0,"battles":52,"tank_id":9473},
{"wins":18,"mark_of_mastery":1,"battles":31,"tank_id":8705},
{"wins":41,"mark_of_mastery":3,"battles":86,"tank_id":64065},
{"wins":79,"mark_of_mastery":4,"battles":156,"tank_id":3921},
{"wins":168,"mark_of_mastery":4,"battles":270,"tank_id":3329},
{"wins":5,"mark_of_mastery":2,"battles":8,"tank_id":54353},
{"wins":147,"mark_of_mastery":4,"battles":316,"tank_id":7457},
{"wins":59,"mark_of_mastery":0,"battles":107,"tank_id":2321},
{"wins":28,"mark_of_mastery":2,"battles":61,"tank_id":55297},
{"wins":170,"mark_of_mastery":4,"battles":363,"tank_id":7489},
{"wins":109,"mark_of_mastery":0,"battles":208,"tank_id":5377},
{"wins":97,"mark_of_mastery":3,"battles":183,"tank_id":2593},
{"wins":26,"mark_of_mastery":2,"battles":35,"tank_id":1073},
{"wins":125,"mark_of_mastery":0,"battles":228,"tank_id":4385},
{"wins":7,"mark_of_mastery":0,"battles":14,"tank_id":289},
{"wins":1,"mark_of_mastery":0,"battles":2,"tank_id":545},
{"wins":460,"mark_of_mastery":3,"battles":858,"tank_id":53249},
{"wins":5,"mark_of_mastery":2,"battles":8,"tank_id":11857},
{"wins":59,"mark_of_mastery":4,"battles":104,"tank_id":14353},
{"wins":132,"mark_of_mastery":3,"battles":239,"tank_id":1313},
{"wins":7,"mark_of_mastery":0,"battles":15,"tank_id":4881},
{"wins":3,"mark_of_mastery":2,"battles":9,"tank_id":6993},
{"wins":48,"mark_of_mastery":2,"battles":91,"tank_id":3649},
{"wins":7,"mark_of_mastery":2,"battles":12,"tank_id":3409},
{"wins":78,"mark_of_mastery":4,"battles":144,"tank_id":7969},
{"wins":320,"mark_of_mastery":4,"battles":659,"tank_id":54289},
{"wins":79,"mark_of_mastery":4,"battles":142,"tank_id":7441},
{"wins":36,"mark_of_mastery":3,"battles":53,"tank_id":849},
{"wins":42,"mark_of_mastery":4,"battles":79,"tank_id":54097},
{"wins":95,"mark_of_mastery":0,"battles":128,"tank_id":6657},{"wins":10,"mark_of_mastery":4,"battles":24,"tank_id":4641},{"wins":11,"mark_of_mastery":3,"battles":14,"tank_id":4401},{"wins":18,"mark_of_mastery":2,"battles":41,"tank_id":12305},{"wins":6,"mark_of_mastery":0,"battles":11,"tank_id":1825},{"wins":139,"mark_of_mastery":3,"battles":253,"tank_id":5697},{"wins":140,"mark_of_mastery":2,"battles":218,"tank_id":7681},{"wins":10,"mark_of_mastery":0,"battles":19,"tank_id":2065},{"wins":109,"mark_of_mastery":2,"battles":216,"tank_id":11777},{"wins":59,"mark_of_mastery":3,"battles":94,"tank_id":8977},{"wins":24,"mark_of_mastery":4,"battles":69,"tank_id":5393},{"wins":4,"mark_of_mastery":3,"battles":9,"tank_id":14113},{"wins":46,"mark_of_mastery":4,"battles":92,"tank_id":5153},{"wins":1,"mark_of_mastery":4,"battles":1,"tank_id":1329},{"wins":11,"mark_of_mastery":4,"battles":22,"tank_id":52257},{"wins":32,"mark_of_mastery":4,"battles":59,"tank_id":4657},{"wins":54,"mark_of_mastery":3,"battles":107,"tank_id":273},{"wins":2,"mark_of_mastery":2,"battles":3,"tank_id":81},{"wins":148,"mark_of_mastery":1,"battles":286,"tank_id":8449},{"wins":29,"mark_of_mastery":3,"battles":71,"tank_id":51457},{"wins":56,"mark_of_mastery":4,"battles":117,"tank_id":4129},{"wins":32,"mark_of_mastery":4,"battles":65,"tank_id":57617},{"wins":22,"mark_of_mastery":2,"battles":51,"tank_id":5969},{"wins":4,"mark_of_mastery":2,"battles":9,"tank_id":2385},{"wins":12,"mark_of_mastery":4,"battles":20,"tank_id":54529},{"wins":209,"mark_of_mastery":4,"battles":375,"tank_id":8961},{"wins":68,"mark_of_mastery":2,"battles":123,"tank_id":529},{"wins":55,"mark_of_mastery":3,"battles":106,"tank_id":3585},{"wins":15,"mark_of_mastery":3,"battles":36,"tank_id":16897},{"wins":11,"mark_of_mastery":3,"battles":15,"tank_id":54545},{"wins":39,"mark_of_mastery":4,"battles":66,"tank_id":3153},{"wins":22,"mark_of_mastery":4,"battles":42,"tank_id":11089},{"wins":61,"mark_of_mastery":0,"battles":107,"tank_id":4113},{"wins":26,"mark_of_mastery":2,"battles":46,"tank_id":10785},{"wins":47,"mark_of_mastery":0,"battles":93,"tank_id":4929},{"wins":411,"mark_of_mastery":3,"battles":700,"tank_id":4353},{"wins":44,"mark_of_mastery":0,"battles":85,"tank_id":2577},{"wins":68,"mark_of_mastery":3,"battles":136,"tank_id":11009},{"wins":34,"mark_of_mastery":0,"battles":55,"tank_id":5409},{"wins":36,"mark_of_mastery":0,"battles":79,"tank_id":2049},{"wins":14,"mark_of_mastery":0,"battles":21,"tank_id":1537},{"wins":38,"mark_of_mastery":4,"battles":67,"tank_id":53585},{"wins":49,"mark_of_mastery":4,"battles":102,"tank_id":53841},{"wins":12,"mark_of_mastery":2,"battles":24,"tank_id":51745},{"wins":174,"mark_of_mastery":0,"battles":343,"tank_id":513},{"wins":11,"mark_of_mastery":4,"battles":29,"tank_id":3121},{"wins":25,"mark_of_mastery":3,"battles":45,"tank_id":3617},{"wins":21,"mark_of_mastery":2,"battles":35,"tank_id":16641},{"wins":3,"mark_of_mastery":2,"battles":8,"tank_id":337},{"wins":6,"mark_of_mastery":2,"battles":9,"tank_id":577},{"wins":2,"mark_of_mastery":0,"battles":2,"tank_id":1345},{"wins":97,"mark_of_mastery":0,"battles":194,"tank_id":11265},{"wins":22,"mark_of_mastery":2,"battles":42,"tank_id":4433},{"wins":111,"mark_of_mastery":0,"battles":195,"tank_id":8465},{"wins":209,"mark_of_mastery":0,"battles":403,"tank_id":5137},{"wins":105,"mark_of_mastery":4,"battles":173,"tank_id":52737},{"wins":25,"mark_of_mastery":3,"battles":58,"tank_id":257},{"wins":153,"mark_of_mastery":4,"battles":306,"tank_id":10257},{"wins":37,"mark_of_mastery":4,"battles":71,"tank_id":4945},{"wins":7,"mark_of_mastery":0,"battles":9,"tank_id":2881},{"wins":329,"mark_of_mastery":4,"battles":589,"tank_id":2561},{"wins":57,"mark_of_mastery":3,"battles":112,"tank_id":9217},{"wins":2,"mark_of_mastery":0,"battles":3,"tank_id":3089},{"wins":13,"mark_of_mastery":4,"battles":17,"tank_id":2817},{"wins":99,"mark_of_mastery":0,"battles":190,"tank_id":3873},{"wins":131,"mark_of_mastery":1,"battles":288,"tank_id":9505},{"wins":54,"mark_of_mastery":3,"battles":95,"tank_id":9745},{"wins":9,"mark_of_mastery":3,"battles":25,"tank_id":6481},{"wins":3,"mark_of_mastery":0,"battles":7,"tank_id":1025},{"wins":4,"mark_of_mastery":0,"battles":5,"tank_id":5953},{"wins":13,"mark_of_mastery":0,"battles":32,"tank_id":7169},{"wins":147,"mark_of_mastery":3,"battles":276,"tank_id":17},{"wins":37,"mark_of_mastery":0,"battles":63,"tank_id":5185},{"wins":52,"mark_of_mastery":3,"battles":110,"tank_id":3377},{"wins":13,"mark_of_mastery":3,"battles":22,"tank_id":2897},{"wins":18,"mark_of_mastery":3,"battles":31,"tank_id":4689},{"wins":32,"mark_of_mastery":3,"battles":55,"tank_id":57361},{"wins":88,"mark_of_mastery":4,"battles":143,"tank_id":1105},{"wins":2,"mark_of_mastery":3,"battles":3,"tank_id":7761},{"wins":2,"mark_of_mastery":3,"battles":3,"tank_id":10497},{"wins":2,"mark_of_mastery":3,"battles":4,"tank_id":2353},{"wins":106,"mark_of_mastery":1,"battles":194,"tank_id":51489},{"wins":49,"mark_of_mastery":0,"battles":87,"tank_id":4865},{"wins":161,"mark_of_mastery":4,"battles":309,"tank_id":52513},{"wins":23,"mark_of_mastery":3,"battles":39,"tank_id":10001},{"wins":461,"mark_of_mastery":4,"battles":754,"tank_id":7937},{"wins":163,"mark_of_mastery":3,"battles":282,"tank_id":4369},{"wins":36,"mark_of_mastery":4,"battles":64,"tank_id":4913},{"wins":100,"mark_of_mastery":1,"battles":180,"tank_id":11521},{"wins":13,"mark_of_mastery":4,"battles":22,"tank_id":13345},{"wins":1,"mark_of_mastery":1,"battles":1,"tank_id":10577},{"wins":44,"mark_of_mastery":4,"battles":71,"tank_id":51713},{"wins":17,"mark_of_mastery":3,"battles":35,"tank_id":1057},{"wins":27,"mark_of_mastery":0,"battles":47,"tank_id":6417},{"wins":39,"mark_of_mastery":3,"battles":71,"tank_id":5457},{"wins":290,"mark_of_mastery":2,"battles":509,"tank_id":1},{"wins":69,"mark_of_mastery":0,"battles":129,"tank_id":1569},{"wins":120,"mark_of_mastery":4,"battles":213,"tank_id":5921},{"wins":106,"mark_of_mastery":4,"battles":168,"tank_id":10769},{"wins":5,"mark_of_mastery":1,"battles":7,"tank_id":1297},{"wins":183,"mark_of_mastery":0,"battles":340,"tank_id":4097},{"wins":60,"mark_of_mastery":3,"battles":110,"tank_id":5169},{"wins":1,"mark_of_mastery":4,"battles":2,"tank_id":5201},{"wins":9,"mark_of_mastery":1,"battles":21,"tank_id":9761},{"wins":65,"mark_of_mastery":4,"battles":135,"tank_id":7233},{"wins":16,"mark_of_mastery":2,"battles":39,"tank_id":12289},{"wins":65,"mark_of_mastery":3,"battles":122,"tank_id":13825},{"wins":15,"mark_of_mastery":3,"battles":22,"tank_id":2129},{"wins":160,"mark_of_mastery":3,"battles":329,"tank_id":8993},{"wins":341,"mark_of_mastery":4,"battles":621,"tank_id":49},{"wins":73,"mark_of_mastery":4,"battles":139,"tank_id":6465},{"wins":72,"mark_of_mastery":0,"battles":141,"tank_id":5633},{"wins":4,"mark_of_mastery":1,"battles":8,"tank_id":7505},{"wins":49,"mark_of_mastery":0,"battles":96,"tank_id":7185}]}}]}
	 */

/**
	 * 
	 */
	private static final long serialVersionUID = 78409739521242390L;
	
	//    public static final ProvidesKey<PlayerVehicles> KEY_PROVIDER = new ProvidesKey<PlayerVehicles>() {
//        @Override
//        public Object getKey(PlayerVehicles item) {
//          return item == null ? null : item.getIdUser();
//        }
//      };
	/**
	 * 
	 */

	private String status;
	private Integer count;

	List<DataPlayerVehicles> data;
	
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<DataPlayerVehicles> getData() {
		return data;
	}

	public void setData(List<DataPlayerVehicles> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	@Override
//	public int compareTo(PlayerVehicles o) {
//		// TODO Auto-generated method stub
//		return (o == null || o.getName() == null) ? -1 : o.getName().compareTo(getName());
//	}

//	@Override
//    public boolean equals(Object o) {
//      if (o instanceof PlayerVehicles) {
//        return getIdUser().equalsIgnoreCase(((PlayerVehicles) o).getIdUser());
//      }
//      return false;
//    }



	@Override
	public int compareTo(PlayerVehicles arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

