package cz.cvut.fel.wa2.stankmic.ukol02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class JPAMain {

    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        final Persons p = new Persons();
        p.setFirstName("John");
        p.setLastName("Doe");

        configHibernate();

        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        session.save(p);
        List<Persons> list = session.createQuery("from Persons p").list();
        tx.commit();
        session.flush();
    }

    private static void configHibernate() {
        final Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Persons.class);
        final Properties props = cfg.getProperties();
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(props)
                .build();

        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }
}
