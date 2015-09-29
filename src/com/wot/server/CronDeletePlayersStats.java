package com.wot.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CronDeletePlayersStats extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(WotServiceImpl.class.getName());
	
	
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	log.warning("========lancement doGet  CronDeletePlayersStats ============== " );
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, CronDeletePlayersStats ");
        String nbDelete = req.getParameter("nbDelete");
        int nb = Integer.valueOf(nbDelete);
        if(nbDelete != null && !"".equalsIgnoreCase(nbDelete) ) {
        	cronDeleteSomeStats( new Date(), nb);
		}else {
			log.info("ERROR: =======lancement CronDeletePlayersStats avec " + nbDelete);
		}
    }

    /**
     * efffacement de la table DaoCommunityAccount2 de nb enregistrement
     * @param date
     * @param nb
     */
	public void cronDeleteSomeStats(Date date, int nb) {
			
			log.warning("========lancement cronDeleteSomeStats : " +  date + ":" + nb + " :============== " );
			
			if (nb ==0) //pas de delete à faire
				return; 
			
			
			PersistenceManager pm =null;
			pm = PMF.get().getPersistenceManager();
			try {
				if (nb !=0 ) {
					//some cleaning in stats 
					Query query = pm.newQuery(DaoCommunityAccount2.class);
				    query.setOrdering("dateCommunityAccount asc");
				    query.setRange(0, nb); //only nb results 
				    List<DaoCommunityAccount2> resultsTmp = (List<DaoCommunityAccount2>) query.execute();
				    
				    try {
				    	 if (!resultsTmp.isEmpty()) {
				    		    for (DaoCommunityAccount2 myDaoCommunityAccount2 : resultsTmp) {
				    		    	pm.currentTransaction().begin();
						        	//
				    		    	//log.warning(myDaoCommunityAccount2.getDateCommunityAccount().toString());
				    		    	//
				    		    	if (myDaoCommunityAccount2.getData() != null && myDaoCommunityAccount2.getData().getStats() != null) {
				    		    		pm.deletePersistent(myDaoCommunityAccount2.getData().getStats());
				    		    		pm.deletePersistent(myDaoCommunityAccount2.getData());
				    		    	}
				    		    	pm.deletePersistent(myDaoCommunityAccount2);
						        	pm.currentTransaction().commit();
				    		    }
				    	 } 
			        }
				    catch(Exception e){
				    	e.printStackTrace();
				    	log.log(Level.SEVERE, "Exception while deleting daoCommunityAccount", e);
			        	pm.currentTransaction().rollback();
			        }
				}


			    //pour réussir à supprimer les DaoDataCommunityAccount, DaoDataCommunityAccountRatings, DaoDataCommunityAccountRatingsElement2 orphelins 
			    //il faut requeter tous les DaoCommunityAccount2 (peu nombreux pour l'instant)
			    // et ensuite requeter par paquet de 200 DaoDataCommunityAccount pour supprimer parmis ces derniers ceux qui ne sont pas liés à des 
			    //DaoCommunityAccount2
			    
			    //some cleaning in stats 
				Query query = pm.newQuery(DaoCommunityAccount2.class);
			    //query.setOrdering("dateCommunityAccount asc");
			    //query.setRange(0, 500); //only nb results 
			    List<DaoCommunityAccount2> resultsDaoCommunityAccount2Tmp = (List<DaoCommunityAccount2>) query.execute();
			    
			    Query query2 = pm.newQuery(DaoDataCommunityAccount2.class);
			    //query.setOrdering("dateCommunityAccount asc");
			    query2.setRange(0, nb); //only nb results 
			    List<DaoDataCommunityAccount2> resultsDaoDataCommunityAccount2Tmp = (List<DaoDataCommunityAccount2>) query2.execute();
			    
			    try {
			    	 if (!resultsDaoCommunityAccount2Tmp.isEmpty() && !resultsDaoDataCommunityAccount2Tmp.isEmpty()) {
			    		    for (DaoDataCommunityAccount2 myDaoDataCommunityAccount2 : resultsDaoDataCommunityAccount2Tmp) {
			    		    	
			    		    	boolean orphin = true ;
			    		    	for (DaoCommunityAccount2 myDaoCommunityAccount2 : resultsDaoCommunityAccount2Tmp) {
			    		    		if (myDaoCommunityAccount2.getData() != null && myDaoCommunityAccount2.getData().getKey() == myDaoDataCommunityAccount2.getKey()) {
			    		    			//ce myDaoDataCommunityAccount2 n 'est pas orphelin 
			    		    			orphin = false ; 
			    		    			break ;
			    		    		}
			    		    	}
			    		    	if (orphin ) {
			    		    		pm.currentTransaction().begin();
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().battle_avg_performance);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().battle_avg_performanceCalc);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().battle_avg_xp);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().battle_wins);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().battles);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().ctf_points);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().damage_dealt);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().dropped_ctf_points);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().frags);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().integrated_rating);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().ratioDamagePoints);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().ratioDestroyedPoints);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().ratioDetectedPoints);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().ratioDroppedCtfPoints);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().spotted);
			    		    		pm.deletePersistent(myDaoDataCommunityAccount2.getStats().xp);
				    		    	pm.deletePersistent(myDaoDataCommunityAccount2);
						        	pm.currentTransaction().commit();
			    		    	}
			    		    	
			    		    }
			    	 } 
		        }
			    catch(Exception e){
			    	e.printStackTrace();
			    	log.log(Level.SEVERE, "Exception while deleting daoCommunityAccount", e);
			    	if(pm.currentTransaction().isActive())
			    		pm.currentTransaction().rollback();
		        }
			    			    
			    
			    
			    if (nb != 0 ) {
			    
				    //DaoDataCommunityAccountStatsVehicules - On peut tout supprimer
				    query = pm.newQuery(DaoDataCommunityAccountStatsVehicules.class);
				    query.setRange(0, nb); //only nb results 
				    List<DaoDataCommunityAccountStatsVehicules> resultsVeh = (List<DaoDataCommunityAccountStatsVehicules>) query.execute();
				    
				    try {
				    	 if (!resultsVeh.isEmpty()) {
				    		    for (DaoDataCommunityAccountStatsVehicules myDao : resultsVeh) {
				    		    	pm.currentTransaction().begin();
						        	//
				    		    	//log.warning(myDao.getName());
				    		    	//
						        	pm.deletePersistent(myDao);
						        	pm.currentTransaction().commit();
				    		    }
				    	 } 
			        }
				    catch(Exception e){
				    	e.printStackTrace();
				    	log.log(Level.SEVERE, "Exception while deleting DaoDataCommunityAccountStatsVehicules", e);
			        	pm.currentTransaction().rollback();
			        }
			    }
			    
			    
			}  catch (Exception e) {
					// ...
				e.printStackTrace();
				log.throwing("Delete stats", "", e);
				log.severe("Exception " + e.getLocalizedMessage());
//				 StackTraceElement[] stack = e.getStackTrace();
//				 for (StackTraceElement st : stack) {
//					 log.severe(st.getMethodName()+":"+st.getLineNumber());
//				 }
			}
			finally {
				if (pm != null)
					pm.close();
			}
			
			
		}
	
}
