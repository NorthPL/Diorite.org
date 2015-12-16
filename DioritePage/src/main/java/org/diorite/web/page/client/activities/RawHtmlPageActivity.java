package org.diorite.web.page.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import org.diorite.web.page.client.DioriteApi;
import org.diorite.web.page.client.places.RawHtmlPagePlace;
import org.diorite.web.page.client.views.RawHtmlPageView;

public class RawHtmlPageActivity extends AbstractActivity
{
    private final String htmlPageName;
    private       String htmlPageContent;

    public RawHtmlPageActivity(final RawHtmlPagePlace place)
    {
        this.htmlPageName = place.getHtmlPageName();
    }

    public String getHtmlPageName()
    {
        return this.htmlPageName;
    }

    public String getHtmlPageContent()
    {
        return this.htmlPageContent;
    }

    @Override
    public void start(final AcceptsOneWidget acceptsOneWidget, final EventBus eventBus)
    {
        DioriteApi.getPageInfoService().getRawHtmlPageContent(this.htmlPageName, new AsyncCallback<String>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
                // TODO
            }

            @Override
            public void onSuccess(final String s)
            {
                RawHtmlPageActivity.this.htmlPageContent = s;
                acceptsOneWidget.setWidget(new RawHtmlPageView(s));
            }
        });
    }
}
