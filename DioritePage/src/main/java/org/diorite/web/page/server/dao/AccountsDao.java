package org.diorite.web.page.server.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.diorite.web.page.server.DioritePageServer;
import org.diorite.web.page.shared.models.Account;

public class AccountsDao
{
    public void insertOrUpdateAccount(final Account account)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(account);

            transaction.commit();
        }
    }

    public Account getAccountById(final int id)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Criteria criteria = session.createCriteria(Account.class);
            criteria.add(Restrictions.eq("id", id));

            return (Account) criteria.uniqueResult();
        }
    }

    public Account getAccountByName(final String name)
    {
        try (Session session = DioritePageServer.getInstance().getHibernateSessionFactory().openSession())
        {
            final Criteria criteria = session.createCriteria(Account.class);
            criteria.add(Restrictions.eq("username", name));

            return (Account) criteria.uniqueResult();
        }
    }
}
