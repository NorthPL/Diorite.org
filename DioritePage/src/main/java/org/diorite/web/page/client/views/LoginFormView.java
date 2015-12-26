package org.diorite.web.page.client.views;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import org.diorite.web.page.client.utils.ui.InputLabel;

public class LoginFormView extends FlowPanel
{
    public LoginFormView()
    {
        this.setStyleName("loginForm");

        final Label welcomeText = new Label("Hi");
        welcomeText.getElement().setAttribute("style", "text-align: center; font-size: 250%;");

        final Button loginButton = new Button("Log in");
        loginButton.addStyleName("btn waves-effect waves-light");

        final InputLabel username = new InputLabel("Your username");

        final InputLabel password = new InputLabel("Your password", true);


        this.add(welcomeText);
        this.add(username);
        this.add(password);
        this.add(loginButton);
    }
}
