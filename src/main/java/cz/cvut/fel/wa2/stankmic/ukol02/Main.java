package cz.cvut.fel.wa2.stankmic.ukol02;

import cz.cvut.fel.wa2.stankmic.ukol02.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class Main {

    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        final Person p = new Person();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setAddresses(new HashSet<>(1));
        final Address a = new Address();
        a.setCity("Prague");
        p.getAddresses().add(a);

        configHibernate();

        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        session.save(p);
        List<Person> list = session.createQuery("from Person p").list();
        tx.commit();
        session.flush();
        System.exit(0);
    }

    private static void configHibernate() {
        final Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Address.class);
        cfg.addAnnotatedClass(Bus.class);
        cfg.addAnnotatedClass(Car.class);
        cfg.addAnnotatedClass(Person.class);
        cfg.addAnnotatedClass(Phone.class);
        final Properties props = cfg.getProperties();
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(props)
                .build();

        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }
}
