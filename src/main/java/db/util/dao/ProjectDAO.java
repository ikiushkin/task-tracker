package db.util.dao;

import entity.Project;
import entity.Report;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProjectDAO extends DefaultDAO {

    private SessionFactory sessionFactory;
    Transaction transaction;

    public ProjectDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addUserToProject(Long projectId, Long userId) {
            Project p = findById(Project.class, projectId);
            if (p == null) {
                System.out.println("Project not found!");
                return;
            }
            User u = findById(User.class, userId);
            if (u == null) {
                System.out.println("User not found!");
            } else {
                try (Session session = sessionFactory.openSession()) {
                    transaction = session.beginTransaction();
                    p.addUser(u);
                    session.saveOrUpdate(p);
                    transaction.commit();
                    System.out.println(u.getName() + " added to " + p.getName());
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                }
                System.out.println("Operation failed!");
            }
        }
    }

    public Report getReport(Long projectID, Long userID) {
        Project project = findById(Project.class, projectID);

        if (project != null) {
            List<User> users = project.getUsers();
            User user = null;

            for (User u : users) {
                if (u.getId().equals(userID)) {
                    user = u;
                    break;
                }
            }

            return new Report(project, user);
        }
        return null;
    }
}
