package org.diorite.web.page.client;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.utils.ConsumerAsyncCallback;
import org.diorite.web.page.client.utils.HtmlFiveHistorian;
import org.diorite.web.page.client.utils.PlacesMapper;
import org.diorite.web.page.client.utils.SimpleAsyncCallback;
import org.diorite.web.page.shared.models.MenuEntry;
import org.diorite.web.page.shared.models.UserContext;

@SuppressWarnings("ClassHasNoToStringMethod")
public class DioritePageClient implements EntryPoint
{
    private static DioritePageClient clientInstance;
    private final Logger          logger          = Logger.getLogger("DioritePageCMS");
    private final SimplePanel     appWidget       = new SimplePanel();
    private final EventBus        eventBus        = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(this.eventBus);
    private ActivityMapper activityMapper;
    private ActivityManager activityManager;
    private PlaceHistoryHandler historyHandler;
    private UserManager userManager;

    @Override
    public void onModuleLoad()
    {
        clientInstance = this;
        this.logger.addHandler(new ConsoleLogHandler());

        {
            this.activityMapper = new DioriteActivityMapper();

            this.activityManager = new ActivityManager(this.activityMapper, this.eventBus);
            this.activityManager.setDisplay(this.appWidget);

            final DioritePlaceHistoryMapper placeHistoryMapper = GWT.create(DioritePlaceHistoryMapper.class);
            if (HtmlFiveHistorian.isSupported())
            {
                this.historyHandler = new PlaceHistoryHandler(placeHistoryMapper, new HtmlFiveHistorian());
            }
            else
            {
                this.historyHandler = new PlaceHistoryHandler(placeHistoryMapper);
                this.logger.warning("history.pushState isn't supported!");
            }
            this.historyHandler.register(this.placeController, this.eventBus, null);
        }

        //this.jsInit();
        this.userManager = new UserManager();

        final Anchor logoAnchor = Anchor.wrap(Document.get().getElementById("logo-container"));
        logoAnchor.addClickHandler(clickEvent -> DioritePageClient.this.navigate(null));

        RootPanel.get("content").add(this.appWidget);

        this.historyHandler.handleCurrentHistory();
        if (this.placeController.getWhere() == null) // navigate to default place if application was started without specified one
        {
            this.navigate(null);
        }
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
        DioriteApi.getWebsiteInfoService().getBaseHeaderName(new ConsumerAsyncCallback<>(DOM.getElementById("logo-container")::setInnerText));

        DioriteApi.getWebsiteInfoService().getBaseTitleName(new ConsumerAsyncCallback<>(Window::setTitle));

        final Element menu = Document.get().getElementById("menu-entries-computer");
        for (int i = 0; i < menu.getChildNodes().getLength(); i++)
        {
            final Node node = menu.getChildNodes().getItem(i);
            if (((Element) node).getId().equals("profile-login-button"))
            {
                continue;
            }
            menu.removeChild(node);
        }

        DioriteApi.getWebsiteInfoService().getMenuEntries(new SimpleAsyncCallback<MenuEntry[]>()
        {
            @Override
            public void onSuccess(final MenuEntry[] menuEntries)
            {
                for (final MenuEntry menuEntry : menuEntries)
                {
                    final Element li = Document.get().createLIElement();
                    final Anchor a = new Anchor(menuEntry.getText());

                    a.addClickHandler(event -> DioritePageClient.this.placeController.goTo(PlacesMapper.mapEnumToPlace(menuEntry.getPageData())));

                    HTMLPanel.wrap(li).add(a);
                    menu.insertFirst(li);
                }
            }
        });

        final Anchor profileButton = Anchor.wrap(Document.get().getElementById("profile-login-button"));
        profileButton.setText(this.getUserContext().isLoggedIn() ? "YOUR PROFILE" : "LOG IN");
        profileButton.addClickHandler(clickEvent -> DioritePageClient.this.placeController.goTo(DioritePageClient.this.getUserContext().isLoggedIn() ? null/*TODO*/ : new LoginPlace(LoginPlace.Action.LOGIN)));
    }

    public void navigate(final Place place)
    {
        if (place == null)
        {
            DioriteApi.getWebsiteInfoService().getStartLocation(new ConsumerAsyncCallback<>(pageData -> DioritePageClient.this.placeController.goTo(PlacesMapper.mapEnumToPlace(pageData))));
            return;
        }
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

    public UserManager getUserManager()
    {
        return this.userManager;
    }

    public UserContext getUserContext()
    {
        return this.userManager.getUserContext();
    }

    public Logger getLogger()
    {
        return this.logger;
    }

    public native String getBaseUrl()/*-{
        return $wnd.baseUrl;
    }-*/;
}
