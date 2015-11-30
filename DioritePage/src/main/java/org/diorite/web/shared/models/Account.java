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
    @Column(name = "password")
    private String password; // TODO add hashing
    @Column(name = "email", nullable = false)
    private String email;
    // TODO add all required fields

    public Account()
    {
        // enterprise bean must have empty constructor
    }

    public Account(final String username, final String password, final String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
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
