package com.alexis.tarotapp.api.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by alzayon on 6/25/2017.
 * http://www.topjavatutorial.com/frameworks/hibernate/adding-hibernate-dao-layer-in-existing-maven-project/
 */
public class SessionUtil {

    private static SessionUtil instance=new SessionUtil();
    private SessionFactory sessionFactory;

    public static SessionUtil getInstance(){
        return instance;
    }

    private SessionUtil(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        //https://stackoverflow.com/questions/8621906/is-buildsessionfactory-deprecated-in-hibernate-4
        //sessionFactory = configuration.buildSessionFactory();

        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        return session;
    }
}
