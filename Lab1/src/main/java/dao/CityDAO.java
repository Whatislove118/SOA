package dao;

import data.City;
import data.Climate;
import data.Government;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.HibernateSessionFactoryUtil;
import services.Utils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static void deleteByClimate(Climate climate){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("delete from City where climate=:c").setParameter("c", climate);
        int count = query.executeUpdate();
        tx1.commit();
        session.close();
        System.out.println(count);
    }

    public static List<City> getByGovernment(Government government, boolean isHigher){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = null;
        if (isHigher){
            query = session.createQuery("From City where government>:s");
        }else{
            query = session.createQuery("From City where government<:s");
        }
        return (List<City>) query.setParameter("s", government).getResultList();
    }

    public static List<City> all(){
        return (List<City>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From City").list();
    }


//
}
