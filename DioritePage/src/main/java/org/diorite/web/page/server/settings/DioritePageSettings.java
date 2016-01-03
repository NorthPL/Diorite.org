package org.diorite.web.page.server.settings;

import static org.diorite.web.page.server.settings.SettingsConstants.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.diorite.web.page.server.DioritePageServer;
import org.diorite.web.page.server.settings.listeners.ManySettingsListener;
import org.diorite.web.page.server.settings.listeners.SettingsListener;
import org.diorite.web.page.server.settings.listeners.SimpleSettingsListener;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class DioritePageSettings
{
    private final Collection<SettingsListener> listeners = new ArrayList<>(10);

    {
        this.defaultEntry(new SettingsEntry(MAX_USERNAME_LENGTH, SettingsEntry.Type.INTEGER, 20));
        this.defaultEntry(new SettingsEntry(MIN_USERNAME_LENGTH, SettingsEntry.Type.INTEGER, 3));

        this.defaultEntry(new SettingsEntry(GUEST_GROUP, SettingsEntry.Type.INTEGER, 0/*TODO*/));
        this.defaultEntry(new SettingsEntry(DEFAULT_GROUP, SettingsEntry.Type.INTEGER, 1/*TODO*/));
    }

    private void defaultEntry(final SettingsEntry entry)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Criteria criteria = session.createCriteria(SettingsEntry.class);
            criteria.add(Restrictions.eq("name", entry.getName()));
            if (criteria.uniqueResult() == null)
            {
                final Transaction transaction = session.beginTransaction();

                session.save(entry);

                transaction.commit();
            }
        }

        this.callListeners(entry);
    }

    public void addListener(final SettingsListener settingsListener)
    {
        this.listeners.add(settingsListener);
    }

    public void addListener(final String key, final Consumer<SettingsEntry> listener)
    {
        this.addListener(new SimpleSettingsListener(key, listener));
    }

    public void addListener(final String[] keys, final Consumer<Map<String, SettingsEntry>> listener)
    {
        this.addListener(new ManySettingsListener(keys, listener));
    }

    private void callListeners(final SettingsEntry settingsEntry)
    {
        this.listeners.stream().filter(listener -> ArrayUtils.contains(listener.getKeys(), settingsEntry.getName())).forEach(listener -> listener.getListener().accept(settingsEntry));
    }

    public void set(final SettingsEntry settingsEntry)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(settingsEntry);

            transaction.commit();
        }

        this.callListeners(settingsEntry);
    }

    public SettingsEntry getAsSettingsEntry(final String key)
    {
        final SettingsEntry result;

        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Criteria criteria = session.createCriteria(SettingsEntry.class);
            criteria.add(Restrictions.eq("name", key));

            result = (SettingsEntry) criteria.uniqueResult();
        }

        return result;
    }

    public <T> T get(final String key)
    {
        //noinspection unchecked
        return (T) this.getAsSettingsEntry(key).getValue();
    }
}
