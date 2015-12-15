package org.diorite.web.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RawHtmlPagePlace extends Place
{
    private String htmlPageName;

    public RawHtmlPagePlace(final String htmlPageName)
    {
        this.htmlPageName = htmlPageName;
    }

    public String getHtmlPageName()
    {
        return this.htmlPageName;
    }

    public void setHtmlPageName(final String htmlPageName)
    {
        this.htmlPageName = htmlPageName;
    }

    public static class Tokenizer implements PlaceTokenizer<RawHtmlPagePlace>
    {
        @Override
        public RawHtmlPagePlace getPlace(final String s)
        {
            return new RawHtmlPagePlace(s);
        }

        @Override
        public String getToken(final RawHtmlPagePlace rawHtmlPagePlace)
        {
            return rawHtmlPagePlace.getHtmlPageName();
        }
    }
}
