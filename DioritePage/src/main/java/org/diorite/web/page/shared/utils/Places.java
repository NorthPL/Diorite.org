package org.diorite.web.page.shared.utils;

public enum Places
{
    STATIC_PAGE(1),
    FORUM(0);

    private final int arguments;

    Places(final int arguments)
    {
        this.arguments = arguments;
    }

    public int getArguments()
    {
        return this.arguments;
    }
}
