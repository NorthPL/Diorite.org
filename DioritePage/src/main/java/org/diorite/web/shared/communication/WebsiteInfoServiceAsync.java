package org.diorite.web.shared.communication;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WebsiteInfoServiceAsync
{
    void getBaseTitleName(AsyncCallback<String> callback);

    void getBaseHeaderName(AsyncCallback<String> callback);

    void getStartLocation(AsyncCallback<String> callback);
}
