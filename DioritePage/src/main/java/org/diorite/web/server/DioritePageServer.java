package org.diorite.web.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DioritePageServer implements ServletContextListener
{
    private static DioritePageServer instance;
    private SessionFactory hibernateSessionFactory;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent)
    {
        instance = this;
        this.initHibernate();
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent)
    {

    }

    public static DioritePageServer getInstance()
    {
        return instance;
    }

    public SessionFactory getHibernateSessionFactory()
    {
        return this.hibernateSessionFactory;
    }

    private void initHibernate()
    {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

        this.hibernateSessionFactory = new Configuration().addProperties(properties).addPackage("org.diorite.web.shared").addPackage("org.diorite.web.server").buildSessionFactory();
    }
}
