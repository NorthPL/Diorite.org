package org.diorite.web.page.server.communication;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.diorite.web.page.shared.communication.PageInfoService;

public class PageInfoServiceImpl extends RemoteServiceServlet implements PageInfoService
{
    @Override
    public String getRawHtmlPageContent(final String pageName)
    {
        return "<b>hello</b><br />// TODO Landing page";
    }
}
