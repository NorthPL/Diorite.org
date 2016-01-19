package org.diorite.web.page.server.settings;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.web.page.server.DioritePageServer;

public final class AutoRefreshable<T>
{
    private       T                                       cache;
    private final String[]                                settings;
    private final Function<Map<String, SettingsEntry>, T> producer;

    public AutoRefreshable(final String[] settings, final Function<Map<String, SettingsEntry>, T> producer)
    {
        this.settings = settings;
        this.producer = producer;

        DioritePageServer.getInstance().getSettings().addListener(settings, this::produce);
    }

    public AutoRefreshable(final String settings, final Function<Map<String, SettingsEntry>, T> producer)
    {
        this(ArrayUtils.toArray(settings), producer);
    }

    public T get()
    {
        if (this.cache == null)
        {
            this.produce();
        }
        return this.cache;
    }

    private T produce()
    {
        final Map<String, SettingsEntry> settingsEntryMap = new IdentityHashMap<>(5);

        for (final String s : this.settings)
        {
            settingsEntryMap.put(s, DioritePageServer.getInstance().getSettings().getAsSettingsEntry(s));
        }

        return this.produce(settingsEntryMap);
    }

    private T produce(final Map<String, SettingsEntry> settings)
    {
        final T newCache = this.producer.apply(settings);
        this.cache = newCache;
        return newCache;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("cache", this.cache).append("settings", this.settings).append("producer", this.producer).toString();
    }
}
