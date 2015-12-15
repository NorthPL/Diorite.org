package org.diorite.web.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import org.diorite.web.client.places.RawHtmlPagePlace;

@WithTokenizers({RawHtmlPagePlace.Tokenizer.class})
public interface DioritePlaceHistoryMapper extends PlaceHistoryMapper
{
}
