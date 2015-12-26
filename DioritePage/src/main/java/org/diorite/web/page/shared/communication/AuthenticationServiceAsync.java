package org.diorite.web.page.shared.communication;

import com.google.gwt.user.client.rpc.AsyncCallback;

import org.diorite.web.page.shared.models.LoginCredientals;
import org.diorite.web.page.shared.models.UserContext;

public interface AuthenticationServiceAsync
{
    void login(LoginCredientals loginCredientals, AsyncCallback<UserContext> callback);

    void getContext(AsyncCallback<UserContext> callback);

    void logout(AsyncCallback<Void> callback);
}
