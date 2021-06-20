package db.util.dao;

import entity.Task;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAO extends DefaultDAO {

    private SessionFactory sessionFactory;
    Transaction transaction;

    public UserDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addTaskToUser(Long userId, Task task) {
        try(Session session = sessionFactory.openSession()) {
            if (findById(User.class, userId) == null) {
                System.out.println("User not found!");
                return;
            }
            Task taskFromDb = findByName(task.getName()).get(0);
            User userFromDb = findById(User.class, userId);
            transaction = session.beginTransaction();
            if (taskFromDb != null) {
                userFromDb.addTask(taskFromDb);
            } else {
                userFromDb.addTask(task);
            }
            session.saveOrUpdate(userFromDb);
            transaction.commit();
            System.out.println("Task added!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
