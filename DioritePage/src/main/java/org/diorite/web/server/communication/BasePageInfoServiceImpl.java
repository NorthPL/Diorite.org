package org.diorite.web.server.communication;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.diorite.web.shared.communication.BasePageInfoService;

public class BasePageInfoServiceImpl extends RemoteServiceServlet implements BasePageInfoService
{
    @Override
    public String getPageHeader()
    {
        return "Hello world"; // TODO from settings
    }
}
