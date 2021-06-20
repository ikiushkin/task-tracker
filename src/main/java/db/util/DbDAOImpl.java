package db.util;

import db.util.dao.ProjectDAO;
import db.util.dao.TaskDAO;
import db.util.dao.UserDAO;
import entity.Project;
import entity.Subtask;
import entity.Task;

public class DbDAOImpl {

    private ProjectDAO projectDAO = new ProjectDAO();
    private UserDAO userDAO = new UserDAO();
    private TaskDAO taskDAO = new TaskDAO();

    public void print(Class tClass) {
        projectDAO.printBDContent(projectDAO.getList(tClass));
    }

    public Object findById(Class tClass, Long id) {
        return projectDAO.findById(tClass, id);
    }

    public void addObject(Object o) {
        projectDAO.addInDB(o);
    }

    public void addUserToProject(Long prId, Long usId) {
        projectDAO.addUserToProject(prId, usId);
    }

    public void addTaskToUser(Long usId, Task task) {
        userDAO.addTaskToUser(usId, task);
    }

    public void addSubtaskToTask(Long tskId, Subtask subtask) {
        taskDAO.addSubtaskToTask(tskId, subtask);
    }
}
