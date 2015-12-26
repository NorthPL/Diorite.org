package org.diorite.web.page.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import org.diorite.web.page.client.activities.LoginActivity;
import org.diorite.web.page.client.activities.RawHtmlPageActivity;
import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.places.RawHtmlPagePlace;

public class DioriteActivityMapper implements ActivityMapper
{
    @Override
    public Activity getActivity(final Place place)
    {
        if (place instanceof RawHtmlPagePlace)
        {
            return new RawHtmlPageActivity((RawHtmlPagePlace) place);
        }

        if (place instanceof LoginPlace)
        {
            return new LoginActivity((LoginPlace) place);
        }
        return null;
    }
}
