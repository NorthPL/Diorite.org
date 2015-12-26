package org.diorite.web.page.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import org.diorite.web.page.client.places.LoginPlace;
import org.diorite.web.page.client.places.RawHtmlPagePlace;

@WithTokenizers({RawHtmlPagePlace.Tokenizer.class, LoginPlace.Tokenizer.class})
public interface DioritePlaceHistoryMapper extends PlaceHistoryMapper
{
}
