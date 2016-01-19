package org.diorite.web.page.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import org.diorite.web.page.shared.communication.AuthenticationService;
import org.diorite.web.page.shared.communication.AuthenticationServiceAsync;
import org.diorite.web.page.shared.communication.PageInfoService;
import org.diorite.web.page.shared.communication.PageInfoServiceAsync;
import org.diorite.web.page.shared.communication.WebsiteInfoService;
import org.diorite.web.page.shared.communication.WebsiteInfoServiceAsync;

public final class DioriteApi
{
    private static final WebsiteInfoServiceAsync    websiteInfoService    = fixDir((WebsiteInfoServiceAsync) GWT.create(WebsiteInfoService.class), "websiteInfo");
    private static final PageInfoServiceAsync       pageInfoService       = fixDir((PageInfoServiceAsync) GWT.create(PageInfoService.class), "pageInfo");
    private static final AuthenticationServiceAsync authenticationService = fixDir((AuthenticationServiceAsync) GWT.create(AuthenticationService.class), "authentication");

    private DioriteApi()
    {
    }

    private static <T> T fixDir(final T serviceObject, final String location)
    {
        final ServiceDefTarget service = (ServiceDefTarget) serviceObject;
        service.setServiceEntryPoint(DioritePageClient.getClientInstance().getBaseUrl() + "/api/" + location);

        return serviceObject;
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
