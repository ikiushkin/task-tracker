package mainapp;

import db.util.DbDAOImpl;
import entity.Project;
import entity.Subtask;
import entity.Task;
import entity.User;

import java.util.Scanner;

public class MenuItemsUtil {

    private static final DbDAOImpl dbDAO = new DbDAOImpl();
    public static int userChoose;

    public static void start() {
        System.out.println("************************** Java console program started **************************");
        System.out.println("This program supports adding, deleting, and displaying projects, users, and tasks.");
        System.out.println("*********************************************************************************** \n");

        System.out.println(
                "Choose your action: \n" +
                        "1 - Show database contents; \n" +
                        "2 - Add to database / Delete from the database; \n" +
                        "3 - Add a user to a project, assign a task to a user, add a subtask; \n" +
                        "4 - Exit program \n"
        );

        MenuItemsUtil.userChoose = MenuItemsUtil.getCorrectNumber(1, 3);

        if (userChoose == 1) {
            showMenuItemOne();
            rerun();
        } else if (MenuItemsUtil.userChoose == 2) {
            showMenuItemTwo();
            rerun();
        } else if (MenuItemsUtil.userChoose == 3) {
            showMenuItemTree();
            rerun();
        } else if (MenuItemsUtil.userChoose == 4) {
            closeProgram();
        }
    }

    public static void showMenuItemOne() {
        System.out.println(
                "Select a further viewing option: \n" +
                        "1 - View all objects: \n" +
                        "2 - Viewing a single object \n"
        );
        userChoose = getCorrectNumber(1, 2);

        /* Get list */
        if (userChoose == 1) {
            System.out.println(
                    "Select the object type: \n" +
                            "1 - Project; \n" +
                            "2 - User \n" +
                            "3 - Task \n"
            );
            userChoose = getCorrectNumber(1, 3);

            if (userChoose == 1) {
                dbDAO.print(Project.class);
            } else if (userChoose == 2) {
                dbDAO.print(User.class);
            } else if (userChoose == 3) {
                dbDAO.print(Task.class);
            }
            /* Get single object */
        } else if (userChoose == 2) {
            System.out.println(
                    "Select the single object type: \n" +
                            "1 - Project; \n" +
                            "2 - User \n" +
                            "3 - Task \n"
            );
            int type = getCorrectNumber(1, 3);

            System.out.println("Select the object id:");
            Long id = Long.parseLong(String.valueOf(new Scanner(System.in).nextInt()));
            if (type == 1) {
                System.out.println(dbDAO.findById(Project.class, id));
            } else if (type == 2) {
                System.out.println(dbDAO.findById(User.class, id));
            } else if (type == 3) {
                System.out.println(dbDAO.findById(Task.class, id));
            }
        }
    }

    public static void showMenuItemTwo() {

        System.out.println(
                "What object do you want to add \n" +
                        "Select object type: \n" +
                        "1 - Project; \n" +
                        "2 - User \n" +
                        "3 - Task \n"
        );
        int type = getCorrectNumber(1, 3);

        System.out.println("Enter the name of the object:");
        String name = new Scanner(System.in).nextLine();
        if (type == 1) {
            dbDAO.addObject(new Project(name));
        } else if (type == 2) {
            dbDAO.addObject(new User(name));
        } else if (type == 3) {
            dbDAO.addObject(new Task(name));
        }
    }

    public static void showMenuItemTree() {
        System.out.println(
                "Enter your action: \n" +
                        "1 - Add a user to the project \n" +
                        "2 - Assign a task to a user \n" +
                        "3 - Add a subtask to task \n"
        );
        userChoose = getCorrectNumber(1, 3);

        if (userChoose == 1) {
            System.out.println("Enter project ID:");
            Long prId = Long.parseLong(String.valueOf(new Scanner(System.in).nextInt()));
            System.out.println("Enter user ID:");
            Long usId = Long.parseLong(String.valueOf(new Scanner(System.in).nextInt()));
            dbDAO.addUserToProject(prId, usId);
        } else if (userChoose == 2) {
            System.out.println("Enter user ID:");
            Long usId = Long.parseLong(String.valueOf(new Scanner(System.in).nextInt()));
            System.out.println("Enter task name:");
            String taskName = new Scanner(System.in).nextLine();
            dbDAO.addTaskToUser(usId, new Task(taskName));
        } else if (userChoose == 3) {
            System.out.println("Enter task ID:");
            Long tskId = Long.parseLong(String.valueOf(new Scanner(System.in).nextInt()));
            System.out.println("Enter subtask name:");
            String subtaskName = new Scanner(System.in).nextLine();
            dbDAO.addSubtaskToTask(tskId, new Subtask(subtaskName));
        }
    }

    public static void rerun() {
        System.out.println("\nWant to go back to the main menu? \n" +
                "1 - Yes; \n" +
                "Any key - Close program"
        );

        try {
            int choose = new Scanner(System.in).nextInt();
            if (choose == 1) {
                start();
            }
        } catch (Exception e) {
            closeProgram();
        }
    }

    public static void closeProgram() {
        System.out.println("\nProgram closed! Bye!");
        System.exit(0);
    }

    private static int getIntValueFromUser() {
        boolean b = false;
        int i;
        String s = null;

        while (!b) {
            System.out.println("Enter a number:");
            s = new Scanner(System.in).nextLine();
            b = isNumber(s);
        }
        i = Integer.parseInt(s);

        return i;
    }

    private static boolean isNumber(String str) {
        return str.matches("-?\\d+");
    }

    public static int getCorrectNumber(int min, int max) {
        boolean b = false;
        int i = 0;

        while (!b) {
            i = getIntValueFromUser();
            if (i >= min && i <= max) {
                b = true;
            } else if (i == 4) {
                closeProgram();
            } else {
                System.out.println("Wrong input! Try again!");
            }
        }
        return i;
    }
}
