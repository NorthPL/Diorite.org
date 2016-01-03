package org.diorite.web.page.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import org.diorite.web.page.client.utils.SimpleAsyncCallback;
import org.diorite.web.page.shared.exceptions.NotLoggedInException;
import org.diorite.web.page.shared.models.LoginCredientals;
import org.diorite.web.page.shared.models.UserContext;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class UserManager
{
    private UserContext userContext;

    public UserManager()
    {
        DioriteApi.getAuthenticationService().getContext(new SimpleAsyncCallback<UserContext>()
        {
            @Override
            public void onSuccess(final UserContext userContext)
            {
                UserManager.this.contextChanged(userContext);
            }
        });
    }

    public void contextChanged(final UserContext newContext)
    {
        UserManager.this.userContext = newContext;
        DioritePageClient.getClientInstance().refreshHeader(); // user data downloaded, so we can now refresh header
        DioritePageClient.getClientInstance().navigate(null);
    }

    public UserContext getUserContext()
    {
        return this.userContext;
    }

    public void login(final LoginCredientals credientals, final SimpleAsyncCallback<UserContext> callback)
    {
        DioriteApi.getAuthenticationService().login(credientals, new SimpleAsyncCallback<UserContext>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
                if (callback != null)
                {
                    callback.onFailure(throwable);
                }
                else
                {
                    super.onFailure(throwable);
                }
            }

            @Override
            public void onSuccess(final UserContext userContext)
            {
                UserManager.this.contextChanged(userContext);
                if (callback != null)
                {
                    callback.onSuccess(userContext);
                }
            }
        });
    }

    public void login(final LoginCredientals credientals)
    {
        this.login(credientals, null);
    }

    public void register(final String username, final String password, final String email, final SimpleAsyncCallback<Void> callback)
    {
        DioriteApi.getAuthenticationService().register(username, password, email, new SimpleAsyncCallback<Void>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
                if (callback != null)
                {
                    callback.onFailure(throwable);
                }
                else
                {
                    super.onFailure(throwable);
                }
            }

            @Override
            public void onSuccess(final Void aVoid)
            {
                if (callback != null)
                {
                    callback.onSuccess(null);
                }
            }
        });
    }

    public void register(final String username, final String password, final String email)
    {
        this.register(username, password, email, null);
    }

    public void logout() throws NotLoggedInException
    {

    }
}
