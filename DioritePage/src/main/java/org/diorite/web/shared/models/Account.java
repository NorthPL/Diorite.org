package org.diorite.web.shared.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents a user account
 */
@Entity
@Table(name = "accounts")
public class Account
{
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "displayname", nullable = true, unique = true)
    private String displayName;
    @Column(name = "password")
    private transient String password; // TODO add hashing
    @Column(name = "email", nullable = false)
    private String email;
    // TODO add all required fields

    public Account()
    {
    }

    public Account(final String username, final String displayName, final String password, final String email)
    {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.email = email;
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
}
