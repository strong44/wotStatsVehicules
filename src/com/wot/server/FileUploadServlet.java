package com.wot.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

/**
 * servlet to handle file upload requests
 * 
 * @author hturksoy
 * 
 */
public class FileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
		//
		URL url = null ;
		//to avoid SSL Protcole erreur ?
		System.setProperty("jsse.enableSNIExtension", "false");

		//posting a folder to wot-dossier
		//http://wot-dossier.appspot.com/service/dossier-to-json 
		if(WotServiceImpl.lieu.equalsIgnoreCase("boulot")){ //on passe par 1 proxy
			 //5726971199750144
			url = new URL(WotServiceImpl.proxy + "http://wot-dossier.appspot.com/service/dossier-to-json");					
		}
		else {
			url = new URL("http://wot-dossier.appspot.com/service/dossier-to-json" );		
		}

		
		
		//URL url = new URL(urlStr);
		URLFetchService urlFetchService =
		URLFetchServiceFactory.getURLFetchService();
		HTTPRequest httpRequest = new HTTPRequest(url);
		HTTPResponse response = urlFetchService.fetch(httpRequest);
		
		
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
		conn.setInstanceFollowRedirects(false);
		PrintWriter writer = null;
		FileInputStream inputStream  = null;
		
		
        // process only multipart requests
        if (ServletFileUpload.isMultipartContent(req)) {

            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            try {
                resp.setContentType("text/plain"); 
                FileItemIterator iterator = upload.getItemIterator(req);

                while (iterator.hasNext()) {
                    FileItemStream item = iterator.next();
                   InputStream stream = item.openStream();
                  if (item.isFormField()) {
                        System.out.println("Got a form field: " + item.getFieldName()  + " " +item);
                 } else{
                    	 System.out.println("Got an uploaded file: " + item.getFieldName() +
                                  ", name = " + item.getName());
                        int len;
                        byte[] buffer = new byte[8192];
                        while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                        	//TODO : send data to service/dossier-to-json
                        	//resp.getOutputStream().write(buffer, 0, len);
                        	conn.getOutputStream().write(buffer, 0, len);

                        }
                        //conn.getOutputStream().flush();
                        
                        /////
    					int responseCode = ((HttpURLConnection) conn).getResponseCode();
    					System.out.println(responseCode); // Should be 200
    					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

    					//printing response
    					String line = "";
    					String AllLines = "";
    					while ((line = reader.readLine()) != null) {
    						AllLines = AllLines + line;
    					}
    					System.out.println(AllLines);

                    }

                }
            } catch (Exception e) {
            	e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "An error occurred while creating the file : " + e.getMessage());
            }

        } else {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                            "Request contents type is not supported by the servlet.");
        }
    }
}