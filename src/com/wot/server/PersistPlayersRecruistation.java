package com.wot.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Text;
import com.wot.shared.AllCommunityAccount;
import com.wot.shared.CommunityAccount;

@SuppressWarnings("serial")
public class PersistPlayersRecruistation extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(WotServiceImpl.class.getName());
	
	//static String lieu = "boulot"; //boulot ou maison si boulot -> pedro proxy 
	

	
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	log.warning("========lancement doGet  PersistPlayersRecruistation ============== " );
        resp.setContentType("text/plain");
        String recrues = req.getParameter("Recrues");
        String pwd = req.getParameter("gogole");
        if(recrues != null && !"".equalsIgnoreCase(recrues)) {
        	if (pwd != null && pwd.equalsIgnoreCase("zorro")) {
        		persistAllUsersRecruistation( new Date(), recrues);
        		resp.getWriter().println("Hello, PersistPlayersRecruistation Done");
        	}
        	else {
        		resp.getWriter().println("Hello, PersistPlayersRecruistation without good param !");
    			log.severe("ERROR: =======lancement PersistPlayersRecruistation  with bad param   ===");
    		}
		}else {
			resp.getWriter().println("Hello, PersistPlayersRecruistation without param Recrues !");
			log.severe("ERROR: =======lancement PersistPlayersRecruistation  with Recrues null ===");
		}
    }

	public static String persistAllUsersRecruistation(Date date, String users) {
			
			log.warning("========lancement persistAllUsersRecruistation : " +  date + ":" + users + " :============== " );
			
			List<CommunityAccount> listCommunityAccount = new ArrayList<CommunityAccount>();
			AllCommunityAccount myAllCommunityAccount = new AllCommunityAccount ();
			myAllCommunityAccount.setListCommunityAccount(listCommunityAccount);
			PersistenceManager pm =null;
			pm = PMF.get().getPersistenceManager();
			List<String> listIdUser = new ArrayList<String>();
			
			try {
				
				DaoRecruistation myDaoRecruistation = new DaoRecruistation();
				myDaoRecruistation.setDate(date);
				//,,,,,,,,,,,,,,,,,,,,,Joueur,bigbatblues,ecraseur37,Jed8,Daveylee,Litiri,luffy145,TCH3RN0BILD,toudoux91,mdum,baracuda58,valdohel07,Jo124,patsappa1973,le_duke,Sheetogaz,Filoflo95,Arns31,___Arno___,z750naked,miwashaky,kormag,KENNY_1,poussinot,Non2diou,Jaeger_Legendary,francisdup57,jubibi,Twinkl3Twinkl3Littl3Star,Traboucaire,lolo5974,Arathornf,slaydead,Heloane,blacksharck,GeneralSkiny,Philix,mathoulin,LaurentH,THUNDERBIRDS1965,keokuk,atila31,Rael_Cun,antourter,JE_GUEVARA,Cappi13,RAGUL27_13,Slayercore,taupinw_a,Pyro68220,Fuxyions,maxagaz60,cesar95,willor62,Blapi2342,Jenka1,Shadowgaia,SdtPithivier,The_replicants,Jarv7s,rompafrolic,Cagole,johnbond005,samaribail,KarmelowyMen,tinkydead,bencrosoft,Acebanana,neox83600,Dokuganryu,jourquinm,backcat,larverne81,lttlejoe,Angelusftw,SaVaFaireBoum,sephirot74,waketrip,appodu92,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
				//users = users.replaceAll(",,", "");
				
				Text text =  new Text(users);
				myDaoRecruistation.setUsers(text);
				
				/////////////////////
				//pm = PMF.get().getPersistenceManager();
		        try {
		        	
		        	
		        	//must transform before persist the objet
		        	pm.currentTransaction().begin();
		        	
		        	pm.makePersistent(myDaoRecruistation);
		        	pm.currentTransaction().commit();
		        	//log.warning("vehicules daoCommunityAccount " + daoCommunityAccount.getData().statsVehicules.get(0).getName() + ":"+  daoCommunityAccount.getData().statsVehicules.get(0).getBattle_count() + ":"+  daoCommunityAccount.getData().statsVehicules.get(0).getWin_count());
		        	
		        }
			    catch(Exception e){
			    	e.printStackTrace();
			    	log.log(Level.SEVERE, "Exception while saving daoCommunityAccount", e);
		        	pm.currentTransaction().rollback();
		        }
			        
			
						
				
			}   catch (Exception e) {
					// ...
				e.printStackTrace();
				log.throwing("Persist users recruistation", "", e);
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
		
			log.warning("======== End persistAllUsersRecruistation : " +  date + ":" + users + " :============== " );

			return users;
		
		}
	


}
