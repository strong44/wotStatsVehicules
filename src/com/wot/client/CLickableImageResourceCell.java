package com.wot.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.ImageResourceRenderer;

public class CLickableImageResourceCell extends AbstractCell<ImageResource> {

	private static ImageResourceRenderer renderer;

    public CLickableImageResourceCell () {
        super("click");
        if (renderer == null) {
            renderer = new ImageResourceRenderer();
        }
    }

    @Override
    public void onBrowserEvent(Context context, final Element parent, ImageResource value, NativeEvent event,
            ValueUpdater<ImageResource> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if (event.getType().equals("click")) {
            if(valueUpdater != null) {
                valueUpdater.update(value);
            }
        }
    }

    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context,
            ImageResource value, final SafeHtmlBuilder sb) {
          sb.append(renderer.render(value));
    }

}
