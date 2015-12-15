package org.diorite.web.server.communication;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.diorite.web.shared.communication.WebsiteInfoService;

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
        return "Hello";
    }

    @Override
    public String getStartLocation()
    {
        return "hello";
    }
}
