package org.diorite.web.page.client;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceTokenizer;

import org.diorite.web.page.client.places.AdminControlPanelPlace;
import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.places.RawHtmlPagePlace;

@SuppressWarnings({"ClassHasNoToStringMethod", "HardcodedFileSeparator"})
public final class DioritePlaceHistoryMapper implements PlaceHistoryMapper
{
    private final Collection<TokenizerData> tokenizers = new ArrayList<>(5);

    {
        this.tokenizers.add(new TokenizerData("authorization", LoginPlace.class, GWT.create(LoginPlace.Tokenizer.class)));
        this.tokenizers.add(new TokenizerData("page", RawHtmlPagePlace.class, GWT.create(RawHtmlPagePlace.Tokenizer.class)));
        this.tokenizers.add(new TokenizerData("admin", AdminControlPanelPlace.class, GWT.create(AdminControlPanelPlace.Tokenizer.class)));
    }

    private TokenizerData tokenizerDataByToken(final String token)
    {
        for (final TokenizerData tk : this.tokenizers)
        {
            if (token.equals(tk.tag))
            {
                return tk;
            }
        }
        return null;
    }

    private TokenizerData tokenizerDataByPlace(final Place place)
    {
        for (final TokenizerData tk : this.tokenizers)
        {
            if (place.getClass().equals(tk.placeClass))
            {
                return tk;
            }
        }
        return null;
    }

    @Override
    public Place getPlace(final String token)
    {
        final int slashLocation = token.indexOf('/');
        final String place = token.substring(0, slashLocation);
        final String rest = token.substring(slashLocation + 1, token.length());

        if (place.isEmpty())
        {
            return null;
        }

        final TokenizerData tokenizerData = this.tokenizerDataByToken(place);

        if (tokenizerData == null)
        {
            return null;
        }

        return tokenizerData.tokenizer.getPlace(rest);
    }

    @Override
    public String getToken(final Place place)
    {
        final TokenizerData tokenizerData = this.tokenizerDataByPlace(place);

        if (tokenizerData == null)
        {
            return "";
        }

        return tokenizerData.tag + "/" + tokenizerData.tokenizer.getToken(place);
    }

    private final class TokenizerData
    {
        private final String tag;
        private final Class<? extends Place> placeClass;
        private final PlaceTokenizer<Place> tokenizer;

        private TokenizerData(final String tag, final Class<? extends Place> placeClass, final PlaceTokenizer<Place> tokenizer)
        {
            this.tag = tag;
            this.placeClass = placeClass;
            this.tokenizer = tokenizer;
        }
    }
}
