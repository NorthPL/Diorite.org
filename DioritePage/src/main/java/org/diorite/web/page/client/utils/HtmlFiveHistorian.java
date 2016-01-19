package org.diorite.web.page.client.utils;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;

import org.diorite.web.page.client.DioritePageClient;

@SuppressWarnings("ClassHasNoToStringMethod")
public class HtmlFiveHistorian implements PlaceHistoryHandler.Historian, HasValueChangeHandlers<String>
{
    private final SimpleEventBus eventBus = new SimpleEventBus();

    public HtmlFiveHistorian()
    {
        this.initEvent();
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> valueChangeHandler)
    {
        return this.eventBus.addHandler(ValueChangeEvent.getType(), valueChangeHandler);
    }

    @Override
    public String getToken()
    {
        final String token = Window.Location.getPath();

        return token.substring(1, (token.endsWith("/") ? (token.length() - 1) : token.length()));
    }

    @Override
    public void newItem(final String token, final boolean issueEvent)
    {
        if (this.getToken().equals(token))
        {
            return;
        }

        final String newToken = this.getBaseUrl() + token;

        this.pushState(newToken);
        if (issueEvent)
        {
            ValueChangeEvent.fire(this, this.getToken());
        }
    }

    private void onPopState()
    {
        ValueChangeEvent.fire(this, this.getToken());
    }

    @Override
    public void fireEvent(final GwtEvent<?> gwtEvent)
    {
        this.eventBus.fireEvent(gwtEvent);
    }

    private String getBaseUrl()
    {
        return DioritePageClient.getClientInstance().getBaseUrl() + "/";
    }

    private native void initEvent() /*-{
        var that = this;
        var oldHandler = $wnd.onpopstate;
        $wnd.onpopstate = $entry(function(e)
        {
            that.@org.diorite.web.page.client.utils.HtmlFiveHistorian::onPopState()();
            if (oldHandler)
            {
                oldHandler();
            }
        });
    }-*/;

    private native void pushState(final String url) /*-{
        $wnd.history.pushState(null, $doc.title, url);
    }-*/;

    public static native boolean isSupported() /*-{
        if ($wnd.history.pushState)
        {
            return true;
        }
        return false;
    }-*/;
}
