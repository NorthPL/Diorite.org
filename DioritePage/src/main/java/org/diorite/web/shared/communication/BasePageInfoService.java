package org.diorite.web.shared.communication;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@SuppressWarnings("HardcodedFileSeparator")
@RemoteServiceRelativePath(value = "/api/baseInfo")
public interface BasePageInfoService extends RemoteService
{
    String getPageHeader();
}
