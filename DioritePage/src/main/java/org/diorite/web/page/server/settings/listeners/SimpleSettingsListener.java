package org.diorite.web.page.server.settings.listeners;

import java.util.function.Consumer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.web.page.server.settings.SettingsEntry;

public class SimpleSettingsListener extends SettingsListener
{
    private final Consumer<SettingsEntry> consumer;

    public SimpleSettingsListener(final String key, final Consumer<SettingsEntry> consumer)
    {
        super(new String[] {key});
        this.consumer = consumer;
    }

    @Override
    public Consumer<SettingsEntry> getListener()
    {
        return this.consumer;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("consumer", this.consumer).toString();
    }
}
