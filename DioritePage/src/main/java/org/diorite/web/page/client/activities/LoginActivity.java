package org.diorite.web.page.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.views.authentication.LoginFormView;
import org.diorite.web.page.client.views.authentication.PasswordLostView;
import org.diorite.web.page.client.views.authentication.RegisterFormView;

@SuppressWarnings("ClassHasNoToStringMethod")
public class LoginActivity extends AbstractActivity
{
    private final LoginPlace loginPlace;

    public LoginActivity(final LoginPlace loginPlace)
    {
        this.loginPlace = loginPlace;
    }

    public LoginPlace getLoginPlace()
    {
        return this.loginPlace;
    }

    @Override
    public void start(final AcceptsOneWidget acceptsOneWidget, final EventBus eventBus)
    {
        switch (this.loginPlace.getAction())
        {
            case LOGIN:
                acceptsOneWidget.setWidget(new LoginFormView());
                break;
            case REGISTER:
                acceptsOneWidget.setWidget(new RegisterFormView());
                break;
            case RESET_PASSWORD:
                acceptsOneWidget.setWidget(new PasswordLostView());
                break;
            default:
                // TODO show error
                break;
        }
    }
}
