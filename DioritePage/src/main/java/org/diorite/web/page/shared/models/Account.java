package org.diorite.web.page.shared.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.GwtTransient;

/**
 * This class represents a user account
 */
@Entity
@Table(name = "accounts")
public class Account implements Serializable
{
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "displayname", nullable = true, unique = true)
    private String displayName;
    @Column(name = "password")
    @GwtTransient
    private String password; // TODO add hashing
    @Column(name = "email", nullable = false)
    private String email;
    @OneToOne
    private Group group;
    // TODO add all required fields

    public Account()
    {
    }

    public Account(final String username, final String displayName, final String password, final String email, final Group group)
    {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.group = group;
    }

    public String getName()
    {
        return this.hasDisplayName() ? this.displayName : this.username;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public boolean hasDisplayName()
    {
        return this.displayName != null;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }

    public void setDisplayName(final String displayName)
    {
        this.displayName = displayName;
    }

    public String getPassword()
    {
        if (GWT.isClient())
        {
            throw new IllegalStateException("Password is unavailable in client");
        }
        return this.password;
    }

    public void setPassword(final String password) // TODO this method should hash password
    {
        this.password = password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public Group getGroup()
    {
        return this.group;
    }

    public void setGroup(final Group group)
    {
        this.group = group;
    }
}
