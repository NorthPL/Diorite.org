package org.diorite.web.page.client;

import com.google.gwt.core.client.GWT;

import org.diorite.web.page.shared.communication.AuthenticationService;
import org.diorite.web.page.shared.communication.AuthenticationServiceAsync;
import org.diorite.web.page.shared.communication.PageInfoService;
import org.diorite.web.page.shared.communication.PageInfoServiceAsync;
import org.diorite.web.page.shared.communication.WebsiteInfoService;
import org.diorite.web.page.shared.communication.WebsiteInfoServiceAsync;

public final class DioriteApi
{
    private static final WebsiteInfoServiceAsync    websiteInfoService    = (WebsiteInfoServiceAsync) GWT.create(WebsiteInfoService.class);
    private static final PageInfoServiceAsync       pageInfoService       = (PageInfoServiceAsync) GWT.create(PageInfoService.class);
    private static final AuthenticationServiceAsync authenticationService = (AuthenticationServiceAsync) GWT.create(AuthenticationService.class);

    private DioriteApi()
    {
    }

    public static WebsiteInfoServiceAsync getWebsiteInfoService()
    {
        return websiteInfoService;
    }

    public static PageInfoServiceAsync getPageInfoService()
    {
        return pageInfoService;
    }

    public static AuthenticationServiceAsync getAuthenticationService()
    {
        return authenticationService;
    }
}
