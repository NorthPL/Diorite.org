package org.diorite.web.page.server.settings.listeners;

import java.util.function.Consumer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.web.page.server.settings.SettingsEntry;

public abstract class SettingsListener
{
    protected final String[] keys;

    public SettingsListener(final String[] keys)
    {
        this.keys = keys;
    }

    public String[] getKeys()
    {
        return this.keys;
    }

    public abstract Consumer<SettingsEntry> getListener();

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("keys", this.keys).toString();
    }
}
