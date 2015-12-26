package org.diorite.web.page.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

@SuppressWarnings("ClassHasNoToStringMethod")
public class LoginPlace extends Place
{
    private Action action;

    public LoginPlace(final Action action)
    {
        this.action = action;
    }

    public Action getAction()
    {
        return this.action;
    }

    public void setAction(final Action action)
    {
        this.action = action;
    }

    public enum Action
    {
        LOGIN, REGISTER, RESET_PASSWORD;
    }

    public static class Tokenizer implements PlaceTokenizer<LoginPlace>
    {
        @Override
        public LoginPlace getPlace(final String s)
        {
            return new LoginPlace(Action.valueOf(s));
        }

        @Override
        public String getToken(final LoginPlace loginPlace)
        {
            return loginPlace.action.name();
        }
    }
}
