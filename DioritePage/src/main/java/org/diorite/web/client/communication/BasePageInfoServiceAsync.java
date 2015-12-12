package org.diorite.web.client.communication;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BasePageInfoServiceAsync
{
    void getPageHeader(AsyncCallback<String> callback);
}
