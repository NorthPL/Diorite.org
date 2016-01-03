package org.diorite.web.page.server.settings.listeners;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.web.page.server.DioritePageServer;
import org.diorite.web.page.server.settings.SettingsEntry;

public class ManySettingsListener extends SettingsListener
{
    final Consumer<Map<String, SettingsEntry>> listener;

    public ManySettingsListener(final String[] keys, final Consumer<Map<String, SettingsEntry>> listener)
    {
        super(keys);
        this.listener = listener;
    }

    @Override
    public Consumer<SettingsEntry> getListener()
    {
        return settingsEntry -> {
            final Map<String, SettingsEntry> values = new IdentityHashMap<>(5); // We hope that everyone use SettingsConstants

            for (final String s : ManySettingsListener.this.keys)
            {
                final SettingsEntry entry = DioritePageServer.getInstance().getSettings().getAsSettingsEntry(s);
                if (entry == null)
                {
                    return;
                }

                values.put(entry.getName(), entry);
            }

            values.put(settingsEntry.getName(), settingsEntry);

            this.listener.accept(values);
        };
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("keys", this.keys).append("listener", this.listener).toString();
    }
}
