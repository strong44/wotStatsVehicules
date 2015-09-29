package com.wot.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HelloWidgetWorld extends Composite {

	  interface MyUiBinder extends UiBinder<Widget, HelloWidgetWorld> {}
	  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	  //@UiField ListBox listBox;
	  @UiField Button button;
	  
	  public HelloWidgetWorld(String... names) {
	    // sets listBox
	    initWidget(uiBinder.createAndBindUi(this));
	    //initWidget(button);
//	    for (String name : names) {
//	      listBox.addItem(name);
//	    }
	  }
	  
	  @UiHandler("button")
	  void handleClick(ClickEvent e) {
	    Window.alert("Hello, AJAX");
	  }
	}
