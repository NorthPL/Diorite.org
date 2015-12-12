package org.diorite.web.client.views;

import com.google.gwt.user.client.ui.FlowPanel;

import org.diorite.web.client.DioritePageView;

public class ErrorView extends FlowPanel implements DioritePageView
{
    @Override
    public String getPageTitle()
    {
        return "Error";
    }
}
