package com.wot.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CronAmazone extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(WotServiceImpl.class.getName());
	private static String urlServerEU =  "https://www.amazon.fr/s/ref=nb_sb_ss_i_7_20?__mk_fr_FR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&url=search-alias%3Daps&field-keywords=samsung+galaxy+tab4+7+pouces&sprefix=samsung+galaxy+tab4+%2Caps%2C207";
	private static String lieu = "maison"; 
	static boolean waitOrNot = false;  
	
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	log.warning("========lancement doGet  CronAmazone ============== " );
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, CronAmazone ");
       	cronAmazone( );
    }

	public static void  cronAmazone( ) {

			
			log.warning("========lancement cronAmazone : " );
			
			try {
				if (waitOrNot){
					System.out.println("Wait 30 minutes ");
					Thread.sleep(1000 + 30 * 60); //attente de 30 minutes pour tromper les déctecteurs de robot
					waitOrNot = !waitOrNot; 
				}else{
					System.out.println("Not wait 30 minutes ");
					waitOrNot = !waitOrNot;
				}
					
				
			} catch (InterruptedException e1) {
			
				e1.printStackTrace();
			} 
			
			try {
				
				//=== recup des stats des joueurs ==========
				URL url = null ;
				String urlServer = urlServerEU ;
				
				if(lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
					url = new URL(WotServiceImpl.proxy + urlServer );
				}
				else {
					url = new URL(urlServer);
				}
				
				/*
				<a class="a-link-normal s-access-detail-page  a-text-normal" 
				  title="Samsung Galaxy Tab 4 Tablette Tactile 7&quot; (17,78 cm) 1,2 GHz 8 Go Android Wi-Fi Blanc" 
				  href="http://www.amazon.fr/Samsung-Galaxy-Tab-Tablette-Tactile/dp/B00KINIP9C/ref=sr_1_1?ie=UTF8&amp;qid=1434972809&amp;sr=8-1&amp;keywords=samsung+galaxy+tab4+7+pouces">
				  <h2 class="a-size-medium a-color-null s-inline s-access-title a-text-normal">
				  Samsung Galaxy Tab 4 Tablette Tactile 7" (17,78 cm) 1,2 GHz 8 Go Android Wi-Fi Blanc
				  </h2>
				 </a>
				*/				
				/*
				 * <span id="priceblock_ourprice" class="a-size-medium a-color-price">EUR 141,10</span>
				 */
				
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setReadTimeout(60000);
				conn.setConnectTimeout(60000);
				conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				String AllLines = "";
			
				while ((line = reader.readLine()) != null) {
					AllLines = AllLines + line;
				}
				//System.out.println(AllLines);
				reader.close();
				
				//title="Samsung Galaxy Tab 4 Tablette Tactile 7&quot;
				//<span class="a-size-base a-color-price s-price a-text-bold">EUR 141,10</span></a>  
				int indexTitle = AllLines.indexOf("title=\"Samsung Galaxy Tab 4 Tablette Tactile 7&quot;");
				if (indexTitle> 0){ 
					int indexDebutEur = AllLines.indexOf("EUR", indexTitle);
					if (indexDebutEur > 0) {
						int indexDebutSpan = AllLines.indexOf("span", indexDebutEur);
						if (indexDebutSpan > 0)
						{
							//EUR 136,90
							String strPrix= AllLines.substring(indexDebutEur + 4 , indexDebutSpan - 2);
							System.out.println("Prix str: " + strPrix);
							try {
								Float floatPrix = Float.parseFloat(strPrix.replace(",", "."));
								System.out.println("Prix float: " + floatPrix);
								int prixMax = 137 ;
								if (floatPrix < prixMax) {
									System.out.println("Prix < " +prixMax);
									
									
									 Properties props = new Properties();
								        Session session = Session.getDefaultInstance(props, null);

								        String msgBody = "Le prix de la tablette samsung galaxy tab4 7 pouces est à :" + strPrix;

								        try {
								            Message msg = new MimeMessage(session);
								            msg.setFrom(new InternetAddress("thierry.leconniat@gmail.com", "Thierry LE CONNIAT"));
								            msg.addRecipient(Message.RecipientType.TO,
								                             new InternetAddress("esthetic.auto29@yahoo.fr", "SARL Esthetic"));
								            msg.addRecipient(Message.RecipientType.CC,
						                             new InternetAddress("thierry.leconniat@gmail.com", "Thierry LE CONNIAT"));
								            msg.setSubject("La recherche de la tablette Samsung galaxy tab4 7 pouces");
								            msg.setText(msgBody);
								            Transport.send(msg);

								        } catch (AddressException e) {
								            // ...
								        } catch (MessagingException e) {
								            // ...
								        }
								}
								else
									System.out.println("Prix > " +prixMax);
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Erreur de conversion de prix en float: " + strPrix);
							}
							
						}
						else
							System.out.println("Erreur index of indexDebutSpan");
					}
					else
						System.out.println("Erreur index of indexDebutEur");
				}
				else
					System.out.println("Erreur index of indexTitle");
				
				
				
//				 org.jsoup.nodes.Document doc = Jsoup.parse(AllLines, "http://wotnvs.appspot.com");
//				 org.jsoup.select.Elements links =  doc.select("span[id]");
//				 
//				 for (Element link : links) {
//			            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
//			     }
				
				
			} catch (MalformedURLException e) {
				// ...
				log.throwing("cronAmazone", "", e);
				log.severe("MalformedURLException " + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				// ...
				log.throwing("cronAmazone", "", e);
				log.severe("IOException " + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (Exception e) {
					// ...
				e.printStackTrace();
				log.throwing("cronAmazone", "", e);
				log.severe("Exception " + e.getLocalizedMessage());
				 StackTraceElement[] stack = e.getStackTrace();
				 for (StackTraceElement st : stack) {
					 log.severe(st.getMethodName()+":"+st.getLineNumber());
					 
					 
				 }
				 
				//e.printStackTrace();
			}
			finally {
				
			}
		
			
		
		}



	

	public static void main(String args[]) {
		cronAmazone();
	}
		

}
