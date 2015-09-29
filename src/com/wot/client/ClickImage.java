package com.wot.client;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;

public class ClickImage extends AbstractSafeHtmlCell<String> {

	/**
	   * Construct a new ButtonCell that will use a {@link SimpleSafeHtmlRenderer}.
	   */
	  private String bgImage="";
	  public ClickImage() 
	  {
	    this(SimpleSafeHtmlRenderer.getInstance());
	  }
	  public ClickImage(String bgImage) 
	  {
	    this(SimpleSafeHtmlRenderer.getInstance());
	    this.bgImage=bgImage;
	  }

	  public String getBgImage() 
	  {
	    return bgImage;
	  }
	  public void setBgImage(String bgImage) 
	  {
	    this.bgImage = bgImage;
	  }
	/**
	   * Construct a new ButtonCell that will use a given {@link SafeHtmlRenderer}.
	   * 
	   * @param renderer a {@link SafeHtmlRenderer SafeHtmlRenderer<String>} instance
	   */
	  public ClickImage(SafeHtmlRenderer<String> renderer) {
	    super(renderer, "click", "keydown");
	  }

	  @Override
	  public void onBrowserEvent(Context context, Element parent, String value,
	      NativeEvent event, ValueUpdater<String> valueUpdater) {
	    super.onBrowserEvent(context, parent, value, event, valueUpdater);
	    if ("click".equals(event.getType())) {
	      onEnterKeyDown(context, parent, value, event, valueUpdater);
	    }
	  }

	  @Override
	  public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) 
	  {
	    String img=getBgImage();
	    String disableButton="";
	    if(img.equals("transparentButton"))
	    {
	        disableButton = "disabled=\"disabled\"";
	    }
	    sb.appendHtmlConstant("<button class=\""+img+"\" type=\"button\" "+disableButton+" tabindex=\"-1\">");
	    if (data != null) {
	      sb.append(data);
	    }
	    sb.appendHtmlConstant("</button>");
	  }

	  @Override
	  protected void onEnterKeyDown(Context context, Element parent, String value,
	      NativeEvent event, ValueUpdater<String> valueUpdater) {
	    if (valueUpdater != null) {
	      valueUpdater.update(value);
	    }
	  }
	
}
