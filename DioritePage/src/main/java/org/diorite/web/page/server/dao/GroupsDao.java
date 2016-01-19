package org.diorite.web.page.server.dao;

import static org.diorite.web.page.server.settings.SettingsConstants.*;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.diorite.web.page.server.DioritePageServer;
import org.diorite.web.page.server.settings.AutoRefreshable;
import org.diorite.web.page.shared.models.Group;

@SuppressWarnings("ClassHasNoToStringMethod")
public class GroupsDao
{
    private final AutoRefreshable<Group> guestGroup = new AutoRefreshable<>(GUEST_GROUP, values -> this.getGroupById(values.get(GUEST_GROUP).getValue()));
    private final AutoRefreshable<Group> defaultUserGroup = new AutoRefreshable<>(DEFAULT_GROUP, values -> this.getGroupById(values.get(DEFAULT_GROUP).getValue()));

    public void insertOrUpdateGroup(final Group group)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(group);

            transaction.commit();
        }
    }

    public Group getGroupById(final int id)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Criteria criteria = session.createCriteria(Group.class);
            criteria.add(Restrictions.eq("id", id));

            return (Group) criteria.uniqueResult();
        }
    }

    public Group getGroupByName(final String name)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Criteria criteria = session.createCriteria(Group.class);
            criteria.add(Restrictions.eq("name", name));

            return (Group) criteria.uniqueResult();
        }
    }

    public Group getGuestGroup()
    {
        return this.guestGroup.get();
    }

    public Group getDefaultUserGroup()
    {
        return this.defaultUserGroup.get();
    }
}
