package org.diorite.web.shared.models;

import org.junit.Test;

import junit.framework.TestCase;

public class AccountTest extends TestCase
{
    @Test
    public void testDisplayNameNull()
    {
        final Account testAccount = new Account("testUser", null, null, null);

        assertEquals("testUser", testAccount.getUsername());
        assertEquals(null, testAccount.getDisplayName());

        assertEquals("testUser", testAccount.getName());

        assertEquals(false, testAccount.hasDisplayName());
    }

    @Test
    public void testDisplayNameNotNull()
    {
        final Account testAccount = new Account("testUser", "funnyUser", null, null);

        assertEquals("testUser", testAccount.getUsername());
        assertEquals("funnyUser", testAccount.getDisplayName());

        assertEquals("funnyUser", testAccount.getName());

        assertEquals(true, testAccount.hasDisplayName());
    }
}