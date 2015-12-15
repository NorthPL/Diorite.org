package org.diorite.web.shared.communication;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@SuppressWarnings("HardcodedFileSeparator")
@RemoteServiceRelativePath(value = "../../api/pageInfo")
public interface PageInfoService extends RemoteService
{
    String getRawHtmlPageContent(String pageName);
}
