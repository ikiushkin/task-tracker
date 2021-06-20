package db.util.dao;

import entity.Subtask;
import entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TaskDAO extends DefaultDAO {

    private SessionFactory sessionFactory;
    Transaction transaction;

    public TaskDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addSubtaskToTask(Long taskId, Subtask subtask) {
        Task taskFromDb = findById(Task.class, taskId);
        if (taskFromDb == null) {
            System.out.println("Task not found!");
            return;
        }
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (subtask != null) {
                taskFromDb.addSubtask(subtask);
            } else {
                System.out.println("Subtask is empty!");
                return;
            }
            session.saveOrUpdate(taskFromDb);
            transaction.commit();
            System.out.println("Subtask added!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Operation failed!");
        }
    }
}
