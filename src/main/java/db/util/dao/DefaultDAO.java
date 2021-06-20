package db.util.dao;

import entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public abstract class DefaultDAO {

    private final SessionFactory sessionFactory;
    Transaction transaction;

    public DefaultDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public <T> void addInDB(T entity) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            System.out.println("The recording was successful!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Addition failed!");
        }
    }

    public <T> void removeFromDB(T t) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            System.out.println("Deletion was successful!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Deletion failed!");
        }
    }

    public <T> List<T> getList(Class<T> clazz) {
        try(Session session = sessionFactory.openSession()) {
            String tableName = clazz.getSimpleName();
            List<T> list = session.createQuery("from " + tableName, clazz).list();
            return list;
        }
    }

    public <T> void printBDContent(List<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    public <T> T findById(Class<T> tClass, Long id) {
        try(Session session = sessionFactory.openSession()) {
            T entity = session.find(tClass, id);
            return entity;
        }
    }

    public List<Task> findByName(String value) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Task where name = :paramName", Task.class);
            query.setParameter("paramName",value);
            List<Task> list = query.getResultList();
            return list;
        }
    }
}
