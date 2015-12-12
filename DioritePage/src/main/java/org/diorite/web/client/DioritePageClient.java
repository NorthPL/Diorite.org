package org.diorite.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import org.diorite.web.client.communication.BasePageInfoServiceAsync;
import org.diorite.web.shared.communication.BasePageInfoService;

public class DioritePageClient implements EntryPoint
{
    private final BasePageInfoServiceAsync basePageInfo = (BasePageInfoServiceAsync) GWT.create(BasePageInfoService.class);

    @Override
    public void onModuleLoad()
    {
        System.out.println("test1");

        //this.jsInit();
        RootPanel.get("content").add(new Button("Czesc"));

        System.out.println("test2s");

        GWT.log("dupa2");

        this.basePageInfo.getPageHeader(new AsyncCallback<String>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
                DOM.getElementById("logo-container").setInnerText("fail");
                System.out.println("error");
            }

            @Override
            public void onSuccess(final String s)
            {
                DOM.getElementById("logo-container").setInnerText(s);
                System.out.println("ok -> " + s);
            }
        });
    }

    private native void jsInit()/*-{
        $('.button-collapse').sideNav();
    }-*/;
}
