package org.diorite.web .page.server;

import javax.persistence.Entity;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.Properties;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.reflections.Reflections;

import org.diorite.web.page.shared.models.Group;

public class DioritePageServer implements ServletContextListener
{
    private static DioritePageServer instance;
    private final Logger logger = Logger.getLogger("DioritePage");
    private SessionFactory hibernateSessionFactory;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent)
    {
        this.logger.info("Launching DioritePage server...");
        instance = this;
        this.initHibernate();

        try (Session session = this.hibernateSessionFactory.openSession())
        {
            final Transaction transaction = session.beginTransaction();

            session.save(new Group("testGroup"));

            transaction.commit();
        }
        catch (final HibernateException exception)
        {
            exception.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent)
    {
        if (this.hibernateSessionFactory != null)
        {
            this.hibernateSessionFactory.close();
        }
    }

    public static DioritePageServer getInstance()
    {
        return instance;
    }

    public Logger getLogger()
    {
        return this.logger;
    }

    public SessionFactory getHibernateSessionFactory()
    {
        return this.hibernateSessionFactory;
    }

    private void initHibernate()
    {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        final Configuration configuration = new Configuration();

        if (Thread.currentThread().getContextClassLoader().getResource("dev_hibernate.cfg.xml") == null)
        {
            configuration.configure();
        }
        else
        {
            this.logger.warning("Launching DioritePage server with development configuration.");
            configuration.configure("dev_hibernate.cfg.xml");
        }

        configuration.addProperties(properties);
        configuration.setPhysicalNamingStrategy(new DioriteNamingStrategy());
        //noinspection ConstantConditions
        new Reflections("org.diorite.web.page").getTypesAnnotatedWith(Entity.class).forEach(configuration::addAnnotatedClass);

        this.hibernateSessionFactory = configuration.buildSessionFactory();
    }
}
