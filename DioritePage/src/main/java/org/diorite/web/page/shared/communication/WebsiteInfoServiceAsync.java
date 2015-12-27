package org.diorite.web.page.shared.communication;

import com.google.gwt.user.client.rpc.AsyncCallback;

import org.diorite.web.page.shared.models.MenuEntry;
import org.diorite.web.page.shared.models.PageData;

public interface WebsiteInfoServiceAsync
{
    void getBaseTitleName(AsyncCallback<String> callback);

    void getBaseHeaderName(AsyncCallback<String> callback);

    void getStartLocation(AsyncCallback<PageData> callback);

    void getMenuEntries(AsyncCallback<MenuEntry[]> callback);
}
