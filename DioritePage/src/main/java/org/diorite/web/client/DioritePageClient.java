package org.diorite.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class DioritePageClient implements EntryPoint
{
    @Override
    public void onModuleLoad()
    {
        this.jsInit();
        RootPanel.get("content").add(new Button("Czesc"));

    }

    private native void jsInit()/*-{
        $('.button-collapse').sideNav();
        console.log("dupa");
    }-*/;
}
