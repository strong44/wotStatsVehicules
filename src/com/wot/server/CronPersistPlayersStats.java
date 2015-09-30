package com.wot.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wot.server.api.TransformDtoObject;
import com.wot.shared.AllCommunityAccount;
import com.wot.shared.CommunityAccount;
import com.wot.shared.CommunityClan;
import com.wot.shared.DataCommunityClan;
import com.wot.shared.DataCommunityClanMembers;
import com.wot.shared.DataCommunityMembers;
import com.wot.shared.DataPlayerInfos;
import com.wot.shared.DataPlayerTankRatings;
import com.wot.shared.DataTankEncyclopedia;
import com.wot.shared.DataWnEfficientyTank;
import com.wot.shared.PlayerTankRatings;
import com.wot.shared.PlayersInfos;
import com.wot.shared.TankEncyclopedia;
import com.wot.shared.WnEfficientyTank;

@SuppressWarnings("serial")
public class CronPersistPlayersStats extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(WotServiceImpl.class.getName());
	static List<String> listUsersPersisted = new ArrayList<String>();
	
	public static TankEncyclopedia tankEncyclopedia;
	public static WnEfficientyTank wnEfficientyTank ;
	public static HashMap<String, DataWnEfficientyTank> hMapWnEfficientyTankHashMap = new HashMap<String, DataWnEfficientyTank>();
	
	//static String lieu = "boulot"; //boulot ou maison si boulot -> pedro proxy 
	
	private static String applicationIdEU = "d0a293dc77667c9328783d489c8cef73";
	private static String urlServerEU =  "http://api.worldoftanks.eu";

	
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	log.warning("========lancement doGet  CronPersistPlayersStats ============== " );
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, CronPersistPlayersStats ");
        String clanId = req.getParameter("clanId");
        if(clanId != null && !"".equalsIgnoreCase(clanId)) {
        	cronPersistAllStats( new Date(), clanId);
		}else {
			log.severe("ERROR: =======lancement CronPersistPlayersStats  with idClan null ===");
		}
    }

	public static List<String> cronPersistAllStats(Date date, String idClan) {
			
			log.warning("========lancement cronPersistAllStats : " +  date + ":" + idClan + " :============== " );
			
			List<CommunityAccount> listCommunityAccount = new ArrayList<CommunityAccount>();
			AllCommunityAccount myAllCommunityAccount = new AllCommunityAccount ();
			myAllCommunityAccount.setListCommunityAccount(listCommunityAccount);
			PersistenceManager pm =null;
			pm = PMF.get().getPersistenceManager();
			
			
			try {

				//construction de la liste des id des joueurs du clan (s�parateur la ,)  
				//String AllIdUser = generateAllIdUsers(idClan, date);
				
				//Tous les joueurs du clan sont stockés dans cette hashMap <hMIdUser> : id joueur / nom joueur 
				HashMap<String , String>  hMIdUser = generateHMAllIdUsers(idClan, date);

				String AllIdUser ="";
				for(String idUser :hMIdUser.keySet()) {
					if ("".equalsIgnoreCase(AllIdUser)) 
						AllIdUser = idUser;
					else
						AllIdUser = AllIdUser + "," + idUser;
				}

				
				
				//=== recup des stats des joueurs ==========
				URL url = null ;
				String urlServer = urlServerEU +"/wot/account/info/?application_id=" + applicationIdEU + "&account_id=";
				// https://api.worldoftanks.eu/wot/account/info/?application_id=d0a293dc77667c9328783d489c8cef73&account_id=506486576
				
				if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
					url = new URL(WotServiceImpl.proxy + urlServer + AllIdUser);
				}
				else {
					url = new URL(urlServer + AllIdUser);
				}
				
				HttpURLConnection conn2 = (HttpURLConnection)url.openConnection();
				conn2.setReadTimeout(20000);
				conn2.setConnectTimeout(20000);
				conn2.getInputStream();
				BufferedReader readerUser = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
	
				//BufferedReader readerUser = new BufferedReader(new InputStreamReader(url.openStream()));
				String lineUser = "";
				;
				String AllLinesUser = "";
	
				while ((lineUser = readerUser.readLine()) != null) {
					AllLinesUser = AllLinesUser + lineUser;
				}
				
				readerUser.close();
				Gson gsonUser = new Gson();
				PlayersInfos playersInfos = gsonUser.fromJson(AllLinesUser, PlayersInfos.class);
				
				//Transform playerRatings en communityAccount (pour utiliser des types compatibles avec la s�rialisation (pas de MAP !!))
				List<DataPlayerInfos> listPlayerInfos =  TransformDtoObject.TransformPlayerInfosToListDataPlayerInfos(playersInfos);
				
				////////////////////
				//recup des stats de batailles par tank et par joueur (pour calcul average level) -- strong44  -- gexman47 
				//"http://api.worldoftanks.eu/2.0/account/tanks/?application_id=d0a293dc77667c9328783d489c8cef73&account_id=506486576,506763437";
				urlServer = urlServerEU +"/2.0/account/tanks/?application_id=" + applicationIdEU + "&account_id=";
				if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
					url = new URL(WotServiceImpl.proxy + urlServer + AllIdUser);
				}
				else {
					url = new URL(urlServer + AllIdUser);
				}
				//
				conn2 = (HttpURLConnection)url.openConnection();
				conn2.setReadTimeout(20000);
				conn2.setConnectTimeout(20000);
				conn2.getInputStream();
				readerUser = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				lineUser = "";
				AllLinesUser = "";
				while ((lineUser = readerUser.readLine()) != null) {
					AllLinesUser = AllLinesUser + lineUser;
				}
				
				
				readerUser.close();
				gsonUser = new Gson();
				PlayerTankRatings playerTankRatings = gsonUser.fromJson(AllLinesUser, PlayerTankRatings.class);
				
				//pb mapDataPlayerTankRatings is null !!!!!!
				Map<String,List<DataPlayerTankRatings>> mapDataPlayerTankRatings = playerTankRatings.getData();
				
				if(mapDataPlayerTankRatings != null )
					log.warning("playerTankRatings.getData() done mapDataPlayerTankRatings is good");
				else 
					log.warning("playerTankRatings.getData() done  mapDataPlayerTankRatings is null !!!");
				
				/////////////////////
				for(DataPlayerInfos dataPlayerInfos : listPlayerInfos) {
						List<DataPlayerTankRatings> listPlayerTanksRatings = mapDataPlayerTankRatings.get(String.valueOf(dataPlayerInfos.getAccount_id()));
						
						if(listPlayerTanksRatings == null) 
							continue ;
						
				        try {
				        	
				        	
				        	//must transform before persist the objet
				        	pm.currentTransaction().begin();
				        	DaoCommunityAccount2 daoCommunityAccount2 = TransformDtoObject.TransformCommunityAccountToDaoCommunityAccount(dataPlayerInfos);
				        	
				        	daoCommunityAccount2.setDateCommunityAccount(date);
				        	
				        	//-Transformer list<DataPlayerTankRatings> contenant des DataPlayerTankRatingsStatistics (retour json avec les stats (Win count , battle...)  char par char du joueur )
				        	//- en un objet sauvegardable en base -> cad  en list<DaoDataCommunityAccountStatsVehicules>
				        	//
				        	 List<DaoDataCommunityAccountStatsVehicules> listDaoDataCommunityAccountStatsVehicules = 
				        			 TransformDtoObject.TransformDataCommunityAccountStatsVehiculesToDaoDataCommunityAccountStatsVehicules(listPlayerTanksRatings);
				        	 daoCommunityAccount2.getData().setStatsVehicules(listDaoDataCommunityAccountStatsVehicules);
				        	 
				        	 pm.makePersistent(daoCommunityAccount2);
				        	 pm.currentTransaction().commit();
				        	 //
				        	 listUsersPersisted.add(String.valueOf(dataPlayerInfos.getAccount_id()));
				        	
				        }
					    catch(Exception e){
					    	e.printStackTrace();
					    	log.log(Level.SEVERE, "Exception while saving daoCommunityAccount", e);
				        	pm.currentTransaction().rollback();
				        }
					        
						
						
						
				}
			} catch (MalformedURLException e) {
				// ...
				log.throwing("Persist stats", "", e);
				log.severe("MalformedURLException " + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				// ...
				log.throwing("Persist stats", "", e);
				log.severe("IOException " + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (Exception e) {
					// ...
				e.printStackTrace();
				log.throwing("Persist stats", "", e);
				log.severe("Exception " + e.getLocalizedMessage());
				 StackTraceElement[] stack = e.getStackTrace();
				 for (StackTraceElement st : stack) {
					 log.severe(st.getMethodName()+":"+st.getLineNumber());
					 
					 
				 }
				 
				//e.printStackTrace();
			}
			finally {
				if (pm != null)
					pm.close();
			}
		
			return listUsersPersisted;
		
		}

	/*
	 * Génere une hashMap id joueur/nom du joueur
	 */
	public static String generateAllIdUsers(String idClan, Date date) throws IOException {
		List<String> listIdUser = new ArrayList<String>();
		////-- membres du clan 
		URL urlClan = null ;
		// recup des membres du clan NVS
		urlClan = null ;
			
		if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
			urlClan = new URL(WotServiceImpl.proxy + "http://api.worldoftanks.eu/wgn/clans/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id="+idClan);				
		}
		else {
			//500006074
			urlClan = new URL("http://api.worldoftanks.eu/wgn/clans/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id="+idClan);
		}
		
		
		HttpURLConnection conn = (HttpURLConnection)urlClan.openConnection();
		conn.setReadTimeout(60000);
		conn.setConnectTimeout(60000);
		conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line = "";
		String AllLines = "";
	
		while ((line = reader.readLine()) != null) {
			AllLines = AllLines + line;
		}
		reader.close();
		Gson gson = new Gson();
		DaoCommunityClan2 daoCommunityClan = gson.fromJson(AllLines, DaoCommunityClan2.class);
		
		daoCommunityClan.setIdClan(idClan);
		daoCommunityClan.setDateCommunityClan(date);
		//persist clan ?
		
		CommunityClan communityClan = TransformDtoObject.TransformCommunityDaoCommunityClanToCommunityClan(daoCommunityClan);
		if (communityClan != null) {
	
			DataCommunityClan myDataCommunityClan = communityClan.getData();
			List<DataCommunityClanMembers> listClanMembers = myDataCommunityClan.getMembers();
	
			for (DataCommunityClanMembers dataClanMember : listClanMembers) {
				for (DataCommunityMembers member : dataClanMember.getMembers()) {
					//log.warning("membermember " + member.getAccount_name() + " " + member.getAccount_id() );
					String idUser = member.getAccount_id();
					//log.warning("treatUser " + treatUser);
					listIdUser.add(idUser);
				}
	
			}//for (DataCommunityClanMembers
		} else {
	
			log.severe("Erreur de parse");
		}
		////
		String AllIdUser ="";
		for(String idUser :listIdUser) {
			if ("".equalsIgnoreCase(AllIdUser)) 
				AllIdUser = idUser;
			else
				AllIdUser = AllIdUser + "," + idUser;
				
		}
		
		return AllIdUser;
	}

	/*
	 * Génere une hashMap id joueur/nom du joueur , persist le clan et ses joueurs
	 */
	public static HashMap<String, String> generateHMAllIdUsers(String idClan, Date date) throws IOException {
		HashMap<String, String> hMidUser = new HashMap<String, String>();
		////-- membres du clan 
		URL urlClan = null ;
		// recup des membres du clan NVS
		urlClan = null ;
			
		if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
			urlClan = new URL(WotServiceImpl.proxy + "http://api.worldoftanks.eu/wgn/clans/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id="+idClan);				
		}
		else {
			//500006074
			urlClan = new URL("http://api.worldoftanks.eu/wgn/clans/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id="+idClan);
		}
		
		
		HttpURLConnection conn = (HttpURLConnection)urlClan.openConnection();
		conn.setReadTimeout(60000);
		conn.setConnectTimeout(60000);
		conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line = "";
		String AllLines = "";

		while ((line = reader.readLine()) != null) {
			AllLines = AllLines + line;
		}
		reader.close();
		Gson gson = new Gson();
		DaoCommunityClan2 daoCommunityClan = gson.fromJson(AllLines, DaoCommunityClan2.class);
		
		daoCommunityClan.setIdClan(idClan);
		daoCommunityClan.setDateCommunityClan(date);
		
		
		//construction de la hashMap des id et name du ser
		CommunityClan communityClan = TransformDtoObject.TransformCommunityDaoCommunityClanToCommunityClan(daoCommunityClan);
		if (communityClan != null) {

			DataCommunityClan myDataCommunityClan = communityClan.getData();
			List<DataCommunityClanMembers> listClanMembers = myDataCommunityClan.getMembers();

			for (DataCommunityClanMembers dataClanMember : listClanMembers) {
				for (DataCommunityMembers member : dataClanMember.getMembers()) {
					//log.warning("membermember " + member.getAccount_name() + " " + member.getAccount_id() );
					String idUser = member.getAccount_id();
					//log.warning("treatUser " + treatUser);
					hMidUser.put(idUser, member.getAccount_name());
				}

			}//for (DataCommunityClanMembers
		} else {

			log.severe("Erreur de parse");
		}

		
		return hMidUser;
	}
	
//	public static void generateTankEncyclopedia() throws IOException {
//		///Recuperation de l'encyclop�die des tanks ( n�cessaire pour connaitre le level de chaque char )  (pour calcul average level) 
//		//=======================
//		if (tankEncyclopedia == null) {
//			//http://api.worldoftanks.eu/2.0/encyclopedia/tanks/?application_id=d0a293dc77667c9328783d489c8cef73
//			String urlServer = urlServerEU +"/2.0/encyclopedia/tanks/?application_id=" + applicationIdEU ;
//			URL url = null;
//			
//			if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
//				url = new URL(WotServiceImpl.proxy + urlServer );
//			}
//			else {
//				url = new URL(urlServer );
//			}
//			
//			
//			HttpURLConnection conn2 = (HttpURLConnection)url.openConnection();
//			conn2.setReadTimeout(20000);
//			conn2.setConnectTimeout(20000);
//			conn2.getInputStream();
//			BufferedReader readerUser = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
//
//			String lineUser = "";
//			String AllLinesUser = "";
//
//			while ((lineUser = readerUser.readLine()) != null) {
//				AllLinesUser = AllLinesUser + lineUser;
//			}
//			readerUser.close();
//
//			Gson gsonUser = new Gson();
//			tankEncyclopedia = gsonUser.fromJson(AllLinesUser, TankEncyclopedia.class);
//		}
//		
//		//contr�le --------
//		if (tankEncyclopedia == null) {
//			log.severe("tankEncyclopedia is null" );
//			
//		}
//		else {
//			log.warning("tankEncyclopedia is good" );
//			if (tankEncyclopedia.getData() ==null ) {
//				log.severe("tankEncyclopedia data is null" );
//			}
//			else {
//				 Set<Entry<String, DataTankEncyclopedia>>  set = tankEncyclopedia.getData().entrySet();
//				
//				if (tankEncyclopedia.getData().get("6417") == null ){
//					log.severe("tankEncyclopedia data get tank id 6417 is null" );
//				}
//			}
//					
//		}
//		
//	}
	
	public static void generateWnEfficientyTank() throws IOException {
		if (wnEfficientyTank == null) {
			URL urlWnEfficienty = null ;
			// recup des membres du clan NVS
			urlWnEfficienty = null ;
			
			if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
				urlWnEfficienty = new URL(WotServiceImpl.proxy + "http://www.wnefficiency.net/exp/expected_tank_values_latest.json");				
			}
			else {
				//500006074
				urlWnEfficienty = new URL("http://www.wnefficiency.net/exp/expected_tank_values_latest.json");
			}
			
			HttpURLConnection connWN = (HttpURLConnection)urlWnEfficienty.openConnection();
			connWN.setReadTimeout(60000);
			connWN.setConnectTimeout(60000);
			connWN.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connWN.getInputStream()));
			
			String line = "";
			String AllLines = "";
	
			while ((line = reader.readLine()) != null) {
				AllLines = AllLines + line;
			}
			reader.close();
			Gson gson = new Gson();
			wnEfficientyTank = gson.fromJson(AllLines, WnEfficientyTank.class);
			System.out.println("wnEfficientyTank" + wnEfficientyTank);
			
			//transform list to hashMap for easy treatement
			//HashMap<String, DataWnEfficientyTank> hMapWnEfficientyTankHashMap = new HashMap<String, DataWnEfficientyTank>();
			for (DataWnEfficientyTank dataWnEfficientyTank : wnEfficientyTank.getData()) {
				//dataWnEfficientyTank.
				hMapWnEfficientyTankHashMap.put(dataWnEfficientyTank.getIDNum(), dataWnEfficientyTank);
			}
		}
	}
	
	public static void generateTankEncyclopedia() throws IOException {
		///Recuperation de l'encyclop�die des tanks ( n�cessaire pour connaitre le level de chaque char )  (pour calcul average level) 
		//=======================
		if (tankEncyclopedia == null) {
			//http://api.worldoftanks.eu/2.0/encyclopedia/tanks/?application_id=d0a293dc77667c9328783d489c8cef73
			String urlServer = urlServerEU +"/2.0/encyclopedia/tanks/?application_id=" + applicationIdEU ;
			URL url = null;
			
			if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
				url = new URL(WotServiceImpl.proxy + urlServer );
			}
			else {
				url = new URL(urlServer );
			}
			
			
			HttpURLConnection conn2 = (HttpURLConnection)url.openConnection();
			conn2.setReadTimeout(20000);
			conn2.setConnectTimeout(20000);
			conn2.getInputStream();
			BufferedReader readerUser = new BufferedReader(new InputStreamReader(conn2.getInputStream()));

			String lineUser = "";
			String AllLinesUser = "";

			while ((lineUser = readerUser.readLine()) != null) {
				AllLinesUser = AllLinesUser + lineUser;
			}
			readerUser.close();

			Gson gsonUser = new Gson();
			tankEncyclopedia = gsonUser.fromJson(AllLinesUser, TankEncyclopedia.class);
		}
		
		//contr�le --------
		if (tankEncyclopedia == null) {
			log.severe("tankEncyclopedia is null" );
			
		}
		else {
			log.warning("tankEncyclopedia is good" );
			if (tankEncyclopedia.getData() ==null ) {
				log.severe("tankEncyclopedia data is null" );
			}
			else {
				 Set<Entry<String, DataTankEncyclopedia>>  set = tankEncyclopedia.getData().entrySet();
				
				if (tankEncyclopedia.getData().get("6417") == null ){
					log.severe("tankEncyclopedia data get tank id 6417 is null" );
				}
			}
					
		}
		
	}


}
