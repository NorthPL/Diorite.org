package org.diorite.web.page.shared.models;

import java.io.Serializable;

public class LoginCredientals implements Serializable
{
    private String username;
    private String password;

    public LoginCredientals()
    {
    }

    public LoginCredientals(final String username, final String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }
}
