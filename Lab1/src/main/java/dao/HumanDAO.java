package dao;

import data.Coordinates;
import data.Human;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.HibernateSessionFactoryUtil;

public class HumanDAO {


    public static Human findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Human.class, id);
    }

    public static void save(Human human) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(human);
        tx1.commit();
        session.close();
    }

    public static void update(Human human) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(human);
        tx1.commit();
        session.close();
    }

    public static void delete(Human human) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(human);
        tx1.commit();
        session.close();
    }

}
