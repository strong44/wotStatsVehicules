package com.wot.client;


import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;


public class MyButtonCell  extends ButtonCell {

	 /** 
     * Constructor. 
     */ 
    public MyButtonCell() 
    { 
        super(); 

    } 
	    
    @Override 
    public void render(final Context context, final SafeHtml data, 
    		final SafeHtmlBuilder sb) 
    { 
    	String pathImg = data.asString().substring(0, data.asString().indexOf("#"));
    	String valImg = data.asString().substring(data.asString().indexOf("#")+1);
        sb.appendHtmlConstant("<button  type=\"button\" style=\"background-color:transparent;text-align:right;font-size: 0.9em;font-weight: bold;background-repeat: no-repeat; width=300px;height=300px;background-image:url(" +pathImg + ")\"" 
            + ">"); 
		String valStr = String.valueOf(valImg);
		int len = valStr.length();
		for(int i = len ; i < 4 ; i++) {
			valStr= "&nbsp" + valStr;  
		}
		
        sb.appendHtmlConstant( "<br><br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + valStr + "</button>");
    } 

	
}
