package org.diorite.web.page.client.views.authentication;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import org.diorite.web.page.client.DioritePageClient;
import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.utils.SimpleAsyncCallback;
import org.diorite.web.page.client.utils.ui.InputLabel;
import org.diorite.web.page.shared.models.LoginCredientals;
import org.diorite.web.page.shared.models.UserContext;

public class LoginFormView extends FlowPanel
{
    public LoginFormView()
    {
        this.setStyleName("loginForm");

        final Label welcomeText = new Label("Hi");
        welcomeText.getElement().setAttribute("style", "text-align: center; font-size: 250%;");

        final Label errorLabel = new Label("Invalid username or password");
        errorLabel.getElement().getStyle().setColor("red");
        errorLabel.setVisible(false);

        final Button loginButton = new Button("Log in");
        loginButton.addStyleName("btn waves-effect waves-light");

        final InputLabel username = new InputLabel("Your username");
        final InputLabel password = new InputLabel("Your password", true);

        loginButton.addClickHandler(clickEvent -> DioritePageClient.getClientInstance().getUserManager().login(new LoginCredientals(username.getValue(), password.getValue()), new SimpleAsyncCallback<UserContext>()
        {
            @Override
            public void onFailure(final Throwable throwable)
            {
                errorLabel.setVisible(true);
            }
        }));

        final Anchor registerLink = new Anchor("Don't have an account?");
        registerLink.getElement().getStyle().setWidth(100, Style.Unit.PCT);
        registerLink.addClickHandler(clickEvent -> DioritePageClient.getClientInstance().navigate(new LoginPlace(LoginPlace.Action.REGISTER)));

        final Anchor passwordLostLink = new Anchor("Forgotten your password?");
        passwordLostLink.getElement().getStyle().setWidth(100, Style.Unit.PCT);
        passwordLostLink.addClickHandler(clickEvent -> DioritePageClient.getClientInstance().navigate(new LoginPlace(LoginPlace.Action.RESET_PASSWORD)));


        this.add(welcomeText);
        this.add(errorLabel);
        this.add(username);
        this.add(password);
        this.add(loginButton);
        this.add(registerLink);
        this.add(passwordLostLink);
    }
}
