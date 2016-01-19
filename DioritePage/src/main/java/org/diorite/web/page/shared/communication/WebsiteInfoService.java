package org.diorite.web.page.shared.communication;

import com.google.gwt.user.client.rpc.RemoteService;

import org.diorite.web.page.shared.models.MenuEntry;
import org.diorite.web.page.shared.models.PageData;

public interface WebsiteInfoService extends RemoteService
{
    /**
     * @return Text which should be displayed in browser's title bar
     */
    String getBaseTitleName();

    /**
     * @return Text which should be displayed in left upper corner of page
     */
    String getBaseHeaderName();

    PageData getStartLocation();

    MenuEntry[] getMenuEntries();
}
