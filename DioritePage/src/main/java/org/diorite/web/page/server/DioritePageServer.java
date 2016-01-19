package org.diorite.web.page.server;

import static org.diorite.web.page.server.settings.SettingsConstants.BASE_PAGE_URL;

import javax.persistence.Entity;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.Properties;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import org.diorite.web.page.server.dao.AccountsDao;
import org.diorite.web.page.server.dao.GroupsDao;
import org.diorite.web.page.server.settings.DioritePageSettings;

@SuppressWarnings("ClassHasNoToStringMethod")
public class DioritePageServer implements ServletContextListener
{
    private static DioritePageServer instance;
    private final Logger logger = Logger.getLogger("DioritePage");
    private SessionFactory hibernateSessionFactory;
    private DioritePageSettings settings;
    private GroupsDao groupsDao;
    private AccountsDao accountsDao;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent)
    {
        this.logger.info("Launching DioritePage server...");
        instance = this;
        this.initHibernate();
        this.settings = new DioritePageSettings();
        this.groupsDao = new GroupsDao();
        this.accountsDao = new AccountsDao();
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
        properties.setProperty("hibernate.hbm2ddl.import_files", "classpath:/default_data.sql");

        properties.setProperty("hibernate.c3p0.min_size", "1");
        properties.setProperty("hibernate.c3p0.max_size", "50");
        properties.setProperty("hibernate.c3p0.timeout", "300");
        properties.setProperty("hibernate.c3p0.max_statements", "50");
        properties.setProperty("hibernate.c3p0.idle_test_period", "30");

        final Configuration configuration = new Configuration();

        if (Thread.currentThread().getContextClassLoader().getResource("dev_hibernate.cfg.xml") == null) // TODO selecting configuration by environmental variables
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

        new Reflections("org.diorite.web.page").getTypesAnnotatedWith(Entity.class).forEach(configuration::addAnnotatedClass);

        this.hibernateSessionFactory = configuration.buildSessionFactory();
    }

    public DioritePageSettings getSettings()
    {
        return this.settings;
    }

    public GroupsDao getGroups()
    {
        return this.groupsDao;
    }

    public AccountsDao getAccounts()
    {
        return this.accountsDao;
    }

    public String getBaseUrl()
    {
        return this.settings.get(BASE_PAGE_URL);
    }
}
