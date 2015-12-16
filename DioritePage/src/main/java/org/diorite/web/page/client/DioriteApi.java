package org.diorite.web.page.client;

import com.google.gwt.core.client.GWT;

import org.diorite.web.page.shared.communication.PageInfoService;
import org.diorite.web.page.shared.communication.PageInfoServiceAsync;
import org.diorite.web.page.shared.communication.WebsiteInfoService;
import org.diorite.web.page.shared.communication.WebsiteInfoServiceAsync;

public final class DioriteApi
{
    private static final WebsiteInfoServiceAsync basePageInfoService = (WebsiteInfoServiceAsync) GWT.create(WebsiteInfoService.class);
    private static final PageInfoServiceAsync    pageInfoService     = (PageInfoServiceAsync) GWT.create(PageInfoService.class);

    private DioriteApi()
    {
    }

    public static WebsiteInfoServiceAsync getBasePageInfoService()
    {
        return basePageInfoService;
    }

    public static PageInfoServiceAsync getPageInfoService()
    {
        return pageInfoService;
    }
}
