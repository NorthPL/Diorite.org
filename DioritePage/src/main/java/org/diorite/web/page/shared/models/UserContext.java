package org.diorite.web.page.shared.models;

import java.io.Serializable;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class UserContext implements Serializable
{
    private Account account;
    private Group   group;

    public UserContext()
    {
    }

    public UserContext(final Account account)
    {
        this(account, null);
    }

    public UserContext(final Group group)
    {
        this(null, group);
    }

    private UserContext(final Account account, final Group group)
    {
        if ((account == null) && (group == null))
        {
            throw new IllegalArgumentException();
        }
        this.account = account;
        this.group = group;
    }

    public Account getAccount()
    {
        return this.account;
    }

    public Group getGroup()
    {
        if (this.account == null)
        {
            return this.group;
        }
        return this.account.getGroup();
    }

    public boolean isLoggedIn()
    {
        return this.account != null;
    }
}
