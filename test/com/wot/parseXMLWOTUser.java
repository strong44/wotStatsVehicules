
package com.wot;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException; 

import org.xml.sax.SAXException; 
import org.xml.sax.helpers.DefaultHandler; 


public class parseXMLWOTUser extends DefaultHandler {

	static void main (String [] args ) {
//		parseXMLWOTUser myH = new parseXMLWOTUser(); 
//     SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
//     SAXParser saxParser = saxParserFactory.newSAXParser(); 
//     InputStream iS = Properties.getInputStream(GetProp 
//                        .getResourceName("WEB-INF/myfile.xml")); 
//     saxParser.parse(iS, pa); 
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		
		 try { 

             // Using factory get an instance of document builder 
             DocumentBuilder db = dbf.newDocumentBuilder(); 

             // parse using builder to get DOM representation of the XML file 
             File f = new File("d:/wot.xml"); 
             if (!f.exists() && f.length() < 0) { 
                     System.out.println("The specified file is not exist"); 
                     //return null; 
             } else { 
                     db.parse(f); 
                     System.out.println(db);
             } 

             // C:\programmieren\ibsys2\scsim\war\WEB-INF 
     } catch (ParserConfigurationException pce) { 
             pce.printStackTrace(); 
     } catch (SAXException se) { 
             se.printStackTrace(); 
     } catch (IOException ioe) { 
             ioe.printStackTrace(); 
     } 
		 
	}
     
}
