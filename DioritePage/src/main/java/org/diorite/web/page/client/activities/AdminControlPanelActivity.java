package org.diorite.web.page.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import org.diorite.web.page.client.DioritePageClient;
import org.diorite.web.page.client.places.AdminControlPanelPlace;

public class AdminControlPanelActivity extends AbstractActivity
{
    private final AdminControlPanelPlace place;

    public AdminControlPanelActivity(final AdminControlPanelPlace place)
    {
        this.place = place;
    }

    @Override
    public void start(final AcceptsOneWidget acceptsOneWidget, final EventBus eventBus)
    {
        if (! DioritePageClient.getClientInstance().getUserManager().getUserContext().getGroup().isCanUseAdminCp())
        {
            // TODO Show error
        }


    }

    public void navigatTo()
    {

    }
}
