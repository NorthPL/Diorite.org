package org.diorite.web.page.shared.communication;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@SuppressWarnings("HardcodedFileSeparator")
@RemoteServiceRelativePath(value = "../../api/websiteInfo")
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

    String getStartLocation();
}
