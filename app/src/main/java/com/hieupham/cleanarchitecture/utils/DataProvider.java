package com.hieupham.cleanarchitecture.utils;

import com.hieupham.cleanarchitecture.data.model.Task;
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
            add(new Task("Title1", "123", Task.Status.IN_PROGRESS,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/RedcrestedTuraco.jpg/1280px-RedcrestedTuraco.jpg"));
            add(new Task("Title2", "123", Task.Status.DONE,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Red-crested_Turaco_RWD.jpg/1280px-Red-crested_Turaco_RWD.jpg"));
            add(new Task("Title3", "123", Task.Status.IN_PROGRESS,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Tauraco_erythrolophus.JPG/800px-Tauraco_erythrolophus.JPG"));
            add(new Task("Title4", "123", Task.Status.REOPEN,
                    "https://upload.wikimedia.org/wikipedia/commons/8/82/RedCrestedTuracoHead.jpg"));
            add(new Task("Title5", "123", Task.Status.RESOLVED,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG"));
            add(new Task("Title6", "123", Task.Status.TODO,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG"));
        }};
        return Maybe.just(tasks);
    }

    public static Maybe<List<Task>> maybeTasksOwnerDelaySuccess() {
        List<Task> tasks = new ArrayList<Task>() {{
            add(new Task("Title1", "123", Task.Status.IN_PROGRESS,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/RedcrestedTuraco.jpg/1280px-RedcrestedTuraco.jpg"));
            add(new Task("Title2", "123", Task.Status.DONE,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Red-crested_Turaco_RWD.jpg/1280px-Red-crested_Turaco_RWD.jpg"));
            add(new Task("Title3", "123", Task.Status.IN_PROGRESS,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Tauraco_erythrolophus.JPG/800px-Tauraco_erythrolophus.JPG"));
            add(new Task("Title4", "123", Task.Status.REOPEN,
                    "https://upload.wikimedia.org/wikipedia/commons/8/82/RedCrestedTuracoHead.jpg"));
            add(new Task("Title5", "123", Task.Status.RESOLVED,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG"));
            add(new Task("Title6", "123", Task.Status.TODO,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Zuliz.JPG/1280px-Zuliz.JPG"));
        }};
        return Maybe.just(tasks).delay(3, TimeUnit.SECONDS);
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
}
