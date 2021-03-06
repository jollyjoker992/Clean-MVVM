package com.hieupham.cleanarchitecture.utils;

import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.model.User;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hieupham on 5/18/18.
 */

public class DataProvider {

    public static Maybe<List<Task>> maybeTasksOwnerSuccess() {
        List<Task> tasks = new ArrayList<Task>() {{
            add(task1());
            add(task2());
            add(task3());
            add(task4());
            add(task5());
            add(task6());
        }};
        return Maybe.just(tasks);
    }

    public static Maybe<List<Task>> maybeTasksOwnerDelaySuccess() {
        return maybeTasksOwnerSuccess().delay(3, TimeUnit.SECONDS);
    }

    public static Maybe<List<Task>> maybeTasksOwnerError() {
        return Maybe.error(new SocketTimeoutException("Timeout"));
    }

    public static Maybe<List<Task>> maybeTasksOwnerErrorDelay() {
        return Maybe.just(1)
                .delay(3, TimeUnit.SECONDS)
                .flatMap(new Function<Integer, MaybeSource<List<Task>>>() {
                    @Override
                    public MaybeSource<List<Task>> apply(Integer integer) throws Exception {
                        return Maybe.error(new SocketTimeoutException(
                                "Timeout when trying connect with server"));
                    }
                });
    }

    public static Maybe<User> maybeUserSuccess() {
        return Maybe.just(user1());
    }

    public static Task task1() {
        return new Task("001", "Title1", "123", Task.Status.IN_PROGRESS,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/RedcrestedTuraco.jpg/1280px-RedcrestedTuraco.jpg");
    }

    public static Task task2() {
        return new Task("002", "Title2", "123", Task.Status.DONE,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Red-crested_Turaco_RWD.jpg/1280px-Red-crested_Turaco_RWD.jpg");
    }

    public static Task task3() {
        return new Task("003", "Title3", "123", Task.Status.IN_PROGRESS,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Tauraco_erythrolophus.JPG/800px-Tauraco_erythrolophus.JPG");
    }

    public static Task task4() {
        return new Task("004", "Title4", "123", Task.Status.REOPEN,
                "https://upload.wikimedia.org/wikipedia/commons/8/82/RedCrestedTuracoHead.jpg");
    }

    public static Task task5() {
        return new Task("005", "Title5", "123", Task.Status.RESOLVED,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG");
    }

    public static Task task6() {
        return new Task("006", "Title6", "123", Task.Status.TODO,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG");
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
}
