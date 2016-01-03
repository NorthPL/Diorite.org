package org.diorite.web.page.shared.utils;

public enum Places
{
    STATIC_PAGE(1, "Specified static page"),
    LOGIN_FORM(0, "Login form"),
    FORUM(0, "Forum main page");

    private final int arguments;
    private final String desciption; // Will be displayed in admin control panel

    Places(final int arguments, final String desciption)
    {
        this.arguments = arguments;
        this.desciption = desciption;
    }

    public int getArguments()
    {
        return this.arguments;
    }

    public String getDesciption()
    {
        return this.desciption;
    }
}
