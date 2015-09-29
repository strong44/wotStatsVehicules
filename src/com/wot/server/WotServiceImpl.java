package com.wot.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.wot.client.WotService;
import com.wot.server.api.TransformDtoObject;
import com.wot.shared.AllCommunityAccount;
import com.wot.shared.Clan;
import com.wot.shared.CommunityAccount;
import com.wot.shared.CommunityClan;
import com.wot.shared.DataPlayerInfos;
import com.wot.shared.FieldVerifier;
import com.wot.shared.ObjectFactory;
import com.wot.shared.PlayersInfos;
import com.wot.shared.TankEncyclopedia;
import com.wot.shared.XmlDescription;
import com.wot.shared.XmlListAchievement;
import com.wot.shared.XmlListCategoryAchievement;
import com.wot.shared.XmlListSrcImg;
import com.wot.shared.XmlSrc;
import com.wot.shared.XmlWiki;

/**
 * The server side implementation of the RPC service.
 * https://github.com/thunder-spb/wot-api-description/blob/master/README.md
 */
@SuppressWarnings("serial")
public class WotServiceImpl extends RemoteServiceServlet implements WotService {
	static public String proxy =  "http://wotnvs.appspot.com/WotWeb?";
	
	static public String lieu = "maison"; //boulot ou maison si boulot -> WotWeb
	boolean saveData = true;
	private boolean saveDataPlayer = true;
	XmlWiki wiki =  null;
	
	private String applicationIdEU = "d0a293dc77667c9328783d489c8cef73";
	private String urlServerEU =  "http://api.worldoftanks.eu";
	//
	static int indexBeginUser = 0;
	static int indexEndUser = 10;
	static int pas = 10;
	static int indexMaxUser = 100;
	static List<String> listUsersPersisted = new ArrayList<String>();
	
	//Definition d'un hashMap id userName
	static HashMap <String , String> hMapIdUserName = new HashMap <String , String>();

	//Definition d'un hashMap id userName
	static HashMap <String , String> hMapUserNameId = new HashMap <String , String>();

	private static final Logger log = Logger.getLogger(WotServiceImpl.class.getName());
	
	static TankEncyclopedia tankEncyclopedia;
	
	@Override
	protected void checkPermutationStrongName() throws SecurityException {
		return;
	}
	
	@Override
	public List<String> persistStats(String input, int indexBegin, int indexEnd, List<String> listIdUser) throws IllegalArgumentException {
		//
		log.warning("========lancement persistStats ============== " + input);
		
		persistAllStats3( new Date(), listIdUser);
		//return resultAchievement;
		return listUsersPersisted;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public Clan getClans(String input, int offset) throws IllegalArgumentException {
		//////////
		log.warning("========lancement getClans ==============");
		ObjectFactory objFactory = null;
		try {
			JAXBContext context = JAXBContext.newInstance(XmlWiki.class);
			
//			//crÃ©ation WIKI
			if (wiki== null) {
				objFactory = new ObjectFactory();
			
				wiki = objFactory.createXmlWiki();

				//A partir du XML instanciÃ© les classes !!
				Unmarshaller unmarshaller = context.createUnmarshaller();
				wiki = (XmlWiki) unmarshaller.unmarshal(new File("wotWiki.xml"));
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>unmarshaller wotWiki.xml");
				log.info(wiki.getACHIEVEMENTS().getCATEGORYACHIEVEMENT().get(0).getNAME());
			}
			
		} catch (JAXBException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//////////////////////////////////////////////////////////////////
		Clan clan = null;
		//int offset = 0 ;
		int limit = 100;
		
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			log.info("arg not good : " + input);
			throw new IllegalArgumentException("Name must be at least 1 characters long");
		}
	
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
	
		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		String resultAchievement = "";
		
		try {
			long maxAchievement = 0;
			String maxAchievementuserName = "";
	
			//recup id clan avec son son nom 
			/**
			 * {
				  "status": "ok", 
				  "status_code": "NO_ERROR", 
				  "data": {
				    "items": [
				      {
				        "abbreviation": "NVS", 
				        "created_at": 1328978449.00, 
				        "name": "NOVA SNAIL", 
				        "member_count": 57, 
				        "owner": "hentz44", 
				        "motto": "Un escargot qui avale un obus a confiance en sa coquille.", 
				        "clan_emblem_url": "http://cw.worldoftanks.eu/media/clans/emblems/clans_5/500006074/emblem_64x64.png", 
				        "id": 500006074, 
				        "clan_color": "#4a426c"
				      }
				    ], 
				    "offset": 0, 
				    "filtered_count": 2
				  }
				}
				
				
				////////////////// NEW ///////////////////// 
				 * {
				  "status": "ok", 
				  "count": 48, 
				  "data": [
				    {
				      "members_count": 48, 
				      "name": "\"Ð‘ÐµÐ»Ð°Ñ� Ð³Ð²Ð°Ñ€Ð´Ð¸Ñ�\"", 
				      "created_at": 1335298122, 
				      "abbreviation": "WG1", 
				      "clan_id": 24810, 
				      "owner_id": 5095539
				    }, 
				    {
				      "members_count": 1, 
				      "name": "Cheerful coffin", 
				      "created_at": 1345383267, 
				      "abbreviation": "WGWWR", 
				      "clan_id": 35231, 
				      "owner_id": 5266248
				    }
				    ]
					}
				 * 
			 */
			URL urlClan = null ;
			input = input.replace(" ", "%20");
			if(lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
				urlClan = new URL(proxy + "http://api.worldoftanks.eu/wgn/clans/list/?application_id=d0a293dc77667c9328783d489c8cef73&search=" +  input);					
			}
			else {
				//NVS : 500006074
				//urlClan = new URL("http://api.worldoftanks.eu/community/clans/500006074/api/1.0/?source_token=WG-WoT_Assistant-1.3.2");
				//http://api.worldoftanks.eu/2.0/clan/list/?application_id=d0a293dc77667c9328783d489c8cef73&search=
				
				/* new from 9.10 */
				/* https://api.worldoftanks.eu/wgn/clans/list/?application_id=d0a293dc77667c9328783d489c8cef73&search=NOVA%20SNAIL */
				
				
				urlClan = new URL("http://api.worldoftanks.eu/wgn/clans/list/?application_id=d0a293dc77667c9328783d489c8cef73&search=" +  input );		
			}
			
			//lecture de la rÃ©ponse recherche du clan
			HttpURLConnection conn = (HttpURLConnection)urlClan.openConnection();
			conn.setReadTimeout(60000);
			conn.setConnectTimeout(60000);
			//conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			//BufferedReader reader = new BufferedReader(new InputStreamReader(urlClan.openStream(), "UTF-8"));
			String line = "";
			String AllLines = "";
	
			while ((line = reader.readLine()) != null) {
				AllLines = AllLines + line;
			}
			log.info(AllLines);
			reader.close();
	
			Gson gson = new Gson();
			//System.out.println("before " + AllLines);
			
			//parsing gson
			clan = gson.fromJson(AllLines, Clan.class );
			//ItemsDataClan  myItemsDataClan = null ;
			

	
		} catch (MalformedURLException e) {
			// ...
			e.printStackTrace();
		} catch (IOException e) {
			// ...
			e.printStackTrace();
		}
	
		//return resultAchievement;
		clan.setWiki(wiki);
		return clan;
	}
	
	/**
	 * getAllMembersClan : REcupÃ©re tous les menbres du clan
	 */
	@Override
	public CommunityClan getAllMembersClan(String idClan) {
		CommunityClan communityClan = null;
		
		DaoCommunityClan2 daoCommunityClan = null;
		Clan desClan =null;
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(idClan)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}
	
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
	
		// Escape data from the client to avoid cross-site script vulnerabilities.
		idClan = escapeHtml(idClan);
		userAgent = escapeHtml(userAgent);
		
		List<CommunityAccount> listCommunityAccount = new ArrayList<CommunityAccount>();
		AllCommunityAccount myAllCommunityAccount = new AllCommunityAccount ();
		myAllCommunityAccount.setListCommunityAccount(listCommunityAccount);
		/**
		 * {
		  "status": "ok", 
		  "count": 1, 
		  "data": {
		    "1": {
		      "members_count": 100, 
		      "description": "Ð—Ð°ÐºÑ€Ñ‹Ñ‚Ñ‹Ð¹ ÐºÐ»Ð°Ð½, Ð² Ñ�Ð¾Ñ�Ñ‚Ð°Ð² ÐºÐ¾Ñ‚Ð¾Ñ€Ð¾Ð³Ð¾ Ð²Ñ…Ð¾Ð´Ñ�Ñ‚ Ð»Ð¸ÑˆÑŒ Ñ€Ð°Ð·Ñ€Ð°Ð±Ð¾Ñ‚Ñ‡Ð¸ÐºÐ¸ Ð¸Ð³Ñ€Ñ‹ &quot;World of Tanks&quot;.\n\nÐ—Ð°Ñ�Ð²ÐºÐ¸, Ð¿Ð¾Ñ�Ð»Ð°Ð½Ð½Ñ‹Ðµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ð¸Ñ€Ñƒ ÐºÐ»Ð°Ð½Ð° Ñ‡ÐµÑ€ÐµÐ· Ñ„Ð¾Ñ€ÑƒÐ¼, Ð�Ð• Ð Ð�Ð¡Ð¡ÐœÐ�Ð¢Ð Ð˜Ð’Ð�Ð®Ð¢Ð¡Ð¯ .", 
		      "description_html": "<p>Ð—Ð°ÐºÑ€Ñ‹Ñ‚Ñ‹Ð¹ ÐºÐ»Ð°Ð½, Ð² Ñ�Ð¾Ñ�Ñ‚Ð°Ð² ÐºÐ¾Ñ‚Ð¾Ñ€Ð¾Ð³Ð¾ Ð²Ñ…Ð¾Ð´Ñ�Ñ‚ <i>Ð»Ð¸ÑˆÑŒ</i> Ñ€Ð°Ð·Ñ€Ð°Ð±Ð¾Ñ‚Ñ‡Ð¸ÐºÐ¸ Ð¸Ð³Ñ€Ñ‹ &quot;World of Tanks&quot;.\n</p><p>\n<br/>Ð—Ð°Ñ�Ð²ÐºÐ¸, Ð¿Ð¾Ñ�Ð»Ð°Ð½Ð½Ñ‹Ðµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ð¸Ñ€Ñƒ ÐºÐ»Ð°Ð½Ð° Ñ‡ÐµÑ€ÐµÐ· Ñ„Ð¾Ñ€ÑƒÐ¼, <i>Ð�Ð• Ð Ð�Ð¡Ð¡ÐœÐ�Ð¢Ð Ð˜Ð’Ð�Ð®Ð¢Ð¡Ð¯</i> .\n</p>", 
		      "created_at": 1293024672, 
		      "updated_at": 1375930001, 
		      "name": "Wargaming.net", 
		      "abbreviation": "WG", 
		      "emblems": {
		        "large": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_64x64.png", 
		        "small": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_24x24.png", 
		        "medium": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_32x32.png", 
		        "bw_tank": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_64x64_tank.png"
		      }, 
		      "clan_id": 1, 
		      "members": {
		        "196632": {
		          "created_at": 1293126248, 
		          "role": "private", 
		          "updated_at": 1375930001, 
		          "account_id": 196632, 
		          "account_name": "Wrobel"
		        }, 
		        "18458": {
		          "created_at": 1360836543, 
		          "role": "diplomat", 
		          "updated_at": 1375930001, 
		          "account_id": 18458, 
		          "account_name": "alienraven"
		        }, 
			      "motto": "ÐžÑ€Ð»Ñ‹! ÐžÑ€Ð»Ð¸Ñ†Ñ‹!", 
			      "clan_color": "#e18000", 
			      "owner_id": 1277137
			    }
			  }
			}
		 */
		try {
		
			URL urlClan = null ;
			// recup des membres du clan NVS
			urlClan = null ;
			if(lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
				/* http://api.worldoftanks.eu/2.0/clan/info/?application_id*/
				urlClan = new URL(proxy + "http://api.worldoftanks.eu/wgn/clans/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id=" + idClan );				
			}
			else {
				//500006074
				//http://api.worldoftanks.eu/2.0/clan/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id=500006074
				//urlClan = new URL("http://api.worldoftanks.eu/community/clans/" + idClan + "/api/1.0/?source_token=WG-WoT_Assistant-1.3.2");
				
				// change in 9.10 
				/* https://api.worldoftanks.eu/wgn/clans/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id=500006074 */
				urlClan = new URL("http://api.worldoftanks.eu/wgn/clans/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id=" + idClan );
			}
	
			HttpURLConnection conn = (HttpURLConnection)urlClan.openConnection();
			conn.setReadTimeout(60000);
			conn.setConnectTimeout(60000);
			conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			//BufferedReader reader = new BufferedReader(new InputStreamReader(urlClan.openStream()));
			String line = "";
			String AllLines = "";
	
			while ((line = reader.readLine()) != null) {
				AllLines = AllLines + line;
			}
			reader.close();
	
			Gson gson = new Gson();
			
			daoCommunityClan = gson.fromJson(AllLines, DaoCommunityClan2.class);
			daoCommunityClan.setIdClan(idClan);
			daoCommunityClan.setDateCommunityClan(new java.util.Date());
			//persist clan ?
			
			for (DaoDataCommunityClanMembers members : daoCommunityClan.getData().values() ) {
				for (DaoDataCommunityMembers member : members.getMembers() ) {
					hMapIdUserName.put(member.getAccount_id(), member.getAccount_name());
					hMapUserNameId.put( member.getAccount_name(), member.getAccount_id());
				}
 

			}
			
		} catch (MalformedURLException e) {
			// ...
			e.printStackTrace();
		} catch (IOException e) {
			// ...
			e.printStackTrace();
		}
		 ///
		communityClan = TransformDtoObject.TransformCommunityDaoCommunityClanToCommunityClan(daoCommunityClan);
		return communityClan;
	}


//	public void generateHMIdUSerNameClan(String idClan) {
//		CommunityClan communityClan = null;
//		
//		DaoCommunityClan2 daoCommunityClan = null;
//		Clan desClan =null;
//		// Verify that the input is valid.
//		if (!FieldVerifier.isValidName(idClan)) {
//			// If the input is not valid, throw an IllegalArgumentException back to
//			// the client.
//			throw new IllegalArgumentException("Name must be at least 4 characters long");
//		}
//	
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
//	
//		// Escape data from the client to avoid cross-site script vulnerabilities.
//		idClan = escapeHtml(idClan);
//		userAgent = escapeHtml(userAgent);
//		
//		List<CommunityAccount> listCommunityAccount = new ArrayList<CommunityAccount>();
//		AllCommunityAccount myAllCommunityAccount = new AllCommunityAccount ();
//		myAllCommunityAccount.setListCommunityAccount(listCommunityAccount);
//		/**
//		 * {
//		  "status": "ok", 
//		  "count": 1, 
//		  "data": {
//		    "1": {
//		      "members_count": 100, 
//		      "description": "Ð—Ð°ÐºÑ€Ñ‹Ñ‚Ñ‹Ð¹ ÐºÐ»Ð°Ð½, Ð² Ñ�Ð¾Ñ�Ñ‚Ð°Ð² ÐºÐ¾Ñ‚Ð¾Ñ€Ð¾Ð³Ð¾ Ð²Ñ…Ð¾Ð´Ñ�Ñ‚ Ð»Ð¸ÑˆÑŒ Ñ€Ð°Ð·Ñ€Ð°Ð±Ð¾Ñ‚Ñ‡Ð¸ÐºÐ¸ Ð¸Ð³Ñ€Ñ‹ &quot;World of Tanks&quot;.\n\nÐ—Ð°Ñ�Ð²ÐºÐ¸, Ð¿Ð¾Ñ�Ð»Ð°Ð½Ð½Ñ‹Ðµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ð¸Ñ€Ñƒ ÐºÐ»Ð°Ð½Ð° Ñ‡ÐµÑ€ÐµÐ· Ñ„Ð¾Ñ€ÑƒÐ¼, Ð�Ð• Ð Ð�Ð¡Ð¡ÐœÐ�Ð¢Ð Ð˜Ð’Ð�Ð®Ð¢Ð¡Ð¯ .", 
//		      "description_html": "<p>Ð—Ð°ÐºÑ€Ñ‹Ñ‚Ñ‹Ð¹ ÐºÐ»Ð°Ð½, Ð² Ñ�Ð¾Ñ�Ñ‚Ð°Ð² ÐºÐ¾Ñ‚Ð¾Ñ€Ð¾Ð³Ð¾ Ð²Ñ…Ð¾Ð´Ñ�Ñ‚ <i>Ð»Ð¸ÑˆÑŒ</i> Ñ€Ð°Ð·Ñ€Ð°Ð±Ð¾Ñ‚Ñ‡Ð¸ÐºÐ¸ Ð¸Ð³Ñ€Ñ‹ &quot;World of Tanks&quot;.\n</p><p>\n<br/>Ð—Ð°Ñ�Ð²ÐºÐ¸, Ð¿Ð¾Ñ�Ð»Ð°Ð½Ð½Ñ‹Ðµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ð¸Ñ€Ñƒ ÐºÐ»Ð°Ð½Ð° Ñ‡ÐµÑ€ÐµÐ· Ñ„Ð¾Ñ€ÑƒÐ¼, <i>Ð�Ð• Ð Ð�Ð¡Ð¡ÐœÐ�Ð¢Ð Ð˜Ð’Ð�Ð®Ð¢Ð¡Ð¯</i> .\n</p>", 
//		      "created_at": 1293024672, 
//		      "updated_at": 1375930001, 
//		      "name": "Wargaming.net", 
//		      "abbreviation": "WG", 
//		      "emblems": {
//		        "large": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_64x64.png", 
//		        "small": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_24x24.png", 
//		        "medium": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_32x32.png", 
//		        "bw_tank": "http://cw.worldoftanks.ru/media/clans/emblems/clans_1/1/emblem_64x64_tank.png"
//		      }, 
//		      "clan_id": 1, 
//		      "members": {
//		        "196632": {
//		          "created_at": 1293126248, 
//		          "role": "private", 
//		          "updated_at": 1375930001, 
//		          "account_id": 196632, 
//		          "account_name": "Wrobel"
//		        }, 
//		        "18458": {
//		          "created_at": 1360836543, 
//		          "role": "diplomat", 
//		          "updated_at": 1375930001, 
//		          "account_id": 18458, 
//		          "account_name": "alienraven"
//		        }, 
//			      "motto": "ÐžÑ€Ð»Ñ‹! ÐžÑ€Ð»Ð¸Ñ†Ñ‹!", 
//			      "clan_color": "#e18000", 
//			      "owner_id": 1277137
//			    }
//			  }
//			}
//		 */
//		try {
//		
//			URL urlClan = null ;
//			// recup des membres du clan NVS
//			urlClan = null ;
//			if(lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
//				urlClan = new URL(proxy + "http://api.worldoftanks.eu/2.0/clan/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id=" + idClan );				
//			}
//			else {
//				//500006074
//				//http://api.worldoftanks.eu/2.0/clan/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id=500006074
//				//urlClan = new URL("http://api.worldoftanks.eu/community/clans/" + idClan + "/api/1.0/?source_token=WG-WoT_Assistant-1.3.2");
//				urlClan = new URL("http://api.worldoftanks.eu/2.0/clan/info/?application_id=d0a293dc77667c9328783d489c8cef73&clan_id=" + idClan );
//			}
//	
//			HttpURLConnection conn = (HttpURLConnection)urlClan.openConnection();
//			conn.setReadTimeout(60000);
//			conn.setConnectTimeout(60000);
//			conn.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			
//			//BufferedReader reader = new BufferedReader(new InputStreamReader(urlClan.openStream()));
//			String line = "";
//			String AllLines = "";
//	
//			while ((line = reader.readLine()) != null) {
//				AllLines = AllLines + line;
//			}
//			reader.close();
//	
//			Gson gson = new Gson();
//			
//			daoCommunityClan = gson.fromJson(AllLines, DaoCommunityClan2.class);
//			daoCommunityClan.setIdClan(idClan);
//			daoCommunityClan.setDateCommunityClan(new java.util.Date());
//			//persist clan ?
//			
//			for (DaoDataCommunityClanMembers members : daoCommunityClan.getData().values() ) {
//				for (DaoDataCommunityMembers member : members.getMembers().values() ) {
//					hMapIdUserName.put(member.getAccount_id(), member.getAccount_name());
//					hMapUserNameId.put( member.getAccount_name(), member.getAccount_id());
//				}
// 
//
//			}
//			
//		} catch (MalformedURLException e) {
//			// ...
//			e.printStackTrace();
//		} catch (IOException e) {
//			// ...
//			e.printStackTrace();
//		}
//		 ///
//		//communityClan = TransformDtoObject.TransformCommunityDaoCommunityClanToCommunityClan(daoCommunityClan);
//		//return communityClan;
//	}

	
	

	@Override
	public AllCommunityAccount getAllMembersClanAndStatsHistorised(String idClan, List<String> listIdUser) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * parse le HTML du wiki achieveemnt pour en extraire les noms de mï¿½dailles , les src d'icï¿½nes et les descriptions
	 * @param AllLinesWot
	 * @param cat1Medal
	 * @param cat2Medal
	 * @param objFactory 
	 * @param wiki 
	 */
	void parseHtmlAchievement (String AllLinesWot, String cat1Medal, String cat2Medal, ObjectFactory objFactory, XmlWiki wiki) {
	
		//crÃ©ation category achievement
		XmlListCategoryAchievement myXmlListCategoryAchievement = objFactory.createXmlListCategoryAchievement();
		myXmlListCategoryAchievement.setNAME(cat1Medal);
		
		//crÃ©ation xlmDescrition category achievement
		XmlDescription myXmlDescription= objFactory.createXmlDescription();
		myXmlDescription.setVALUE("Desccription de la ctÃ©gorie de mÃ©dailles");
		myXmlListCategoryAchievement.setDESCRIPTION(myXmlDescription);
		
		//Ajouter la catÃ©gorie achievement au wiki
		wiki.getACHIEVEMENTS().getCATEGORYACHIEVEMENT().add(myXmlListCategoryAchievement);
		
		//parse WIKI HTML
		int pos1 = AllLinesWot.indexOf(cat1Medal+"</span></div>" );
		int pos2 = -1; 
		if ("printfooter".equalsIgnoreCase(cat2Medal))
			pos2 = AllLinesWot.indexOf(cat2Medal );
		else
			pos2 = AllLinesWot.indexOf(cat2Medal+"</span></div>" );
		
		
		//selon les catï¿½gories de mï¿½dailles, on doit aller rechercher la 2ï¿½me pour la 1ï¿½re ocurence du nom de la catï¿½gorie de mï¿½dailles
		//donc on prend toujours la derniï¿½re 
		
		if (pos1 == -1)
			pos1 = AllLinesWot.indexOf(cat1Medal+ " <i>" );
		
		
		if (pos2 == -1)
			pos2 = AllLinesWot.indexOf(cat2Medal+ " <i>" );
		
		
		System.out.println("=======>>>>>>>>>>>>>" + cat1Medal);
		if (pos1 != -1 && pos2 !=-1) {
			int posSrc = 0 ;
			while(posSrc != -1 && posSrc<pos2) {
				posSrc = AllLinesWot.indexOf("src=", pos1);
				int posSlashDiv = AllLinesWot.indexOf("</div>", posSrc); //on doit rechercher tous les src avant </div>
				
				if (posSrc != -1 && posSrc<pos2) {
					
					//on est dans les medailles en question
					String srcImgMedal = "";
					List<String> listSrcImgMedal = new ArrayList<String>();
					do {
						posSrc = AllLinesWot.indexOf("src=", pos1);
						posSrc=  posSrc+"src=".length();
						int posWidth = AllLinesWot.indexOf("width", posSrc);
						srcImgMedal = AllLinesWot.substring(posSrc+1, posWidth-2);
						listSrcImgMedal.add(srcImgMedal);
						
						pos1= posWidth;
						posSrc = AllLinesWot.indexOf("src=", pos1);
					}while(posSrc < posSlashDiv);
					
					int posDebutB = AllLinesWot.indexOf("<b>", pos1);
					int posFinB = AllLinesWot.indexOf("</b>", pos1);
					
					String titleMedal= AllLinesWot.substring(posDebutB+"<b>".length(), posFinB);
					for (String src : listSrcImgMedal) { 
						System.out.println(src + "\t" + titleMedal + "\t"); //titre de la mï¿½daille
					}
					pos1= posFinB;
					
					//la description de la mï¿½daille se trouve entre </b> et le prochain "<"
					int posInf = AllLinesWot.indexOf("<", posFinB + "</b>".length());
					String descMedalWithB= AllLinesWot.substring(posDebutB-1 + "<".length(), posInf);
					System.out.println("\t" + descMedalWithB);
					
					
					//crÃ©ation d'un achievement
					XmlListAchievement myXmlListAchievement = objFactory.createXmlListAchievement();
					
					//set du nom de la mÃ©daille
					myXmlListAchievement.setNAME(titleMedal);
					
					//set description de la mÃ©daille
					//crÃ©ation xlmDescrition achievement
					myXmlDescription= objFactory.createXmlDescription();
					myXmlDescription.setVALUE(descMedalWithB);
					myXmlListAchievement.setDESCRIPTION(myXmlDescription);
					
					//set des src des icÃ´nes des mÃ©dailles
					XmlListSrcImg myXmlListSrcImg = objFactory.createXmlListSrcImg();
					
					for (String src : listSrcImgMedal) {
						//crÃ©ation des src
						XmlSrc myXmlSrc = objFactory.createXmlSrc();
						myXmlSrc.setVALUE(src);
						
						//ajout Ã  la liste des src de la mÃ©daille
						myXmlListSrcImg.getSRC().add(myXmlSrc);
					}
					
					myXmlListAchievement.setSRCIMG(myXmlListSrcImg);
					
					//ajouter listAchievement Ã  CatÃ©gory achievement
					myXmlListCategoryAchievement.getACHIEVEMENT().add(myXmlListAchievement);
				}
				
				
			}
			
		}
		
		
	}

	//xx
	public HashMap<String, XmlListAchievement> BuidHashMapAchievement (XmlWiki xmlWiki) {
		HashMap<String, XmlListAchievement> hashMapAchievement = new HashMap<String, XmlListAchievement>();
		
		
		//parcours de toutes les catï¿½gories de mï¿½dailles
		for(XmlListCategoryAchievement listCatAch	:	xmlWiki.getACHIEVEMENTS().getCATEGORYACHIEVEMENT() ) {
			for (XmlListAchievement ach : listCatAch.getACHIEVEMENT()) {
				for (XmlSrc src : ach.getSRCIMG().getSRC()) {
					String srcValue = src.getVALUE();
					int posLastSlash  = srcValue.lastIndexOf("/");
					String nameFile = srcValue.substring(posLastSlash);
					hashMapAchievement.put(nameFile, ach);
				}
				
			}
		}
		
		return hashMapAchievement;
		
	}


	/**
	 * get all historized stats of users with their id 
	 * @param stat 
	 * @param range 
	 * @param idClan
	 * @param indexBegin
	 * @param indexEnd
	 * @return
	 * @throws Exception 
	 */
	public List<CommunityAccount> getHistorizedStatsUsers(String stat, List<String> listIdUsers, int range ) throws Exception {
		
//		for (String user :  listIdUsers ) {
//			
//			log.warning("getHistorizedStatsUsers for user : " + hMapIdUserName.get(user));
//		}
		
		List<CommunityAccount> resultsFinal = new ArrayList<CommunityAccount>();
		

		PersistenceManager pm =null;
		
		try {
			pm = PMF.get().getPersistenceManager();
	    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm");

				
	        
        	for (String user  : listIdUsers) {
        	/// query
				Query query = pm.newQuery(DaoCommunityAccount2.class);
			    query.setFilter("idUser == nameParam");
			    query.setOrdering("name desc");
			    query.setOrdering("dateCommunityAccount desc");
			    query.setRange(0, range); //only 6 results 
			    //query.setOrdering("hireDate desc");
			    query.declareParameters("String nameParam");
			    List<DaoCommunityAccount2> resultsTmp = (List<DaoCommunityAccount2>) query.execute(user);
			    
			    if(resultsTmp.size() != 0 )
			    {
				    DaoCommunityAccount2 daoComAcc = resultsTmp.get(0);
				    CommunityAccount comAcc=  TransformDtoObject.TransformDaoCommunityAccountToCommunityAccount(daoComAcc);
				    String previousDate = "";
				    for (DaoCommunityAccount2 myDaoCommunityAccount : resultsTmp ) {
				    	//si 2 dates identiques se suivent on ne prend la deuxiÃ¨me
				    	String dateCurrent = "";
				    	if (myDaoCommunityAccount.getDateCommunityAccount() != null) { 
					    	dateCurrent = sdf.format(myDaoCommunityAccount.getDateCommunityAccount());
					    	if (!dateCurrent.equalsIgnoreCase(previousDate)) {
					    		comAcc.listDates.add(dateCurrent);
					    		comAcc.listbattles.add(myDaoCommunityAccount.getData().getStats().getBattles());
					    		
					    		comAcc.listBattlesWins.add(myDaoCommunityAccount.getData().getStats().getBattle_wins());
					    		CommunityAccount comA=  TransformDtoObject.TransformDaoCommunityAccountToCommunityAccount(myDaoCommunityAccount);
					    		
					    		comAcc.listDataPlayerInfos.add(comA.getData());
					    	}
				    	}
				    	previousDate = dateCurrent;
				    }
				    resultsFinal.add(comAcc);
			    }
			    query.closeAll();
		    
        	}
		} catch (Exception e) {
			
			log.severe(e.getLocalizedMessage());
			log.log(Level.SEVERE, "exception :" , e);
			throw e;
		} 
		finally {
			if(pm != null)
				pm.close();
		}
	
		return resultsFinal;
	
	}

	public Map<String, CommunityAccount> getMapHistorizedStatsUsers(List<String> listIdUsers, int range ) {
		
		for (String user :  listIdUsers ) {
			
			log.warning("getHistorizedStatsUsers for user : " + hMapIdUserName.get(user));
		}
		
		Map<String, CommunityAccount> resultsFinal = new HashMap<String, CommunityAccount>();
		

		PersistenceManager pm =null;
		
		try {
			pm = PMF.get().getPersistenceManager();
	    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd hh:mm");

				
	        try {
	        	for (String user  : listIdUsers) {
	        	/// query
					Query query = pm.newQuery(DaoCommunityAccount2.class);
				    query.setFilter("idUser == nameParam");
				    query.setOrdering("name desc");
				    query.setOrdering("dateCommunityAccount desc");
				    query.setRange(0, range); //only 6 results 
				    //query.setOrdering("hireDate desc");
				    query.declareParameters("String nameParam");
				    List<DaoCommunityAccount2> resultsTmp = (List<DaoCommunityAccount2>) query.execute(user);
				    
				    if(resultsTmp.size() != 0 )
				    {
					    DaoCommunityAccount2 daoComAcc = resultsTmp.get(0);
					    CommunityAccount comAcc=  TransformDtoObject.TransformDaoCommunityAccountToCommunityAccount(daoComAcc);
					    String previousDate = "";
					    for (DaoCommunityAccount2 myDaoCommunityAccount : resultsTmp ) {
					    	//si 2 dates identiques se suivent on ne prend la deuxiÃ¨me
					    	String dateCurrent = "";
					    	if (myDaoCommunityAccount.getDateCommunityAccount() != null) { 
						    	dateCurrent = sdf.format(myDaoCommunityAccount.getDateCommunityAccount());
						    	if (!dateCurrent.equalsIgnoreCase(previousDate)) {
						    		comAcc.listDates.add(dateCurrent);
						    		comAcc.listbattles.add(myDaoCommunityAccount.getData().getStats().getBattles());
						    		
						    		comAcc.listBattlesWins.add(myDaoCommunityAccount.getData().getStats().getBattle_wins());
						    	}
					    	}
					    	previousDate = dateCurrent;
					    }
					    resultsFinal.put(user, comAcc);
				    }
				    query.closeAll();
			    
	        	}
			    
			    
	        }
		    catch(Exception e){
		    	log.severe(e.getLocalizedMessage());
	        	//pm.currentTransaction().rollback();
	        }
		} catch (Exception e) {
			e.printStackTrace();
			log.severe(e.getLocalizedMessage());
		} 
		finally {
			if(pm != null)
				pm.close();
		}
	
		return resultsFinal;
	
	}

	/**
	 * get all historized stats tanks of users with their id 
	 * @param idClan
	 * @param indexBegin
	 * @param indexEnd
	 * @return
	 */
	public List<CommunityAccount> getHistorizedStatsTanksUsers(List<String> listIdUsers ) {
		
		log.warning("getHistorizedStatsTanksUsers for " + listIdUsers.size() + " users");
		List<CommunityAccount> resultsFinal = new ArrayList<CommunityAccount>();
		

		PersistenceManager pm =null;
		
		try {
			pm = PMF.get().getPersistenceManager();
	    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd hh:mm");

				
	        try {
	        	for (String user  : listIdUsers) {
	        	/// query
					Query query = pm.newQuery(DaoCommunityAccount2.class);
				    query.setFilter("idUser == nameParam");
				    query.setOrdering("name desc");
				    query.setOrdering("dateCommunityAccount desc");
				    query.setRange(0, 6); //only 6 results 
				    //query.setOrdering("hireDate desc");
				    query.declareParameters("String nameParam");
				    List<DaoCommunityAccount2> resultsTmp = (List<DaoCommunityAccount2>) query.execute(user);
				    
				    if(resultsTmp.size() != 0 )
				    {
					    DaoCommunityAccount2 daoComAcc = resultsTmp.get(0);
					    CommunityAccount comAcc=  TransformDtoObject.TransformDaoCommunityAccountToCommunityAccount(daoComAcc);
					    String previousDate = "";
					    for (DaoCommunityAccount2 myDaoCommunityAccount : resultsTmp ) {
					    	//si 2 dates identiques se suivent on ne prend la deuxiÃ¨me
					    	String dateCurrent = "";
					    	if (myDaoCommunityAccount.getDateCommunityAccount() != null) { 
						    	dateCurrent = sdf.format(myDaoCommunityAccount.getDateCommunityAccount());
						    	if (!dateCurrent.equalsIgnoreCase(previousDate)) {
						    		CommunityAccount comAccForList=  TransformDtoObject.TransformDaoCommunityAccountToCommunityAccount(myDaoCommunityAccount);
						    		comAcc.listDates.add(dateCurrent);
						    		//comAcc.listBattlesTanks.add(comAccForList.g);  //bug now !!!!
						    		
						    		//comAcc.listBattlesTanksWins.add(myDaoCommunityAccount.getData().getStatsVehicules().getBattle_wins());
						    	}
					    	}
					    	previousDate = dateCurrent;
					    }
					    resultsFinal.add(comAcc);
				    }
				    query.closeAll();
			    
	        	}
			    
			    
	        }
		    catch(Exception e){
		    	log.severe(e.getLocalizedMessage());
	        	//pm.currentTransaction().rollback();
	        }
		} catch (Exception e) {
			e.printStackTrace();
			log.severe(e.getLocalizedMessage());
		} 
		finally {
			if(pm != null)
				pm.close();
		}
	
		return resultsFinal;
	
	}

	
	@Override
		public List<CommunityAccount> getHistorizedStatsTanks(  List<String> listIdUser) {
		
			return getHistorizedStatsTanksUsers(listIdUser);
		
		}
	
	@Override
		public List<CommunityAccount> getHistorizedStats(  String stat, List<String> listIdUser) throws Exception {
		
			return getHistorizedStatsUsers(stat , listIdUser, 30);
		
		}



	

	/**
			 * persist wot server's datas  in datastore appengine
			 * @param idClan
			 * @param indexBegin
		 * @param listIdClan 
			 * @param indexEnd
			 */
	public void persistAllStats3(Date date, List<String> listIdClan) {
	
		CronPersistPlayersStats.cronPersistAllStats (new Date(), "500006074");
	
	}


	@Override
	public AllCommunityAccount getAllMembersClanAndStats( List<String> listIdUser) {
	
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
	
		// Escape data from the client to avoid cross-site script vulnerabilities.
		userAgent = escapeHtml(userAgent);
		
		List<CommunityAccount> listCommunityAccount = new ArrayList<CommunityAccount>();
		AllCommunityAccount myAllCommunityAccount = new AllCommunityAccount ();
		myAllCommunityAccount.setListCommunityAccount(listCommunityAccount);
		PersistenceManager pm =null;
		
		try {
			pm = PMF.get().getPersistenceManager();
			
			String AllIdUser ="";
			
			for(String idUser :listIdUser) {
				
				//log.warning("Request stat for user : " + hMapIdUserName.get(idUser));
				
				if("".equalsIgnoreCase(AllIdUser))
					AllIdUser =  idUser;
				else
					AllIdUser = AllIdUser + "," + idUser;
			}
			
	
						
			URL url = null ;

			//http://api.worldoftanks.eu/wot/account/info/?application_id=d0a293dc77667c9328783d489c8cef73&account_id=506486576
			String urlServer = urlServerEU +"/wot/account/info/?application_id=" + applicationIdEU + "&account_id=";
			
			if(lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
				url = new URL(proxy + urlServer + AllIdUser);
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
			
			//Transform playerRatings en communityAccount (pour utiliser des types compatibles avec la sÃ©rialisation (pas de MAP !!))
			List<CommunityAccount> listCommunityAccount1 =  TransformDtoObject.TransformPlayersInfosToListCommunityAccount(playersInfos);
			
			//////////////////////////////////
			// ==API encyclopÃ©die des tanks - Pour obtenir le level des char (on doit calculier le tier moyen jouÃ©)
			// Dans les stats des joueurs nous avons le tank-id mais pas son level
			
			//on requete notre base les averageLevel y ont été stockés par le cron dans la table community Account
			Map <String, CommunityAccount> mapHistStatsUsers = getMapHistorizedStatsUsers(listIdUser, 6);
			
			
			//make some calculation of stats 
			for(CommunityAccount communityAccount : listCommunityAccount1) {
				String user_id = communityAccount.getIdUser();
			
				//setData.
				CommunityAccount  histStatUser= mapHistStatsUsers.get(user_id);
				
				//calcul du tier moyen
				//Double nbBattles = 0.0;
				//Double levelByBattles = 0.0 ; 
				//log.warning("histStatUser " + histStatUser);
				//log.warning("histStatUser.getData() " + histStatUser.getData());
				///log.warning("commAcc.getData().getAverageLevel " + commAcc.getData().getAverageLevel);
				
				DataPlayerInfos myDataCommunityAccountRatings = communityAccount.getData();
				if(histStatUser != null && histStatUser.getData() != null ) {
					Double averageLevelTank =histStatUser.getData().getStatistics().getAllStatistics().getAverageLevelTankCalc(); 
					Double wn8 =histStatUser.getData().getStatistics().getAllStatistics().getWn8();
					
					//average level tank
					myDataCommunityAccountRatings.getStatistics().getAllStatistics().setAverageLevelTankCalc(averageLevelTank);
					//wn8
					myDataCommunityAccountRatings.getStatistics().getAllStatistics().setWn8(wn8);
				}else {
					//WN8 : recup de noobmeter
					ArrayList<String> param = new ArrayList<String>();
					param.add("WN8");
					String statsWN8 = ReadPersistPlayersRecruistation.getStatsWithoutFormat(communityAccount.getData().getNickname(), param).get("WN8");
					
					try {
						Double wn8 = Double.valueOf(statsWN8);
						//wn8
						myDataCommunityAccountRatings.getStatistics().getAllStatistics().setWn8(wn8);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				
				
				
				//== WR calculated
				int battles = myDataCommunityAccountRatings.getStatistics().getAllStatistics().getBattles();
				int battlesWin = myDataCommunityAccountRatings.getStatistics().getAllStatistics().getWins();
				Double wrCal = (double) ((double)battlesWin/(double)battles);
				
				//on ne conserve que 2 digits aprÃ¨s la virgule 
				wrCal = wrCal * 100; //ex : 51,844444
				int intWrCal = (int) (wrCal * 100); //ex : 5184
				
				wrCal = (double)intWrCal / 100 ; //ex : 51,84
				myDataCommunityAccountRatings.getStatistics().getAllStatistics().setBattle_avg_performanceCalc(wrCal);
				
				//== Ratio capture points calculated
				int ctfPoints = myDataCommunityAccountRatings.getStatistics().getAllStatistics().getCapture_points();
				Double ctfPointsCal = (double) ((double)ctfPoints/(double)battles);// 1,28 :1 = 1.28 
				
				//on ne conserve que 2 digits aprÃ¨s la virgule 
				//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
				int intCtfPointsCal = (int) (ctfPointsCal * 100); //ex intCtfPointsCal : 128,27 ctfPointsCal = 1.28
				
				ctfPointsCal = (double)intCtfPointsCal / 100 ; //ex ctfPointsCal : 1,28 intCtfPointsCal = 128
				myDataCommunityAccountRatings.getStatistics().getAllStatistics().setRatioCtfPoints(ctfPointsCal);
				
				//==Damage Ration calculated
				int damagePoints = myDataCommunityAccountRatings.getStatistics().getAllStatistics().getDamage_dealt();
				Double ratioDamagePoints = (double) ((double)damagePoints/(double)battles);
				
				//on ne conserve que 2 digits aprÃ¨s la virgule 
				//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
				int intRatioDamagePoints = (int) (ratioDamagePoints * 100); //ex : 128,27
				
				ratioDamagePoints = (double)intRatioDamagePoints / 100 ; //ex : 1,28
				myDataCommunityAccountRatings.getStatistics().getAllStatistics().setRatioDamagePoints(ratioDamagePoints);
				
				
				//==Ratio Defense calculated
				int droppedCtfPoints = myDataCommunityAccountRatings.getStatistics().getAllStatistics().getDropped_capture_points();
				Double ratioDroppedCtfPoints = (double) ((double)droppedCtfPoints/(double)battles);
				
				//on ne conserve que 2 digits aprÃ¨s la virgule 
				//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
				int intRatioDroppedCtfPoints = (int) (ratioDroppedCtfPoints * 100); //ex : 128,27
				
				ratioDroppedCtfPoints = (double)intRatioDroppedCtfPoints / 100 ; //ex : 1,28
				myDataCommunityAccountRatings.getStatistics().getAllStatistics().setRatioDroppedCtfPoints(ratioDroppedCtfPoints);
				
				
				//==Ratio Destroyed calculated
				int destroyedPoints = myDataCommunityAccountRatings.getStatistics().getAllStatistics().getFrags();
				Double ratiodestroyedPoints = (double) ((double)destroyedPoints/(double)battles);
				
				//on ne conserve que 2 digits aprÃ¨s la virgule 
				//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
				int intRatiodestroyedPoints = (int) (ratiodestroyedPoints * 100); //ex : 128,27
				
				ratiodestroyedPoints = (double)intRatiodestroyedPoints / 100 ; //ex : 1,28
				myDataCommunityAccountRatings.getStatistics().getAllStatistics().setRatioDestroyedPoints(ratiodestroyedPoints);
				
				//==Ratio Detected calculated
				int detectedPoints = myDataCommunityAccountRatings.getStatistics().getAllStatistics().getSpotted();
				Double ratioDetectedPoints = (double) ((double)detectedPoints/(double)battles);
				
				//on ne conserve que 2 digits aprÃ¨s la virgule 
				//ctfPointsCal = ctfPointsCal * 100; //ex : 1,2827
				int intRatioDetectedPoints = (int) (ratioDetectedPoints * 100); //ex : 128,27
				
				ratioDetectedPoints = (double)intRatioDetectedPoints / 100 ; //ex : 1,28
				myDataCommunityAccountRatings.getStatistics().getAllStatistics().setRatioDetectedPoints(ratioDetectedPoints);
			
			}
			//add account
			listCommunityAccount.addAll(listCommunityAccount1);


		} catch (MalformedURLException e) {
			// ...
			e.printStackTrace();
		} catch (IOException e) {
			// ...
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	
		return myAllCommunityAccount;
	
	}

	static void main (String arg[]) {
		WotServiceImpl wot  = new WotServiceImpl();
		 wot.getClans("NOVA_SNAIL", 0);
		 System.exit(0);
		
	}

	@Override
	public AllCommunityAccount getAllStatsFromDossierCache(String fileName)
			throws IllegalArgumentException {
		
		URL url = null ;
		//to avoid SSL Protcole erreur ?
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
			//posting a folder to wot-dossier
			//http://wot-dossier.appspot.com/service/dossier-to-json 
			if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
				 //5726971199750144
				url = new URL(WotServiceImpl.proxy + "http://wot-dossier.appspot.com/service/dossier-to-json");					
			}
			else {
				url = new URL("http://wot-dossier.appspot.com/service/dossier-to-json" );		
			}
			
			File fileToUpload = new File("D:\\PrivÃ©\\tle-conniat\\wot\\Wargaming.net\\WorldOfTanks\\dossier_cache\\NRXWO2LOFVRXILLQGEXHO33SNRSG6ZTUMFXGW4ZONZSXIORSGAYDCNJ3ON2HE33OM42DIX2FKU======.dat");

			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setReadTimeout(60000);
			conn.setConnectTimeout(60000);
			
			conn.setDoOutput(true); // This sets request method to POST.
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/binary");
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.setRequestProperty("Accept-Language", "en,fr;q=0.8,fr-fr;q=0.5,en-us;q=0.3");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			conn.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
			conn.setRequestProperty("Transfer-Encoding", "base64");
			
			conn.setRequestProperty("Pragma", "no-cache");
			conn.setRequestProperty("Cache-Control", "no-cache");
			
			PrintWriter writer = null;
			FileInputStream inputStream  = null;
			
			try {
			    BufferedReader reader = null;
			    //StringBuffer rec = new StringBuffer("");  
			    InputStreamReader is = null;
			    BufferedInputStream in = null;
			        
			        inputStream = new FileInputStream(fileToUpload);
			        byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			        	conn.getOutputStream().write(buffer, 0, bytesRead);
			        }
			        conn.getOutputStream().flush();
			        
					int responseCode = ((HttpURLConnection) conn).getResponseCode();
					System.out.println(responseCode); // Should be 200
					reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

					//printing response
					String line = "";
					String AllLines = "";
					while ((line = reader.readLine()) != null) {
						AllLines = AllLines + line;
					}
					System.out.println(AllLines);
			        
			} finally {
		        if (inputStream != null )
						inputStream.close();
					
			}
			
		} catch (Exception e)  {
	       
			e.printStackTrace();
		}
		
		
		return null;
	}



}
