package org.diorite.web.page.server.communication;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.diorite.web.page.shared.communication.AuthenticationService;
import org.diorite.web.page.shared.exceptions.InvalidCredientalsException;
import org.diorite.web.page.shared.models.LoginCredientals;
import org.diorite.web.page.shared.models.UserContext;

public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService
{
    @Override
    public UserContext login(final LoginCredientals loginCredientals) throws InvalidCredientalsException
    {
        // TODO check credientals
        // TODO return user context
        return null;
    }

    @Override
    public UserContext getContext()
    {
        return new UserContext();
    }

    @Override
    public void logout()
    {

    }
}
