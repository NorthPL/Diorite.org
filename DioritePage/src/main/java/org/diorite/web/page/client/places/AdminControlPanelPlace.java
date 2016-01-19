package org.diorite.web.page.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

@SuppressWarnings("ClassHasNoToStringMethod")
public class AdminControlPanelPlace extends Place
{
    private final ControlPanelPlace place;
    private final String            parameter;

    public AdminControlPanelPlace(final ControlPanelPlace place, final String parameter)
    {
        if ((place.hasParameter && ((parameter == null) || parameter.isEmpty())) || ((! place.hasParameter) && (parameter != null)))
        {
            throw new IllegalArgumentException();
        }
        this.place = place;
        this.parameter = parameter;
    }

    public ControlPanelPlace getPlace()
    {
        return this.place;
    }

    public String getParameter()
    {
        return this.parameter;
    }

    public AdminControlPanelPlace(final ControlPanelPlace place)
    {
        this(place, null);
    }

    public enum ControlPanelPlace
    {
        WELCOME,

        USER_LIST,
        USER_EDIT(true),

        PAGE_LIST,
        PAGE_EDIT(true);

        private final boolean hasParameter;

        ControlPanelPlace(final boolean hasParameter)
        {
            this.hasParameter = hasParameter;
        }

        ControlPanelPlace()
        {
            this(false);
        }

        public boolean isHasParameter()
        {
            return this.hasParameter;
        }
    }

    public static class Tokenizer implements PlaceTokenizer<AdminControlPanelPlace>
    {
        @Override
        public AdminControlPanelPlace getPlace(final String s)
        {
            final String[] splitted = s.split("\\/");

            if (splitted.length > 2)
            {
                throw new IllegalArgumentException();
            }

            final ControlPanelPlace place = ControlPanelPlace.valueOf(splitted[0]);

            if (place.hasParameter)
            {
                return new AdminControlPanelPlace(place, splitted[1]);
            }

            return new AdminControlPanelPlace(place);
        }

        @Override
        public String getToken(final AdminControlPanelPlace loginPlace)
        {
            //noinspection HardcodedFileSeparator
            return loginPlace.place.name() + (loginPlace.place.hasParameter ? "/" + loginPlace.parameter : null);
        }
    }
}
