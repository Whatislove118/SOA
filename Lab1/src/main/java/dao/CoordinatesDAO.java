package dao;

import data.City;
import data.Coordinates;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.HibernateSessionFactoryUtil;

import java.util.List;

public class CoordinatesDAO {

    public static Coordinates findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Coordinates.class, id);
    }

    public static void save(Coordinates coordinates) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(coordinates);
        tx1.commit();
        session.close();
    }

    public static void update(Coordinates coordinates) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(coordinates);
        tx1.commit();
        session.close();
    }

    public static void delete(Coordinates coordinates) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(coordinates);
        tx1.commit();
        session.close();
    }

    public static List<Coordinates> all(){
        return (List<Coordinates>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Coordinates ").list();
    }

}

