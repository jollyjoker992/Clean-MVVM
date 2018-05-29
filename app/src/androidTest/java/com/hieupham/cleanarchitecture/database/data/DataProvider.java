package com.hieupham.cleanarchitecture.database.data;

import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by hieupham on 5/28/18.
 */

public class DataProvider {

    public static List<Task> tasks1() {
        return new ArrayList<Task>() {{
            add(task1());
            add(task2());
            add(task3());
            add(task4());
            add(task5());
            add(task6());
        }};
    }

    public static List<Task> tasks2() {
        return new ArrayList<Task>() {{
            add(task1());
            add(task2());
            add(task5());
            add(task3());
            add(task4());
        }};
    }

    public static List<Task> tasks3() {
        return new ArrayList<Task>() {{
            add(task4());
            add(task5());
            add(task6());
            add(task1());
            add(task2());
            add(task3());
        }};
    }

    public static List<Task> tasks() {
        return tasks1();
    }

    public static List<Task> tasksByOwner() {
        return new ArrayList<Task>() {{
            add(task1());
            add(task2());
            add(task5());
        }};
    }

    public static List<Task> tasksByOwnerStatus() {
        return new ArrayList<Task>() {{
            add(task2());
            add(task5());
        }};
    }

    public static List<Task> tasksByOwnerMultiStatus() {
        return new ArrayList<Task>() {{
            add(task1());
            add(task2());
            add(task5());
        }};
    }

    public static List<TaskModel> taskModels1() {
        return new ArrayList<TaskModel>() {{
            add(TaskModel.from(task1(), user1()));
            add(TaskModel.from(task2(), user1()));
            add(TaskModel.from(task3(), user1()));
            add(TaskModel.from(task4(), user1()));
            add(TaskModel.from(task5(), user1()));
            add(TaskModel.from(task6(), user1()));
        }};
    }

    public static List<TaskModel> taskModels2() {
        return new ArrayList<TaskModel>() {{
            add(TaskModel.from(task1(), user1()));
            add(TaskModel.from(task2(), user2()));
            add(TaskModel.from(task5(), user5()));
            add(TaskModel.from(task3(), user3()));
            add(TaskModel.from(task4(), user4()));
        }};
    }

    public static List<TaskModel> taskModels3() {
        return new ArrayList<TaskModel>() {{
            add(TaskModel.from(task4(), user4()));
            add(TaskModel.from(task5(), user5()));
            add(TaskModel.from(task6(), user6()));
            add(TaskModel.from(task1(), user1()));
            add(TaskModel.from(task2(), user2()));
            add(TaskModel.from(task3(), user3()));
        }};
    }

    public static Task task1() {
        return new Task("001", "Title1", "001", Task.Status.IN_PROGRESS,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/RedcrestedTuraco.jpg/1280px-RedcrestedTuraco.jpg");
    }

    public static Task task2() {
        return new Task("002", "Title2", "001", Task.Status.DONE,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Red-crested_Turaco_RWD.jpg/1280px-Red-crested_Turaco_RWD.jpg");
    }

    public static Task task3() {
        return new Task("003", "Title3", "002", Task.Status.IN_PROGRESS,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Tauraco_erythrolophus.JPG/800px-Tauraco_erythrolophus.JPG");
    }

    public static Task task4() {
        return new Task("004", "Title4", "002", Task.Status.REOPEN,
                "https://upload.wikimedia.org/wikipedia/commons/8/82/RedCrestedTuracoHead.jpg");
    }

    public static Task task5() {
        return new Task("005", "Title5", "001", Task.Status.DONE,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG");
    }

    public static Task task6() {
        return new Task("006", "Title6", "003", Task.Status.TODO,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG");
    }

    public static Task task7() {
        return new Task("007", "Title7", "003", Task.Status.RESOLVED,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG");
    }

    public static List<User> users() {
        return new ArrayList<User>() {{
            add(user1());
            add(user2());
            add(user3());
            add(user4());
            add(user5());
            add(user6());
        }};
    }

    public static User user1() {
        return new User("001", "Name1", "Email1");
    }

    public static User user2() {
        return new User("002", "Name2", "Email2");
    }

    public static User user3() {
        return new User("003", "Name3", "Email3");
    }

    public static User user4() {
        return new User("004", "Name4", "Email4");
    }

    public static User user5() {
        return new User("005", "Name5", "Email5");
    }

    public static User user6() {
        return new User("006", "Name6", "Email6");
    }

    public static List<Task> emptyTasks() {
        return new ArrayList<>();
    }

    public static SQLException sqlException() {
        return new SQLException("SQLite exception has occurred");
    }

    public static TimeoutException timeoutException() {
        return new TimeoutException("Timeout Exception has occurred");
    }
}
