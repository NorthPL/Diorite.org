package org.diorite.web.page.shared.models;

import java.io.Serializable;

import org.diorite.web.page.shared.utils.Places;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class PageData implements Serializable
{
    private Places   places;
    private String[] arguments;

    public PageData()
    {
    }

    public PageData(final Places places, final String... arguments)
    {
        this.places = places;
        this.arguments = arguments;

        if (this.places.getArguments() != arguments.length)
        {
            throw new IllegalArgumentException();
        }
    }

    public Places getPlaces()
    {
        return this.places;
    }

    public void setPlaces(final Places places)
    {
        this.places = places;
    }

    public String[] getArguments()
    {
        return this.arguments;
    }

    public void setArguments(final String[] arguments)
    {
        this.arguments = arguments;
    }
}
