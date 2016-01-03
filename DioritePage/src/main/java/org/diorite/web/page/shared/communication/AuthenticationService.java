package org.diorite.web.page.shared.communication;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import org.diorite.web.page.shared.exceptions.InvalidCredientalsException;
import org.diorite.web.page.shared.models.LoginCredientals;
import org.diorite.web.page.shared.models.UserContext;

@RemoteServiceRelativePath(value = "../../api/authentication")
public interface AuthenticationService extends RemoteService
{
    void register(String username, String password, String email) throws IllegalArgumentException;

    UserContext login(LoginCredientals loginCredientals) throws InvalidCredientalsException;

    UserContext getContext();

    void logout();
}
