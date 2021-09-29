package dao;

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

//    public Auto findAutoById(int id) {
//        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Auto.class, id);
//    }

//    public List<Coordinates> findAll() {
//        List<Coordinates> coordinates = (List<Coordinates>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("select * from Coordinates;").list();
//        return coordinates;
//    }
}

