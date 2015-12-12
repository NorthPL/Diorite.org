package org.diorite.web.client.views;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;

import org.diorite.web.client.DioritePageView;

public class RawHtmlView extends HTMLPanel implements DioritePageView
{
    public RawHtmlView(final String body)
    {
        super(body);
    }

    @Override
    public String getPageTitle()
    {
        return null;
    }
}
