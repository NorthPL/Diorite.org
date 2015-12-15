package org.diorite.web.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import org.diorite.web.client.activities.RawHtmlPageActivity;
import org.diorite.web.client.places.RawHtmlPagePlace;

public class DioriteActivityMapper implements ActivityMapper
{
    @Override
    public Activity getActivity(final Place place)
    {
        if (place instanceof RawHtmlPagePlace)
        {
            return new RawHtmlPageActivity((RawHtmlPagePlace) place);
        }
        return null;
    }
}
