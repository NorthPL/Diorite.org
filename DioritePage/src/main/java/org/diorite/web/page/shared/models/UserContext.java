package org.diorite.web.page.shared.models;

import java.io.Serializable;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class UserContext implements Serializable
{
    private Account account;

    public UserContext()
    {
    }

    public UserContext(final Account account)
    {
        this.account = account;
    }

    public Account getAccount()
    {
        return this.account;
    }

    public Group getGroup()
    {
        // if account == null, return guest group
        return this.account.getGroup();
    }

    public boolean isLoggedIn()
    {
        return this.account != null;
    }
}
