package org.diorite.web.page.shared.models;

import java.io.Serializable;

public class MenuEntry implements Serializable
{
    private String text;
    private PageData pageData;

    public MenuEntry()
    {
    }

    public MenuEntry(final String text, final PageData pageData)
    {
        this.text = text;
        this.pageData = pageData;
    }

    public String getText()
    {
        return this.text;
    }

    public void setText(final String text)
    {
        this.text = text;
    }

    public PageData getPageData()
    {
        return this.pageData;
    }

    public void setPageData(final PageData pageData)
    {
        this.pageData = pageData;
    }
}
