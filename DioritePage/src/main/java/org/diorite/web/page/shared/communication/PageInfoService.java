package org.diorite.web.page.shared.communication;

import com.google.gwt.user.client.rpc.RemoteService;

public interface PageInfoService extends RemoteService
{
    String getRawHtmlPageContent(String pageName);
}
