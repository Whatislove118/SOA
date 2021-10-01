package dao;

import data.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.HibernateSessionFactoryUtil;

import java.util.List;

public class CityDAO {


    public static City findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(City.class, id);
    }

    public static void save(City city) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(city);
        tx1.commit();
        session.close();
    }

    public static void update(City city) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(city);
        tx1.commit();
        session.close();
    }

    public static void delete(City city) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(city);
        tx1.commit();
        session.close();
    }

    public static List<City> all(){
        return (List<City>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From City").list();
    }
}
