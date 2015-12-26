package org.diorite.web.page.client;

import javax.xml.registry.infomodel.User;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.places.RawHtmlPagePlace;
import org.diorite.web.page.shared.models.UserContext;

@SuppressWarnings("ClassHasNoToStringMethod")
public class DioritePageClient implements EntryPoint
{
    private static DioritePageClient clientInstance;
    private final SimplePanel     appWidget       = new SimplePanel();
    private final EventBus        eventBus        = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(this.eventBus);
    private ActivityMapper activityMapper;
    private ActivityManager activityManager;
    private PlaceHistoryHandler historyHandler;
    private UserContext userContext;

    @Override
    public void onModuleLoad()
    {
        clientInstance = this;

        {
            this.activityMapper = new DioriteActivityMapper();

            this.activityManager = new ActivityManager(this.activityMapper, this.eventBus);
            this.activityManager.setDisplay(this.appWidget);


            this.historyHandler = new PlaceHistoryHandler((PlaceHistoryMapper) GWT.create(DioritePlaceHistoryMapper.class));
            this.historyHandler.register(this.placeController, this.eventBus, null);
        }

        //this.jsInit();
        DioriteApi.getAuthenticationService().getContext(new AsyncCallback<UserContext>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
                // TODO
            }

            @Override
            public void onSuccess(final UserContext userContext)
            {
                DioritePageClient.this.userContext = userContext;
            }
        });
        this.refreshHeader();

        RootPanel.get("content").add(this.appWidget);

        this.historyHandler.handleCurrentHistory();
        //this.placeController.goTo(new RawHtmlPagePlace("examplePage"));
        this.placeController.goTo(new LoginPlace(LoginPlace.Action.LOGIN));
    }

    public static DioritePageClient getClientInstance()
    {
        return clientInstance;
    }

    private native void jsInit()/*-{
        $('.button-collapse').sideNav();
    }-*/;

    public void refreshHeader()
    {
        DioriteApi.getBasePageInfoService().getBaseHeaderName(new AsyncCallback<String>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
            }

            @Override
            public void onSuccess(final String s)
            {
                DOM.getElementById("logo-container").setInnerText(s);
            }
        });

        DioriteApi.getBasePageInfoService().getBaseTitleName(new AsyncCallback<String>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
            }

            @Override
            public void onSuccess(final String s)
            {
                Window.setTitle(s);
            }
        });

        // TODO refresh menu
    }

    public void navigate(final Place place)
    {
        this.placeController.goTo(place);
    }

    public EventBus getEventBus()
    {
        return this.eventBus;
    }

    public PlaceController getPlaceController()
    {
        return this.placeController;
    }

    public ActivityMapper getActivityMapper()
    {
        return this.activityMapper;
    }

    public ActivityManager getActivityManager()
    {
        return this.activityManager;
    }

    public PlaceHistoryHandler getHistoryHandler()
    {
        return this.historyHandler;
    }
}
