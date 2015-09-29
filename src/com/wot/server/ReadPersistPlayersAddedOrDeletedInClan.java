package com.wot.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ReadPersistPlayersAddedOrDeletedInClan extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(WotServiceImpl.class.getName());
	
	private static String applicationIdEU = "d0a293dc77667c9328783d489c8cef73";
	private static String urlServerEU =  "http://api.worldoftanks.eu";

	//private static List<CommunityAccount> listCommAcc = new ArrayList<CommunityAccount>();
	
	private static	List<String> listMembersAdded = null;
	private static	List<String> listMembersDeleted = null;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	log.warning("========lancement doGet  ReadPersistPlayersAddedOrDeletedInClan ============== " );
        resp.setContentType("text/html");
        //resp.getWriter().println("Hello, ReadPersistPlayersStats ");
        String clanId = req.getParameter("clanId");
        
        if(clanId != null && !"".equalsIgnoreCase(clanId) ) {
        	
        	//lecture des stats du joueur de nom userName
        	//List<CommunityAccount> listCommAcc = readPersistAllStats( new Date(), clanId, userName);
        	
        	
        	//lecture de la composition du clan en base wotachievement et seulement une fois 
        	if (listMembersAdded == null ) {
        		listMembersAdded = new ArrayList<String>();
        		listMembersDeleted = new ArrayList<String>();

         		PersistenceManager pm = null;
        		pm = PMF.get().getPersistenceManager();
        		
                try {
        			Query query = pm.newQuery(DaoCommunityClan2.class);
        		    query.setFilter("idClan == nameParam");
        		    //query.setOrdering("name desc");
        		    query.setOrdering("dateCommunityClan desc");
        		    query.setRange(0, 30); //only 30 results 
        		    //query.setOrdering("hireDate desc");
        		    query.declareParameters("String nameParam");
        		    List<DaoCommunityClan2> resultsTmp = (List<DaoCommunityClan2>) query.execute(clanId);
        		    
        		  //recup des membres des 30 derniers jours
        		    if(resultsTmp.size() >= 1  )
        		    {       
        		    	for (DaoCommunityClan2 myDaoCommunityClan2 : resultsTmp) {
        		    		
        		    		if (myDaoCommunityClan2.getUserAdded() != null && !"".equalsIgnoreCase(myDaoCommunityClan2.getUserAdded()) )  {
        		    			listMembersAdded.add(myDaoCommunityClan2.getUserAdded());
        		    		}

        		    		if (myDaoCommunityClan2.getUserDeleted() != null && !"".equalsIgnoreCase(myDaoCommunityClan2.getUserDeleted()) )  {
        		    			listMembersDeleted.add(myDaoCommunityClan2.getUserDeleted());
        		    		}
        		    	}
        		    }
                }
        	    catch(Exception e){
        	    	e.printStackTrace();
        	    	log.log(Level.SEVERE, "Exception while quering  DaoCommunityClan2", e);
                	pm.currentTransaction().rollback();
                }
        	}
        	
        	
        	//build a HTML result
        	if (true ) { 
        		String userNameAdded = "";
        		String userNameDeleted = "";
        		
				for ( String members :listMembersAdded) {
					
					userNameAdded = userNameAdded + " " + members +"<BR>";
				}
				for ( String members :listMembersDeleted) {
					
					userNameDeleted = userNameDeleted + " " + members +"<BR>";
				}
        		
        		StringBuffer strBuf = new StringBuffer();
        		//<html
        		strBuf.append("<HTML>");
        		strBuf.append("<BODY bgcolor='#FFFFFF'>"); //fond blanc de l'iframe
 
        		String userAddedCodeColor= "#6d9521"; //vert;
        		String userDeletedCodeColor= "#cd3333"; //rouge;
        		

        		strBuf.append("<TABLE width='150' border bgcolor='" + userAddedCodeColor + "' style='color:white;' >").
        					//ent�tes des colonnes
			        		append("<TR>").
								append("<TH>").
									append("Entrées de Joueurs dans le clan").
								append("</TH>").
							append("</TR>").
        					append("<TR>").
        						append("<TD>").
        							append(userNameAdded).
        						append("</TD>").
        					append("</TR>").
        				append("</TABLE>");
        		//== WR
        		strBuf.append("<TABLE width='150' border bgcolor='" + userDeletedCodeColor + "' style='color:white;' >").
				//ent�tes des colonnes
        		append("<TR>").
					append("<TH>").
						append("Sorties de joueurs du clan").
					append("</TH>").
				append("</TR>").
				append("<TR>").
					append("<TD>").
						append(userNameDeleted).
					append("</TD>").
				append("</TR>").
			append("</TABLE>");
        		
        		strBuf.append("</BODY>");
        		strBuf.append("</HTML>");
        		String buf= strBuf.toString();
        		resp.getWriter().println(buf);
        	}
        	
		}else {
			log.warning("WARNING: =======lancement ReadPersistPlayersAddedOrDeletedInClan  with bad idClan or bad userName =" + clanId );
		}
    }

}
