package org.diorite.web.page.client.views.authentication;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import org.diorite.web.page.client.DioritePageClient;
import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.utils.SimpleAsyncCallback;
import org.diorite.web.page.client.utils.ui.InputLabel;

public class RegisterFormView extends FlowPanel
{
    public RegisterFormView()
    {
        this.setStyleName("loginForm");

        final Label welcomeText = new Label("Create new account");
        welcomeText.getElement().setAttribute("style", "text-align: center; font-size: 250%;");

        final Label errorLabel = new Label("Please fill all fields");
        errorLabel.getElement().getStyle().setColor("red");
        errorLabel.setVisible(false);

        final Button registerButton = new Button("Register");
        registerButton.addStyleName("btn waves-effect waves-light");

        final InputLabel username = new InputLabel("Your username");
        final InputLabel password = new InputLabel("Your password", true);
        final InputLabel passwordRetype = new InputLabel("Retype your password", true);
        final InputLabel email = new InputLabel("Your e-mail");

        registerButton.addClickHandler(clickEvent -> {
            if (! password.getValue().equals(passwordRetype.getValue()))
            {
                errorLabel.setVisible(true);
                errorLabel.setText("Given passwords don't match");
                return;
            }

            DioritePageClient.getClientInstance().getUserManager().register(username.getValue(), password.getValue(), email.getValue(), new SimpleAsyncCallback<Void>()
            {
                @Override
                public void onFailure(final Throwable throwable)
                {
                    errorLabel.setVisible(true);
                    if (throwable.getMessage() != null)
                    {
                        errorLabel.setText(throwable.getMessage());
                    }
                }

                @Override
                public void onSuccess(final Void aVoid)
                {
                    DioritePageClient.getClientInstance().navigate(new LoginPlace(LoginPlace.Action.LOGIN));
                }
            });
        });


        this.add(welcomeText);
        this.add(errorLabel);
        this.add(username);
        this.add(password);
        this.add(passwordRetype);
        this.add(email);
        this.add(registerButton);
    }
}
