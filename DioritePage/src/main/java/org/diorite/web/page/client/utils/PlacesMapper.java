package org.diorite.web.page.client.utils;

import java.util.EnumMap;
import java.util.Map;

import com.google.gwt.place.shared.Place;

import org.diorite.web.page.client.places.RawHtmlPagePlace;
import org.diorite.web.page.shared.models.PageData;
import org.diorite.web.page.shared.utils.Places;

@SuppressWarnings("Convert2Lambda") // We can't use lambdas in language level 7 :(
public final class PlacesMapper
{
    @SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
    public interface MappingResolver
    {
        Place map(String[] args);
    }

    private static final Map<Places, MappingResolver> mappings = new EnumMap<>(Places.class);

    private PlacesMapper()
    {
    }

    public static void registerMapping(final Places enumPlace, final MappingResolver mapper)
    {
        mappings.put(enumPlace, mapper);
    }

    public static Place mapEnumToPlace(final Places enumPlace, final String... arguments)
    {
        if (enumPlace.getArguments() != arguments.length)
        {
            throw new IllegalArgumentException();
        }
        return mappings.get(enumPlace).map(arguments);
    }

    public static Place mapEnumToPlace(final PageData pageData)
    {
        return mapEnumToPlace(pageData.getPlaces(), pageData.getArguments());
    }

    static
    {
        registerMapping(Places.STATIC_PAGE, new MappingResolver()
        {
            @Override
            public Place map(final String[] args)
            {
                return new RawHtmlPagePlace(args[0]);
            }
        });
        //registerMapping(DefaultPlaces.FORUM, args -> ); // TODO
    }
}
