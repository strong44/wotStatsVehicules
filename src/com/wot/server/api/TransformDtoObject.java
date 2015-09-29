package com.wot.server.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wot.server.DaoClan;
import com.wot.server.DaoCommunityAccount2;
import com.wot.server.DaoCommunityClan2;
import com.wot.server.DaoDataClan;
import com.wot.server.DaoDataCommunityAccount2;
import com.wot.server.DaoDataCommunityAccountRatings2;
import com.wot.server.DaoDataCommunityClanMembers;
import com.wot.server.DaoDataCommunityMembers;
import com.wot.server.DaoItemsDataClan;
import com.wot.shared.AllStatistics;
import com.wot.shared.Clan;
import com.wot.shared.CommunityAccount;
import com.wot.shared.CommunityClan;
import com.wot.shared.DataClan;
import com.wot.shared.DataCommunityClan;
import com.wot.shared.DataCommunityClanMembers;
import com.wot.shared.DataCommunityMembers;
import com.wot.shared.DataPlayerInfos;
import com.wot.shared.ItemsDataClan;
import com.wot.shared.PlayersInfos;
import com.wot.shared.Statistics;
//import com.wot.server.DaoDataCommunityAccountStats;

public class TransformDtoObject {

	public static DaoClan TransformClanToDaoClan(Clan clan ) {
		
		DaoClan daoClan = new DaoClan();
		daoClan.setStatus( clan.getStatus());
		
		daoClan.setItems(TransformItemsDataClanToDaoItemsDataClan(clan.getItems()));
		return daoClan;
	}

	public static DaoDataClan TransformDataClanToDaoDataClan(DataClan dataclan ) {
		
		DaoDataClan daoDataClan = new DaoDataClan();
		daoDataClan.setItems(TransformItemsDataClanToDaoItemsDataClan(dataclan.getItems()));
		return daoDataClan;
	}
	
	//transforme la liste des itemsDataclan
	public static List<DaoItemsDataClan> TransformItemsDataClanToDaoItemsDataClan(List<ItemsDataClan> itemsDataclan ) {
		
		List<DaoItemsDataClan> listDaoItemsDataClan = new ArrayList<DaoItemsDataClan>();
		
		//transform each ItemsDataClan to DAOItemsDataClan
		for (ItemsDataClan myItemsDataClan : itemsDataclan) {
			listDaoItemsDataClan.add(TransformItemsDataClanToDaoItemsDataClan(myItemsDataClan));
		}
		return listDaoItemsDataClan;
		
	}
	
	//transforme un itemsDataclan
	public static DaoItemsDataClan TransformItemsDataClanToDaoItemsDataClan(ItemsDataClan itemsDataclan ) {
		
		DaoItemsDataClan daoItemsDataClan = new DaoItemsDataClan();

		daoItemsDataClan.setClan_color(itemsDataclan.getClan_color());
		
		
		daoItemsDataClan.setCreated_at(itemsDataclan.getCreated_at());
		
		daoItemsDataClan.setId(itemsDataclan.getId());
		
		daoItemsDataClan.setMember_count(itemsDataclan.getMember_count());
		
		
		daoItemsDataClan.setName(itemsDataclan.getName());
		
		
		
		return daoItemsDataClan;
		
	}
	
//	//== Tranform datas CLAN and members 
//	public static DaoCommunityClan TransformCommunityClanToDaoCommunityClan(CommunityClan communityClan ) {
//		
//		DaoCommunityClan daoCommunityClan = new DaoCommunityClan();
//		daoCommunityClan.setStatus( communityClan.getStatus());
//		daoCommunityClan.setStatus_code(communityClan.getStatus_code());
//		
//		daoCommunityClan.setIdClan(communityClan.getIdClan());
//		daoCommunityClan.setDateCommunityClan(communityClan.getDateCommunityClan());
//		
//		daoCommunityClan.setData(TransformDataCommunityClanToDaoDataCommunityClan(communityClan.getData()));
//		return daoCommunityClan;
//	}

	//== new  
	public static CommunityClan TransformCommunityDaoCommunityClanToCommunityClan(DaoCommunityClan2 daoCommunityClan ) {
		
		CommunityClan communityClan = new CommunityClan();
		//communityClan.setStatus( daoCommunityClan.getStatus());
		//communityClan.setStatus_code(daoCommunityClan.getStatus_code());
		
		communityClan.setIdClan(daoCommunityClan.getIdClan());
		communityClan.setDateCommunityClan(daoCommunityClan.getDateCommunityClan());
		
		communityClan.setData(TransformDaoDataCommunityClanToDataCommunityClan(daoCommunityClan.getData()));
		return communityClan;
	}

	
	private static DataCommunityClan TransformDaoDataCommunityClanToDataCommunityClan( Map<String, DaoDataCommunityClanMembers> data) {
		DataCommunityClan myDataCommunityClan = new DataCommunityClan();
		//data
		myDataCommunityClan.setMembers(TransformListDaoDataCommunityClanMembersToListDataCommunityClanMembers(data));
		
		return myDataCommunityClan;
	}

	private static List<DataCommunityClanMembers> TransformListDaoDataCommunityClanMembersToListDataCommunityClanMembers(Map<String, DaoDataCommunityClanMembers> map) {
		List<DataCommunityClanMembers> listDataCommunityClanMembers = new ArrayList<DataCommunityClanMembers>();
		
		//transform each ItemsDataClan to DAOItemsDataClan
		Set<Entry<String, DaoDataCommunityClanMembers>> set =  map.entrySet();
		for (Entry<String, DaoDataCommunityClanMembers> entry :set) {
			listDataCommunityClanMembers.add(TransformDaoDataCommunityClanMembersToDataCommunityClanMembers(entry.getValue()));
		}
		return listDataCommunityClanMembers;
	}

	private static DataCommunityClanMembers TransformDaoDataCommunityClanMembersToDataCommunityClanMembers(DaoDataCommunityClanMembers clanMembers) {
		DataCommunityClanMembers myDataCommunityClanMembers = new DataCommunityClanMembers();
		List<DataCommunityMembers> listDataCommunityMembers = new ArrayList<DataCommunityMembers>();
		
		//myDataCommunityClanMembers.setAccount_id(clanMembers.getAccount_id());
		//myDataCommunityClanMembers.setAccount_name(clanMembers.getAccount_name());
		
		//Transform 
		//Set<Entry<String, DaoDataCommunityMembers>> set =  clanMembers.getMembers();
		for (DaoDataCommunityMembers myDaoDataCommunityMembers :  clanMembers.getMembers()) {
			listDataCommunityMembers.add(TransformDaoDataCommunityMembersToDataCommunityMembers(myDaoDataCommunityMembers));
		}
		
		//add list !! 
		myDataCommunityClanMembers.setMembers(listDataCommunityMembers);
		return myDataCommunityClanMembers;
	}

	private static DataCommunityMembers TransformDaoDataCommunityMembersToDataCommunityMembers(DaoDataCommunityMembers myDaoDataCommunityMembers) {
		// TODO Auto-generated method stub
		DataCommunityMembers myDataCommunityMembers = new DataCommunityMembers();
		//
		myDataCommunityMembers.setAccount_id(myDaoDataCommunityMembers.getAccount_id());
		myDataCommunityMembers.setAccount_name(myDaoDataCommunityMembers.getAccount_name());
		//myDataCommunityMembers.setCreated_at(myDaoDataCommunityMembers.getCreated_at());
		//myDataCommunityMembers.setRole(myDaoDataCommunityMembers.getRole());
		//myDataCommunityMembers.setUpdated_at(myDaoDataCommunityMembers.getUpdated_at());
		
		return myDataCommunityMembers;
	}

	public static DaoCommunityAccount2 TransformCommunityAccountToDaoCommunityAccount(DataPlayerInfos dataPlayerInfos) {
		// TODO Auto-generated method stub
		DaoCommunityAccount2 myDaoCommunityAccount = new DaoCommunityAccount2();
		//myDaoCommunityAccount.setStatus( dataPlayerInfos.getStatus());
		//myDaoCommunityAccount.setStatus_code(account.getStatus_code());
		myDaoCommunityAccount.setIdUser(String.valueOf(dataPlayerInfos.getAccount_id()));
		//myDaoCommunityAccount.setDateCommunityAccount(account.getDateCommunityAccount());
		myDaoCommunityAccount.setName(dataPlayerInfos.getNickname());	
		//! TODO setData
		myDaoCommunityAccount.setData(TransformDataCommunityAccountToDaoDataCommunityAccount(dataPlayerInfos.getStatistics()));
		
		return myDaoCommunityAccount;
	}

	
	public static CommunityAccount TransformDaoCommunityAccountToCommunityAccount(DaoCommunityAccount2 daoAccount) {
		// TODO Auto-generated method stub
		CommunityAccount myCommunityAccount = new CommunityAccount();
		myCommunityAccount.setStatus( daoAccount.getStatus());
		//myCommunityAccount.setStatus_code(daoAccount.getStatus_code());
		myCommunityAccount.setIdUser(daoAccount.getIdUser());
		//myCommunityAccount.setDateCommunityAccount(daoAccount.getDateCommunityAccount());
		myCommunityAccount.setName(daoAccount.getName());
		//bug here data is null !!
		myCommunityAccount.setData(TransformDaoDataCommunityAccountToDataCommunityAccount(daoAccount.getData()));
		
		return myCommunityAccount;
	}

	
	private static DataPlayerInfos TransformDaoDataCommunityAccountToDataCommunityAccount(DaoDataCommunityAccount2 data) {
		
		//	build return object
		DataPlayerInfos myDataPlayerInfos = new DataPlayerInfos();
		//
		Statistics statistics = new Statistics();
		AllStatistics allStatistics = new AllStatistics();
		myDataPlayerInfos.setStatistics(statistics);
		myDataPlayerInfos.getStatistics().setAllStatistics(allStatistics);
		
		if(data.getStats().getBattle_avg_performanceCalc() != null)
			myDataPlayerInfos.getStatistics().getAllStatistics().setBattle_avg_performanceCalc(data.getStats().getBattle_avg_performanceCalc());
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setBattle_avg_xp(data.getStats().getBattle_avg_xp());
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setWins(data.getStats().getBattle_wins());
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setBattles(data.getStats().getBattles());
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setCapture_points(data.getStats().getCtf_points());
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setDamage_dealt(data.getStats().getDamage_dealt());
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setDropped_capture_points(data.getStats().getDropped_ctf_points());
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setFrags(data.getStats().getFrags());
		
		if (data.getStats().getRatioCtfPoints() != null)
			myDataPlayerInfos.getStatistics().getAllStatistics().setRatioCtfPoints(new Double(data.getStats().getRatioCtfPoints()));
		
		if (data.getStats().getRatioDamagePoints() != null) 
			myDataPlayerInfos.getStatistics().getAllStatistics().setRatioDamagePoints(new Double(data.getStats().getRatioDamagePoints()));
		
		if(data.getStats().getRatioDestroyedPoints()!= null) 
			myDataPlayerInfos.getStatistics().getAllStatistics().setRatioDestroyedPoints(new Double(data.getStats().getRatioDestroyedPoints()));
		
		if(data.getStats().getRatioDetectedPoints() != null)
			myDataPlayerInfos.getStatistics().getAllStatistics().setRatioDetectedPoints(new Double(data.getStats().getRatioDetectedPoints()));
		
		if(data.getStats().getRatioDestroyedPoints() != null)
			myDataPlayerInfos.getStatistics().getAllStatistics().setRatioDroppedCtfPoints(new Double(data.getStats().getRatioDestroyedPoints()));
		
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setSpotted(data.getStats().getSpotted());
		
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setXp(data.getStats().getXp());
		
		Double avLevel =  data.getStats().getAverageLevel();
		if(avLevel == null)
			avLevel = 0.0;
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setAverageLevelTankCalc(avLevel);
		//
		Double wn8 =  data.getStats().getWn8();
		if(wn8 == null)
			wn8 = 0.0;
		
		myDataPlayerInfos.getStatistics().getAllStatistics().setWn8(wn8);
		
		
		return myDataPlayerInfos;
		
		
	}

	private static DaoDataCommunityAccount2 TransformDataCommunityAccountToDaoDataCommunityAccount(Statistics statistics) {
		DaoDataCommunityAccount2 myDaoDataCommunityAccount = new DaoDataCommunityAccount2();
		myDaoDataCommunityAccount.setStats(TransformDataCommunityAccountRatingsToDaoDataCommunityAccountRatings(statistics));
		
		//myDaoDataCommunityAccount.setAchievements(TransformDataCommunityAccountAchievementsToDaoDataCommunityAccountAchievements(map.getAchievements()));
		//myDaoDataCommunityAccount.setStats(TransformDataCommunityAccountStatsToDaoDataCommunityAccountStats(map.getStats()));
		//
		//myDaoDataCommunityAccount.setStatsVehicules(TransformDataCommunityAccountStatsVehiculesToDaoDataCommunityAccountStatsVehicules(map.getVehicles()));
		return myDaoDataCommunityAccount;
	}

	private static DaoDataCommunityAccountRatings2 TransformDataCommunityAccountRatingsToDaoDataCommunityAccountRatings(Statistics statistics) {
		DaoDataCommunityAccountRatings2 myDaoDataCommunityAccountRatings = new DaoDataCommunityAccountRatings2();
		
		//avg perf : ratio wins/battles 
		//Double perf =  new Double(statistics.getAllStatistics().getWins())/ new Double(statistics.getAllStatistics().getBattles());
		//myDaoDataCommunityAccountRatings.setBattle_avg_performance(perf);
		
		myDaoDataCommunityAccountRatings.setBattle_avg_performanceCalc(statistics.getAllStatistics().getBattle_avg_performanceCalc());
		
		myDaoDataCommunityAccountRatings.setBattle_avg_xp(new Double(statistics.getAllStatistics().getBattle_avg_xp()));
		
		myDaoDataCommunityAccountRatings.setBattle_wins(new Double(statistics.getAllStatistics().getWins()));
		
		myDaoDataCommunityAccountRatings.setBattles(new Double(statistics.getAllStatistics().getBattles()));
		
		myDaoDataCommunityAccountRatings.setCtf_points(new Double(statistics.getAllStatistics().getCapture_points()));
		
		myDaoDataCommunityAccountRatings.setDamage_dealt(new Double(statistics.getAllStatistics().getDamage_dealt()));
		
		myDaoDataCommunityAccountRatings.setDropped_ctf_points(new Double(statistics.getAllStatistics().getDropped_capture_points()));
		
		myDaoDataCommunityAccountRatings.setFrags(new Double(statistics.getAllStatistics().getFrags()));
		
	
		myDaoDataCommunityAccountRatings.setRatioCtfPoints(new Double(statistics.getAllStatistics().getRatioCtfPoints()));
		
		if (statistics.getAllStatistics().getRatioDamagePoints() != null) 
			myDaoDataCommunityAccountRatings.setRatioDamagePoints(new Double(statistics.getAllStatistics().getRatioDamagePoints()));
		
		if(statistics.getAllStatistics().getRatioDestroyedPoints()!= null) 
			myDaoDataCommunityAccountRatings.setRatioDestroyedPoints(new Double(statistics.getAllStatistics().getRatioDestroyedPoints()));
		
		if(statistics.getAllStatistics().getRatioDetectedPoints() != null)
			myDaoDataCommunityAccountRatings.setRatioDetectedPoints(new Double(statistics.getAllStatistics().getRatioDetectedPoints()));
		
		if(statistics.getAllStatistics().getRatioDestroyedPoints() != null)
			myDaoDataCommunityAccountRatings.setRatioDroppedCtfPoints(new Double(statistics.getAllStatistics().getRatioDestroyedPoints()));
		
		
		myDaoDataCommunityAccountRatings.setSpotted(new Double(statistics.getAllStatistics().getSpotted()));
		
		
		myDaoDataCommunityAccountRatings.setXp(new Double(statistics.getAllStatistics().getXp()));
		
		myDaoDataCommunityAccountRatings.setAverageLevel(new Double(statistics.getAllStatistics().getAverageLevelTankCalc()));
		
		myDaoDataCommunityAccountRatings.setWn8(new Double(statistics.getAllStatistics().getWn8()));
		
		return myDaoDataCommunityAccountRatings;
	}

//	private static List<DaoDataCommunityAccountStatsVehicules> TransformDataCommunityAccountStatsVehiculesToDaoDataCommunityAccountStatsVehicules(
//			List<DataCommunityAccountVehicules> vehicules) {
//		// TODO Auto-generated method stub
//		List<DaoDataCommunityAccountStatsVehicules> listDaoDataCommunityAccountStatsVehicules = new ArrayList<DaoDataCommunityAccountStatsVehicules>();
//		
//		for (DataCommunityAccountVehicules myDataCommunityAccountVehicules : vehicules) {
//			//on ne prend que les chars qui ont + de 100 batailles sinon trop de chars
//			if (myDataCommunityAccountVehicules.getBattle_count()>=100) {
//				listDaoDataCommunityAccountStatsVehicules.add(TransformDataCommunityAccountStatsVehiculeToDaoDataCommunityAccountStatsVehicule(myDataCommunityAccountVehicules));
//			}
//		}
//		return listDaoDataCommunityAccountStatsVehicules;
//	}

//	private static DaoDataCommunityAccountStatsVehicules TransformDataCommunityAccountStatsVehiculeToDaoDataCommunityAccountStatsVehicule(
//			DataCommunityAccountVehicules myDataCommunityAccountVehicules) {
//		// TODO Auto-generated method stub
//		/**
//		 * 	/**
//	 * {
//        "spotted": 0, 
//        "localized_name": "KV-1S", 
//        "name": "KV-1s", 
//        "level": 6, 
//        "damageDealt": 0, 
//        "survivedBattles": 0, 
//        "battle_count": 1400, 
//        "nation": "ussr", 
//        "image_url": "/static/2.7.0/encyclopedia/tankopedia/vehicle/small/ussr-kv-1s.png", 
//        "frags": 0, 
//        "win_count": 773, 
//        "class": "heavyTank"
//      },  */
//		DaoDataCommunityAccountStatsVehicules myDaoDataCommunityAccountStatsVehicules = new DaoDataCommunityAccountStatsVehicules();
//		myDaoDataCommunityAccountStatsVehicules.setBattle_count(myDataCommunityAccountVehicules.getBattle_count());
//		myDaoDataCommunityAccountStatsVehicules.setImage_url(myDataCommunityAccountVehicules.getImage_url());
//		myDaoDataCommunityAccountStatsVehicules.setLevel(myDataCommunityAccountVehicules.getLevel());
//		myDaoDataCommunityAccountStatsVehicules.setLocalized_name(myDataCommunityAccountVehicules.getLocalized_name());
//		myDaoDataCommunityAccountStatsVehicules.setName(myDataCommunityAccountVehicules.getName());
//		myDaoDataCommunityAccountStatsVehicules.setNation(myDataCommunityAccountVehicules.getNation());
//		myDaoDataCommunityAccountStatsVehicules.setWin_count(myDataCommunityAccountVehicules.getWin_count());
//		return myDaoDataCommunityAccountStatsVehicules;
//	}

//	private static DataCommunityAccount TransformDaoDataCommunityAccountToDataCommunityAccount(DaoDataCommunityAccount data) {
//		DataCommunityAccount myDataCommunityAccount = new DataCommunityAccount();
//		myDataCommunityAccount.setName(data.getName());
//		
//		myDataCommunityAccount.setAchievements(TransformDaoDataCommunityAccountAchievementsToDataCommunityAccountAchievements(data.getAchievements()));
//		myDataCommunityAccount.setStats(TransformDaoDataCommunityAccountStatsToDataCommunityAccountStats(data.getStats()));
//		myDataCommunityAccount.setVehicles(TransformDaoDataCommunityAccountVehiclesToDataCommunityAccountVehicles(data.getStatsVehicules()));
//		return myDataCommunityAccount;
//	}
	
	
//	private static List<DataCommunityAccountVehicules> TransformDaoDataCommunityAccountVehiclesToDataCommunityAccountVehicles(List<DaoDataCommunityAccountStatsVehicules> statsVehicules) {
//		// TODO Auto-generated method stub
//		List<DataCommunityAccountVehicules> listDataCommAccVeh = new ArrayList<DataCommunityAccountVehicules>();
//		for(DaoDataCommunityAccountStatsVehicules myDaoDataCommunityAccountStatsVehicules : statsVehicules ) {
//			DataCommunityAccountVehicules  myDataCommunityAccountVehicules = new DataCommunityAccountVehicules();
//			myDataCommunityAccountVehicules.setBattle_count(myDaoDataCommunityAccountStatsVehicules.getBattle_count());
//			myDataCommunityAccountVehicules.setName(myDaoDataCommunityAccountStatsVehicules.getName());
//			myDataCommunityAccountVehicules.setWin_count(myDaoDataCommunityAccountStatsVehicules.getWin_count());
//			myDataCommunityAccountVehicules.setImage_url(myDaoDataCommunityAccountStatsVehicules.getImage_url());
//			myDataCommunityAccountVehicules.setLevel(myDaoDataCommunityAccountStatsVehicules.getLevel());
//			myDataCommunityAccountVehicules.setNation(myDaoDataCommunityAccountStatsVehicules.getNation());
//			listDataCommAccVeh.add(myDataCommunityAccountVehicules);
//		}
//		return listDataCommAccVeh;
//	}

//	private static DataPlayerInfos TransformDaoDataCommunityAccountStatsToDataCommunityAccountStats(DaoDataCommunityAccountStats stats) {
//		DataPlayerInfos myDataCommunityAccountStats =  new DataPlayerInfos();
//		
//		myDataCommunityAccountStats.setBattle_avg_performance(new Double(stats.getBattle_avg_performance()));
//		myDataCommunityAccountStats.setBattle_avg_xp(new Double(stats.getBattle_avg_xp()));
//		myDataCommunityAccountStats.setBattle_wins(new Double(stats.getBattle_wins()));
//		myDataCommunityAccountStats.setBattles(new Double(stats.getBattles()));
//		myDataCommunityAccountStats.setCtf_points(new Double(stats.getCtf_points()));
//		myDataCommunityAccountStats.setDamage_dealt(new Double(stats.getDamage_dealt()));
//		myDataCommunityAccountStats.setDropped_ctf_points(new Double(stats.getDropped_ctf_points()));
//		myDataCommunityAccountStats.setFrags(new Double(stats.getFrags()));
//		myDataCommunityAccountStats.setIntegrated_rating(new Double(stats.getIntegrated_rating()));
//		myDataCommunityAccountStats.setSpotted(new Double(stats.getSpotted()));
//		myDataCommunityAccountStats.setXp(new Double(stats.getXp()));
//		
//		
//		
//		return myDataCommunityAccountStats;
//	}

//	private static DataCommunityAccountAchievements TransformDaoDataCommunityAccountAchievementsToDataCommunityAccountAchievements(DaoDataCommunityAccountAchievements achievements) {
//		// TODO Auto-generated method stub
//		DataCommunityAccountAchievements myDataCommunityAccountAchievements = new DataCommunityAccountAchievements();
//		myDataCommunityAccountAchievements.setBeasthunter(achievements.getBeasthunter());
//		myDataCommunityAccountAchievements.setDefender(achievements.getDefender());
//		myDataCommunityAccountAchievements.setDiehard(achievements.getDiehard());
//		myDataCommunityAccountAchievements.setInvader(achievements.getInvader());
//		myDataCommunityAccountAchievements.setLumberjack(achievements.getLumberjack());
//		myDataCommunityAccountAchievements.setMedalAbrams(achievements.getMedalAbrams());
//		myDataCommunityAccountAchievements.setMedalBillotte(achievements.getMedalBillotte());
//		myDataCommunityAccountAchievements.setMedalBurda(achievements.getMedalBurda());
//		myDataCommunityAccountAchievements.setMedalCarius(achievements.getMedalCarius());
//		myDataCommunityAccountAchievements.setMedalEkins(achievements.getMedalEkins());
//		myDataCommunityAccountAchievements.setMedalFadin(achievements.getMedalFadin());
//		myDataCommunityAccountAchievements.setMedalHalonen(achievements.getMedalHalonen());
//		myDataCommunityAccountAchievements.setMedalKay(achievements.getMedalKay());
//		myDataCommunityAccountAchievements.setMedalKnispel(achievements.getMedalKnispel());
//		myDataCommunityAccountAchievements.setMedalKolobanov(achievements.getMedalKolobanov());
//		myDataCommunityAccountAchievements.setMedalLavrinenko(achievements.getMedalLavrinenko());
//		myDataCommunityAccountAchievements.setMedalLeClerc(achievements.getMedalLeClerc());
//		myDataCommunityAccountAchievements.setMedalOrlik(achievements.getMedalOrlik());
//		myDataCommunityAccountAchievements.setMedalOskin(achievements.getMedalOskin());
//		myDataCommunityAccountAchievements.setMedalPoppel(achievements.getMedalPoppel() );
//		myDataCommunityAccountAchievements.setMedalWittmann(achievements.getMedalWittmann());
//		myDataCommunityAccountAchievements.setMousebane(achievements.getMousebane());
//		myDataCommunityAccountAchievements.setRaider(achievements.getRaider());
//		myDataCommunityAccountAchievements.setScout(achievements.getScout());
//		myDataCommunityAccountAchievements.setSniper(achievements.getSniper());
//		myDataCommunityAccountAchievements.setSupporter(achievements.getSupporter());
//		myDataCommunityAccountAchievements.setTankExpert(achievements.getTankExpert());
//		myDataCommunityAccountAchievements.setTitleSniper(achievements.getTitleSniper());
//		myDataCommunityAccountAchievements.setWarrior(achievements.getWarrior());
//		
//		return myDataCommunityAccountAchievements;
//	}
//
	
	
	
//	private static DaoDataCommunityAccountStats TransformDataCommunityAccountStatsToDaoDataCommunityAccountStats(DataPlayerInfos stats) {
//		DaoDataCommunityAccountStats myDaoDataCommunityAccountStats =  new DaoDataCommunityAccountStats();
//		
//		myDaoDataCommunityAccountStats.setBattle_avg_performance(stats.getBattle_avg_performance());
//		myDaoDataCommunityAccountStats.setBattle_avg_xp(stats.getBattle_avg_xp());
//		myDaoDataCommunityAccountStats.setBattle_wins(stats.getBattle_wins());
//		myDaoDataCommunityAccountStats.setBattles(stats.getBattles());
//		myDaoDataCommunityAccountStats.setCtf_points(stats.getCtf_points());
//		myDaoDataCommunityAccountStats.setDamage_dealt(stats.getDamage_dealt());
//		myDaoDataCommunityAccountStats.setDropped_ctf_points(stats.getDropped_ctf_points());
//		myDaoDataCommunityAccountStats.setFrags(stats.getFrags());
//		myDaoDataCommunityAccountStats.setIntegrated_rating(stats.getIntegrated_rating());
//		myDaoDataCommunityAccountStats.setSpotted(stats.getSpotted());
//		myDaoDataCommunityAccountStats.setXp(stats.getXp());
//		
//		
//		
//		return myDaoDataCommunityAccountStats;
//	}

//	private static DaoDataCommunityAccountAchievements TransformDataCommunityAccountAchievementsToDaoDataCommunityAccountAchievements(DataCommunityAccountAchievements achievements) {
//		// TODO Auto-generated method stub
//		DaoDataCommunityAccountAchievements myDaoDataCommunityAccountAchievements = new DaoDataCommunityAccountAchievements();
//		myDaoDataCommunityAccountAchievements.setBeasthunter(achievements.getBeasthunter());
//		myDaoDataCommunityAccountAchievements.setDefender(achievements.getDefender());
//		myDaoDataCommunityAccountAchievements.setDiehard(achievements.getDiehard());
//		myDaoDataCommunityAccountAchievements.setInvader(achievements.getInvader());
//		myDaoDataCommunityAccountAchievements.setLumberjack(achievements.getLumberjack());
//		myDaoDataCommunityAccountAchievements.setMedalAbrams(achievements.getMedalAbrams());
//		myDaoDataCommunityAccountAchievements.setMedalBillotte(achievements.getMedalBillotte());
//		myDaoDataCommunityAccountAchievements.setMedalBurda(achievements.getMedalBurda());
//		myDaoDataCommunityAccountAchievements.setMedalCarius(achievements.getMedalCarius());
//		myDaoDataCommunityAccountAchievements.setMedalEkins(achievements.getMedalEkins());
//		myDaoDataCommunityAccountAchievements.setMedalFadin(achievements.getMedalFadin());
//		myDaoDataCommunityAccountAchievements.setMedalHalonen(achievements.getMedalHalonen());
//		myDaoDataCommunityAccountAchievements.setMedalKay(achievements.getMedalKay());
//		myDaoDataCommunityAccountAchievements.setMedalKnispel(achievements.getMedalKnispel());
//		myDaoDataCommunityAccountAchievements.setMedalKolobanov(achievements.getMedalKolobanov());
//		myDaoDataCommunityAccountAchievements.setMedalLavrinenko(achievements.getMedalLavrinenko());
//		myDaoDataCommunityAccountAchievements.setMedalLeClerc(achievements.getMedalLeClerc());
//		myDaoDataCommunityAccountAchievements.setMedalOrlik(achievements.getMedalOrlik());
//		myDaoDataCommunityAccountAchievements.setMedalOskin(achievements.getMedalOskin());
//		myDaoDataCommunityAccountAchievements.setMedalPoppel(achievements.getMedalPoppel() );
//		myDaoDataCommunityAccountAchievements.setMedalWittmann(achievements.getMedalWittmann());
//		myDaoDataCommunityAccountAchievements.setMousebane(achievements.getMousebane());
//		myDaoDataCommunityAccountAchievements.setRaider(achievements.getRaider());
//		myDaoDataCommunityAccountAchievements.setScout(achievements.getScout());
//		myDaoDataCommunityAccountAchievements.setSniper(achievements.getSniper());
//		myDaoDataCommunityAccountAchievements.setSupporter(achievements.getSupporter());
//		myDaoDataCommunityAccountAchievements.setTankExpert(achievements.getTankExpert());
//		myDaoDataCommunityAccountAchievements.setTitleSniper(achievements.getTitleSniper());
//		myDaoDataCommunityAccountAchievements.setWarrior(achievements.getWarrior());
//		
//		return myDaoDataCommunityAccountAchievements;
//	}

//	public static CommunityAccount TransformPlayerRatingsToCommunityAccount(PlayersInfos playerRatings) {
//		// TODO Auto-generated method stub
//		CommunityAccount myCommunityAccount = new CommunityAccount();
//		myCommunityAccount.setStatus( playerRatings.getStatus());
//		//myCommunityAccount.setStatus_code(daoAccount.getStatus_code());
//		myCommunityAccount.setIdUser(playerRatings.getIdUser());
//		//myCommunityAccount.setDateCommunityAccount(daoAccount.getDateCommunityAccount());
//		myCommunityAccount.setName(playerRatings.getName());
//		
//		//myCommunityAccount.setData(TransformDaoDataCommunityAccountToDataCommunityAccount(daoAccount.getData()));
//		
//		myCommunityAccount.setData(playerRatings.getData().get(playerRatings.getIdUser()));
//		return myCommunityAccount;
//	}

	public static List<CommunityAccount> TransformPlayersInfosToListCommunityAccount(PlayersInfos playerRatings) {
	/**
	 * {"status":"ok","count":2,"data":{ 
		"461":{ 
		"spotted":{ 
		"place":97640,"value":28440},"dropped_ctf_points":{ 
		"place":360995,"value":12639},"battle_avg_xp":{ 
		"place":94606,"value":650},"battles":{ 
		"place":288113,"value":18370},"damage_dealt":{ 
		"place":254542,"value":18500952},"frags":{ 
		"place":209141,"value":18488},"ctf_points":{ 
		"place":873803,"value":14755},"integrated_rating":{ 
		"place":173662,"value":18},"xp":{ 
		"place":147710,"value":11943200},"battle_avg_performance":{ 
		"place":295870,"value":54},"battle_wins":{ 
		"place":237633,"value":9886}},
		"462":{ 
		"spotted":{ 
		"place":165842,"value":24130},"dropped_ctf_points":{ 
		"place":364655,"value":12580},"battle_avg_xp":{ 
		"place":869330,"value":472},"battles":{ 
		"place":545346,"value":14467},"damage_dealt":{ 
		"place":475334,"value":13284143},"frags":{ 
		"place":347706,"value":14965},"ctf_points":{ 
		"place":186236,"value":29660},"integrated_rating":{ 
		"place":526707,"value":6},"xp":{ 
		"place":543957,"value":6829602},"battle_avg_performance":{ 
		"place":128047,"value":56},"battle_wins":{ 
		"place":420199,"value":8072}}}}
	 */
		List<CommunityAccount> myListCommunityAccount = new ArrayList<CommunityAccount>();
		
		Set<Entry<String, DataPlayerInfos>>  set = playerRatings.getData().entrySet();
		
		for(Entry<String, DataPlayerInfos> entry :set) {
			CommunityAccount myCommunityAccount = new CommunityAccount();
			myCommunityAccount.setIdUser(entry.getKey());
			myCommunityAccount.setData(entry.getValue());
			//
			//
			myListCommunityAccount.add(myCommunityAccount);
		}
		return myListCommunityAccount;
	}

	public static List<DataPlayerInfos> TransformPlayerInfosToListDataPlayerInfos(PlayersInfos playersInfos) {
		// TODO Auto-generated method stub
		
		List<DataPlayerInfos> listDataPlayerInfos = new ArrayList<DataPlayerInfos>();
		
		Collection<DataPlayerInfos> col = playersInfos.getData().values();
		
		listDataPlayerInfos.addAll(col);
		
		return listDataPlayerInfos;
	}
	
	
}
