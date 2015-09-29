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
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wot.server.api.TransformDtoObject;
import com.wot.shared.AllCommunityAccount;
import com.wot.shared.AllStatistics;
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
	static TankEncyclopedia tankEncyclopedia;
	static WnEfficientyTank wnEfficientyTank ;
	static HashMap<String, DataWnEfficientyTank> hMapWnEfficientyTankHashMap = new HashMap<String, DataWnEfficientyTank>();
	
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
			List<String> listIdUser = new ArrayList<String>();
			
			try {
				///Recuperation de l'encyclop�die des tanks ( n�cessaire pour connaitre le level de chaque char )  (pour calcul average level) 
				//=======================
				generateTankEncyclopedia();
				

				//-----------recup�ration des stats moyennes par TANK sur Noobmeter ---------
				// n�cessaire pour le calcul du wn8
				// url : http://www.wnefficiency.net/exp/expected_tank_values_latest.json
				// retour de l'URL
				/*
				 * {"header":{"version":14},
				 * "data":[{"IDNum":"3089","expFrag":"2.11","expDamage":"278.00","expSpot":"2.35","expDef":"1.84","expWinRate":"59.54"},
				 * {"IDNum":"3329","expFrag":"2.10","expDamage":"270.00","expSpot":"1.55","expDef":"1.81","expWinRate":"60.46"},
				 * {"IDNum":"577","expFrag":"2.01","expDamage":"268.00","expSpot":"2.12","expDef":"2.17","expWinRate":"60.24"},
				 * {"IDNum":"1329","expFrag":"2.05","expDamage":"274.00","expSpot":"1.51","expDef":"2.10","expWinRate":"60.00"},
				 */
				generateWnEfficientyTank();

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
				//http://api.worldoftanks.ru/2.0/account/ratings/?application_id=171745d21f7f98fd8878771da1000a31&account_id=506486576
				//nouvelle URL car ancienne obsolète 
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
				//log.warning(url + " --> " + AllLinesUser.substring(0, 400)); 
				
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
				//log.warning(url + " --> " + AllLinesUser.substring(0, 50)); 
				
				
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
					
						//Transform playerRatings en communityAccount (pour utiliser des types compatibles avec la s�rialisation (pas de MAP !!))
						//communityAccount =  TransformDtoObject.TransformPlayerRatingsToCommunityAccount(playerRatings);
						
						//make some calculation of stats 
						//calcul average level 
						//log.warning("communityAccount.getIdUser() " + dataPlayerInfos.getAccount_id());
						List<DataPlayerTankRatings> listPlayerTanksRatings = mapDataPlayerTankRatings.get(String.valueOf(dataPlayerInfos.getAccount_id()));
						
						if(listPlayerTanksRatings == null) 
							continue ;
						
						//calcul du tier moyen
						Double nbBattles = 0.0;
						Double levelByBattles = 0.0 ; 
						Double averageLevelTank =0.0;
						//frag, dmg,  spot def, xp, wr
						double totalExpFrag = 0;
						double totalExpDmg = 0;
						double totalExpSpot = 0;
						double totalExpDef = 0;
						//int totalExpXp = 0;
						double totalExpWr = 0;
						//
						AllStatistics allStatistics = dataPlayerInfos.getStatistics().getAllStatistics();
						
						int ActualFrag = allStatistics.getFrags();
						
						int ActualDmg = allStatistics.getDamage_dealt() ;
						int ActualSpot = allStatistics.getSpotted() ;
						int ActualDef = allStatistics.getDropped_capture_points() ;
						double ActualWr = Double.valueOf(allStatistics.getWins())/ Double.valueOf(allStatistics.getBattles());
						//
						double rFrag = 0 ;
						double rDmg = 0 ;
						double rSpot = 0 ;
						double rDef = 0 ;
						double rWr = 0;
						//
						double  rFragC = 0 ;
						double rDmgC = 0 ;
						double rSpotC = 0 ;
						double rDefC = 0 ;
						double rWrC = 0;
						//
						double wn8 = 0;
						//---calculate WN8 -----
						for (DataPlayerTankRatings dataPlayerTankRatings : listPlayerTanksRatings) {
							try {
								int tankId= dataPlayerTankRatings.getTank_id();
								int tankBattles =0 ;
								
									tankBattles = dataPlayerTankRatings.getStatistics().getBattles();
								
								//int wins = dataPlayerTankRatings.getStatistics().getAll().getWins();
								//
								//log.warning("tankId :" + tankId );
								if (tankEncyclopedia.getData().get(String.valueOf(tankId)) == null ) {
									log.severe ("tankEncyclopedia.getData().get(tankId) is null ");
									continue;
								}
								else {
									int levelTank = tankEncyclopedia.getData().get(String.valueOf(tankId)).getLevel();
									//
									nbBattles = nbBattles + tankBattles;
									levelByBattles =levelByBattles + levelTank * tankBattles;
								}
		
								//for each tank do sum of frag, dmg,  spot def, xp, wr
								//In wnEfficientyTank we have the expected values for each tank 
								//
								//						for (DataWnEfficientyTank dataWnEfficientyTank : wnEfficientyTank.getData()) {
								//							//dataWnEfficientyTank.
								//							
								//						}
								//for each tank do the sum of frag, dmg,  spot def, xp, wr
								DataWnEfficientyTank dataWnEfficientyTank = hMapWnEfficientyTankHashMap.get(String.valueOf(tankId));
								if (dataWnEfficientyTank != null) {
									// takes the counts of tanks played on account, and multiplies them by the expected stats to get the account total expected values.
									totalExpFrag = totalExpFrag + Double.valueOf(dataWnEfficientyTank.getExpFrag()) * tankBattles;
									totalExpDmg = totalExpDmg + Double.valueOf(dataWnEfficientyTank.getExpDamage()) * tankBattles;
									totalExpSpot = totalExpSpot + Double.valueOf(dataWnEfficientyTank.getExpSpot()) * tankBattles;
									totalExpDef = totalExpDef + Double.valueOf(dataWnEfficientyTank.getExpDef()) * tankBattles;
									totalExpWr = totalExpWr + Double.valueOf(dataWnEfficientyTank.getExpWinRate()) * tankBattles;
								}
							
							
							
								//
								/*
								Very Bad	below 500	below 300
								Bad	500 - 699	300 - 599
								Below Average	700 - 899	600 - 899
								Average	900 - 1099	900 - 1249
								Good	1100 - 1349	1250 - 1599
								Very Good	1350 - 1499	1600 - 1899
								Great	1500 - 1699	1900 - 2349
								Unicum	1700 - 1999	2350 - 2899
								Super Unicum	2000 and above	2900 and above
								*/
								
							}catch (Exception e) {
								break ;
							}	
								
						}//for 
						
						//Then the actual account totals (your total dmg, frags, spots, def, win-rate) are divided by the total expected values to give the ratios.
						rFrag = ActualFrag /totalExpFrag ;
						rDmg = ActualDmg /totalExpDmg ;
						rSpot = ActualSpot / totalExpSpot;
						rDef = ActualDef / totalExpDef;
						rWr = ActualWr*100*allStatistics.getBattles() / totalExpWr;//0.50 * 100 = 50 
						
						//Step 2 ---
						// sets the zero point for the ratios. See the assumptions section for more info on why this happen. 
						//min and max are functions to ensure the ratios stay within bounds. The constants are in the format of
						//(rSTAT � constant) / (1 � constant)
						/*
						 *	rWINc    = max(0,                     (rWIN    - 0.71) / (1 - 0.71) )
							rDAMAGEc = max(0,                     (rDAMAGE - 0.22) / (1 - 0.22) )
							rFRAGc   = max(0, min(rDAMAGEc + 0.2, (rFRAG   - 0.12) / (1 - 0.12)))
							rSPOTc   = max(0, min(rDAMAGEc + 0.1, (rSPOT   - 0.38) / (1 - 0.38)))
							rDEFc    = max(0, min(rDAMAGEc + 0.1, (rDEF    - 0.10) / (1 - 0.10)))
						 */
						
						rWrC    =   Math.max(0,                     (rWr    - 0.71) / (1 - 0.71) );
						rDmgC =  Math.max(0,                     (rDmg - 0.22) / (1 - 0.22) );
						rFragC   =  Math.max(0, Math.min(rDmgC + 0.2, (rFrag   - 0.12) / (1 - 0.12)));
						rSpotC   =  Math.max(0, Math.min(rDmgC + 0.1, (rSpot   - 0.38) / (1 - 0.38)));
						rDefC    =  Math.max(0, Math.min(rDmgC + 0.1, (rDef    - 0.10) / (1 - 0.10)));
						//
						//Step 3
						//WN8 = 980*rDAMAGEc + 210*rDAMAGEc*rFRAGc + 155*rFRAGc*rSPOTc + 75*rDEFc*rFRAGc + 145*MIN(1.8,rWINc)
						wn8 =   980*rDmgC    + 210*rDmgC*rFragC    + 155*rFragC*rSpotC + 75*rDefC*rFragC + 145*Math.min(1.8,rWrC);
						//
						//
						
						averageLevelTank = levelByBattles/nbBattles;
						//AllStatistics myDataCommunityAccountRatings = dataPlayerInfos.getStatistics().getAllStatistics();
						
						// set wn8 
						allStatistics.setWn8(wn8);
						
						//average level tank
						allStatistics.setAverageLevelTankCalc(averageLevelTank);
						//
						
						//== Ratio capture points calculated ================
						int ctfPoints = allStatistics.getCapture_points();
						Double ctfPointsCal = (double) ((double)ctfPoints/(double)allStatistics.getBattles());// 1,28 :1 = 1.28 
						
						//on ne conserve que 2 digits aprÃ¨s la virgule 
						//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
						int intCtfPointsCal = (int) (ctfPointsCal * 100); //ex intCtfPointsCal : 128,27 ctfPointsCal = 1.28
						
						ctfPointsCal = (double)intCtfPointsCal / 100 ; //ex ctfPointsCal : 1,28 intCtfPointsCal = 128
						allStatistics.setRatioCtfPoints(ctfPointsCal);
						//======================================================
						
						
						//== Ratio DMG points calculated ================
						int points = allStatistics.getDamage_dealt();
						Double pointsCal = (double) ((double)points/(double)allStatistics.getBattles());// 1,28 :1 = 1.28 
						
						//on ne conserve que 2 digits aprÃ¨s la virgule 
						//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
						int intPointsCal = (int) (pointsCal * 100); //ex intCtfPointsCal : 128,27 ctfPointsCal = 1.28
						
						pointsCal = (double)intPointsCal / 100 ; //ex ctfPointsCal : 1,28 intCtfPointsCal = 128
						allStatistics.setRatioDamagePoints(pointsCal);
						//======================================================
						

						//== Ratio Destroyed points calculated ================
						points = allStatistics.getFrags();
						pointsCal = (double) ((double)points/(double)allStatistics.getBattles());// 1,28 :1 = 1.28 
						
						//on ne conserve que 2 digits aprÃ¨s la virgule 
						//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
						intPointsCal = (int) (pointsCal * 100); //ex intCtfPointsCal : 128,27 ctfPointsCal = 1.28
						
						pointsCal = (double)intPointsCal / 100 ; //ex ctfPointsCal : 1,28 intCtfPointsCal = 128
						allStatistics.setRatioDestroyedPoints(pointsCal);
						//======================================================

						
						//== Ratio Detected points calculated ================
						points = allStatistics.getSpotted();
						pointsCal = (double) ((double)points/(double)allStatistics.getBattles());// 1,28 :1 = 1.28 
						
						//on ne conserve que 2 digits aprÃ¨s la virgule 
						//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
						intPointsCal = (int) (pointsCal * 100); //ex intCtfPointsCal : 128,27 ctfPointsCal = 1.28
						
						pointsCal = (double)intPointsCal / 100 ; //ex ctfPointsCal : 1,28 intCtfPointsCal = 128
						allStatistics.setRatioDetectedPoints(pointsCal);
						//======================================================

						
						//== Ratio Dropped capture points calculated ================
						points = allStatistics.getDropped_capture_points();
						pointsCal = (double) ((double)points/(double)allStatistics.getBattles());// 1,28 :1 = 1.28 
						
						//on ne conserve que 2 digits aprÃ¨s la virgule 
						//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
						intPointsCal = (int) (pointsCal * 100); //ex intCtfPointsCal : 128,27 ctfPointsCal = 1.28
						
						pointsCal = (double)intPointsCal / 100 ; //ex ctfPointsCal : 1,28 intCtfPointsCal = 128
						allStatistics.setRatioDroppedCtfPoints(pointsCal);
						//======================================================
						
						//=== Battle_avg_performance win/batttles ====
						//avg perf : ratio wins/battles 
						Double perf =  new Double(allStatistics.getWins())/ new Double(allStatistics.getBattles());
						allStatistics.setBattle_avg_performanceCalc(perf);
						//=============================================
						
						if (true){
							//pm = PMF.get().getPersistenceManager();
					        try {
					        	
					        	
					        	//must transform before persist the objet
					        	pm.currentTransaction().begin();
					        	DaoCommunityAccount2 daoCommunityAccount2 = TransformDtoObject.TransformCommunityAccountToDaoCommunityAccount(dataPlayerInfos);
					        	
					        	//pour eviter trop de donn�es en base 60 write OP 
					        	//daoCommunityAccount.getData().setAchievements(null);
					        	daoCommunityAccount2.setDateCommunityAccount(date);
					        	
					        	//
					        	pm.makePersistent(daoCommunityAccount2);
					        	pm.currentTransaction().commit();
					        	//log.warning("vehicules daoCommunityAccount " + daoCommunityAccount.getData().statsVehicules.get(0).getName() + ":"+  daoCommunityAccount.getData().statsVehicules.get(0).getBattle_count() + ":"+  daoCommunityAccount.getData().statsVehicules.get(0).getWin_count());
					        	listUsersPersisted.add(String.valueOf(dataPlayerInfos.getAccount_id()));
					        	
					        }
						    catch(Exception e){
						    	e.printStackTrace();
						    	log.log(Level.SEVERE, "Exception while saving daoCommunityAccount", e);
					        	pm.currentTransaction().rollback();
					        }
					        
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
		
		
		//construction de la liste des joueurs partis ou arrivés dans le clan
		//on requête le dernier <DaoCommunityClan2> et on le compare avec le courant (pas encore sauvé) 
		//pour constituer une liste des joueurs added et une autre deleted que l'on sauve dans DaoCommunityClan2 courant
		PersistenceManager pm = null;
		pm = PMF.get().getPersistenceManager();
        try {
			Query query = pm.newQuery(DaoCommunityClan2.class);
		    query.setFilter("idClan == nameParam");
		    //query.setOrdering("name desc");
		    query.setOrdering("dateCommunityClan desc"); //recup de la  derniere compo du CLAN  (J-1) 
		    query.setRange(0, 1); //only 1 results 
		    //query.setOrdering("hireDate desc");
		    query.declareParameters("String nameParam");
		    List<DaoCommunityClan2> resultsTmp = (List<DaoCommunityClan2>) query.execute(idClan);
	
	    	Map<String, String>  mapPrevDaoMembers = new HashMap<String, String>();; 
	    	Map<String, String>  mapCurrentDaoMembers = new HashMap<String, String>(); 

		  //recup des membres du j-1
		    if(resultsTmp.size() >= 1  )
		    {       
		    	DaoCommunityClan2 myPrevDaoCommunityClan = resultsTmp.get(0);
		    	
		    	//construction de la hashMap des id et name du ser
				CommunityClan prevCommunityClan = TransformDtoObject.TransformCommunityDaoCommunityClanToCommunityClan(myPrevDaoCommunityClan);
				if (prevCommunityClan != null) {

					DataCommunityClan myDataCommunityClan = prevCommunityClan.getData();
					List<DataCommunityClanMembers> listClanMembers = myDataCommunityClan.getMembers();

					for (DataCommunityClanMembers dataClanMember : listClanMembers) {
						for (DataCommunityMembers member : dataClanMember.getMembers()) {
							//log.warning("previous member " + member.getAccount_name() + " " + member.getAccount_id() );
							String idUser = member.getAccount_id();
							//log.warning("Previous User " + member.getAccount_name());
							mapPrevDaoMembers.put(idUser, member.getAccount_name());
						}
					}//for (DataCommunityClanMembers
				} else {

					log.severe("Erreur de parse sur myPrevDaoCommunityClan");
				}
		    	
		    	//recup des membres courant 
		    	if (mapPrevDaoMembers != null && mapPrevDaoMembers.size() > 0 ) {
					CommunityClan currentCommunityClan = TransformDtoObject.TransformCommunityDaoCommunityClanToCommunityClan(daoCommunityClan);

					if (currentCommunityClan != null) {

						DataCommunityClan myDataCommunityClan = currentCommunityClan.getData();
						List<DataCommunityClanMembers> listClanMembers = myDataCommunityClan.getMembers();

						for (DataCommunityClanMembers dataClanMember : listClanMembers) {
							for (DataCommunityMembers member : dataClanMember.getMembers()) {
								//log.warning("member " + member.getAccount_name() + " " + member.getAccount_id() );
								String idUser = member.getAccount_id();
								//log.warning("Previous User " + member.getAccount_name());
								mapCurrentDaoMembers.put(idUser, member.getAccount_name());
							}
						}//for (DataCommunityClanMembers
					} else {

						log.severe("Erreur de parse sur currentCommunityClan");
					}
		    	}
		    	
		    	//comparaison des 2 liste de users
		    	 Set<Entry<String, String>>  entryCurrentDaoMembers =  mapCurrentDaoMembers.entrySet();
		    	 Set<Entry<String, String>>  entryPrevDaoMembers =  mapPrevDaoMembers.entrySet();
		    	 
		    	 String userAdded = "";
		    	 String userDeleted = "";
		    	 
		    	 for(Entry<String, String> entryCurrentDaoMember : entryCurrentDaoMembers) {
		    		 
		    		 if (!mapPrevDaoMembers.containsKey(entryCurrentDaoMember.getKey())  ) {
		    			 //joueur nouveau ds Clan 
			    		 //log.warning("joueur ajouté " + entryCurrentDaoMember.getValue());
			    		 userAdded = userAdded + " " + entryCurrentDaoMember.getValue();

		    		 }else {
		    			 //log.warning("joueur deja la  " + entryCurrentDaoMember.getValue());
		    		 }
		    		 
		    	 }
		    	 
		    	 for(Entry<String, String> entryPrevDaoMember : entryPrevDaoMembers) {
		    		 
		    		 if (!mapCurrentDaoMembers.containsKey(entryPrevDaoMember.getKey())) {
		    			 //Joueur parti du clan 
		    			 //log.warning("joueur parti du clan " + entryPrevDaoMember.getValue());
		    			 userDeleted = userDeleted + " " + entryPrevDaoMember.getValue();
		    		 }else {
		    			 //log.warning("joueur tjrs la " + entryPrevDaoMember.getValue());
		    		 }
		    		 
		    	 }
		    	 daoCommunityClan.setUserAdded(userAdded);
		    	 daoCommunityClan.setUserDeleted(userDeleted);
		    	
		    }
		    
        }
	    catch(Exception e){
	    	e.printStackTrace();
	    	log.log(Level.SEVERE, "Exception while saving daoCommunityClan", e);
        	pm.currentTransaction().rollback();
        }
		
				
		//On persist le clan et ses joueurs pour trouver ceux qui partent et qui arrivent 
		//pm = PMF.get().getPersistenceManager();
		pm =null;
		pm = PMF.get().getPersistenceManager();
        try {
        	pm.currentTransaction().begin();
        	pm.makePersistent(daoCommunityClan);
        	pm.currentTransaction().commit();
        }
	    catch(Exception e){
	    	e.printStackTrace();
	    	log.log(Level.SEVERE, "Exception while saving daoCommunityClan", e);
        	pm.currentTransaction().rollback();
        }
	        

		
	
		
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
	


}
