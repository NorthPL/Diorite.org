package org.diorite.web.page.server.communication;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.diorite.web.page.shared.communication.WebsiteInfoService;
import org.diorite.web.page.shared.models.MenuEntry;
import org.diorite.web.page.shared.models.PageData;
import org.diorite.web.page.shared.utils.Places;

public class WebsiteInfoServiceImpl extends RemoteServiceServlet implements WebsiteInfoService
{
    @Override
    public String getBaseTitleName()
    {
        return "Hello";
    }

    @Override
    public String getBaseHeaderName()
    {
        return "World of Diorite"; // :D
    }

    @Override
    public PageData getStartLocation()
    {
        return new PageData(Places.STATIC_PAGE, "landing");
    }

    @Override
    public MenuEntry[] getMenuEntries()
    {
        return new MenuEntry[] { new MenuEntry("test", new PageData(Places.STATIC_PAGE, "test")) };
    }
}
