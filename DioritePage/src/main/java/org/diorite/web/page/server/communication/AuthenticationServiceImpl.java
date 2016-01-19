package org.diorite.web.page.server.communication;

import static org.diorite.web.page.server.settings.SettingsConstants.MAX_USERNAME_LENGTH;
import static org.diorite.web.page.server.settings.SettingsConstants.MIN_USERNAME_LENGTH;


import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import org.diorite.web.page.server.DioritePageServer;
import org.diorite.web.page.server.settings.AutoRefreshable;
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
    private static final String USER_SESSION_ATTRIBUTE_NAME = "diorite_user_context";
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
        //this.emailValidator.validate(email); // TODO fix validator

        if (DioritePageServer.getInstance().getAccounts().getAccountByName(username) != null)
        {
            throw new IllegalArgumentException("This nick already exists");
        }

        DioritePageServer.getInstance().getAccounts().insertOrUpdateAccount(new Account(username, null, password, email, DioritePageServer.getInstance().getGroups().getDefaultUserGroup()));
    }

    @Override
    public UserContext login(final LoginCredientals loginCredientals) throws InvalidCredientalsException
    {
        final UserContext newContext;
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Criteria loginCriteria = session.createCriteria(Account.class);

            loginCriteria.add(Restrictions.eq("username", loginCredientals.getUsername()));
            loginCriteria.add(Restrictions.eq("password", loginCredientals.getPassword())); // TODO Hash password

            final Account account = (Account) loginCriteria.uniqueResult();
            if (account == null)
            {
                throw new IllegalArgumentException();
            }
            newContext = new UserContext(account);
        }

        final HttpSession session = this.getThreadLocalRequest().getSession();
        session.setAttribute(USER_SESSION_ATTRIBUTE_NAME, newContext);

        return newContext;
    }

    @Override
    public UserContext getContext()
    {
        final HttpSession session = this.getThreadLocalRequest().getSession();
        final UserContext userContext = (UserContext) session.getAttribute(USER_SESSION_ATTRIBUTE_NAME);

        if (userContext == null)
        {
            return new UserContext(DioritePageServer.getInstance().getGroups().getGuestGroup());
        }

        return userContext;
    }

    @Override
    public void logout()
    {
        this.getThreadLocalRequest().getSession().removeAttribute(USER_SESSION_ATTRIBUTE_NAME);
    }
}
