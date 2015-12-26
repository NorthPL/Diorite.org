package org.diorite.web.page.server.communication;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.diorite.web.page.shared.communication.WebsiteInfoService;

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
    public String getStartLocation()
    {
        return "hello";
    }
}
