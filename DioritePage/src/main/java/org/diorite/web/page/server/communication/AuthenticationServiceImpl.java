package org.diorite.web.page.server.communication;

import static org.diorite.web.page.server.settings.SettingsConstants.*;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.apache.commons.lang3.ArrayUtils;

import org.diorite.web.page.server.DioritePageServer;
import org.diorite.web.page.server.settings.AutoRefreshable;
import org.diorite.web.page.server.settings.DioritePageSettings;
import org.diorite.web.page.shared.communication.AuthenticationService;
import org.diorite.web.page.shared.exceptions.InvalidCredientalsException;
import org.diorite.web.page.shared.models.Account;
import org.diorite.web.page.shared.models.LoginCredientals;
import org.diorite.web.page.shared.models.UserContext;
import org.diorite.web.page.shared.utils.validator.StringValidators;
import org.diorite.web.page.shared.utils.validator.Validator;

@SuppressWarnings("ClassHasNoToStringMethod")
public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService
{
    private final AutoRefreshable<Validator<String>> userNameValidator;
    private final Validator<String> passwordValidator;
    private final Validator<String> emailValidator;

    {
        this.userNameValidator = new AutoRefreshable<>(ArrayUtils.toArray(MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH), values -> {
            return Validator.create(StringValidators.length(values.get(MIN_USERNAME_LENGTH).getValue(), values.get(MAX_USERNAME_LENGTH).getValue(), (s, userNameValidator) -> new IllegalArgumentException("Invalid username")));
        });

        this.passwordValidator = Validator.create(StringValidators.length(6, 50, (s, validator) -> new IllegalArgumentException("Password is too short or too long"))); // TODO move these values to settings

        this.emailValidator = Validator.create(StringValidators.requiredChars((s, validator) -> new IllegalArgumentException("Invalid email"), "@", "."));
    }

    @Override
    public void register(final String username, final String password, final String email) throws IllegalArgumentException
    {
        this.userNameValidator.get().validate(username);
        this.passwordValidator.validate(password);
        this.emailValidator.validate(email);

        if (DioritePageServer.getInstance().getAccounts().getAccountByName(username) != null)
        {
            throw new IllegalArgumentException("This nick already exists");
        }

        DioritePageServer.getInstance().getAccounts().insertOrUpdateAccount(new Account(username, null, password, email, DioritePageServer.getInstance().getGroups().getDefaultUserGroup()));
    }

    @Override
    public UserContext login(final LoginCredientals loginCredientals) throws InvalidCredientalsException
    {
        // TODO check credientals
        // TODO return user context
        throw new IllegalArgumentException();
        //return null;
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
