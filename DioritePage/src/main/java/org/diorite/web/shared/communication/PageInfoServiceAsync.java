package org.diorite.web.shared.communication;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PageInfoServiceAsync
{
    void getRawHtmlPageContent(String pageName, AsyncCallback<String> callback);
}
