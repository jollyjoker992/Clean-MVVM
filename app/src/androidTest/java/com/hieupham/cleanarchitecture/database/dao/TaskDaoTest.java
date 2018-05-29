package com.hieupham.cleanarchitecture.database.dao;

import com.hieupham.cleanarchitecture.database.data.DataProvider;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.source.local.api.dao.TaskDao;
import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;
import java.util.List;
import org.junit.Test;

/**
 * Created by hieupham on 5/25/18.
 */

public class TaskDaoTest extends DaoTest<TaskDao> {

    @Test
    public void verifySaveTask() {

        final Task expectedTask = DataProvider.task7();
        dao.delete();

        dao.save(expectedTask);
        Maybe<Task> stream = dao.getById(expectedTask.getUid());
        TestObserver<Task> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedTask);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifySaveTasks() {

        final List<Task> expectedTasks = DataProvider.tasks();
        dao.delete();

        dao.save(expectedTasks);
        Maybe<List<Task>> stream = dao.get();
        TestObserver<List<Task>> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedTasks);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetById() {

        final Task expectedTask = DataProvider.task1();
        dao.save(expectedTask);

        Maybe<Task> stream = dao.getById(expectedTask.getUid());
        TestObserver<Task> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedTask);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskOwner() {

        final List<Task> expectedTask = DataProvider.tasksByOwner();
        final String owner = expectedTask.get(0).getOwnerUid();
        dao.save(expectedTask);

        Maybe<List<Task>> stream = dao.getByOwner(owner);
        TestObserver<List<Task>> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedTask);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerStatus() {

        final List<Task> expectedTask = DataProvider.tasksByOwnerStatus();
        final String owner = expectedTask.get(0).getOwnerUid();
        final String status = expectedTask.get(0).getStatus();
        dao.save(expectedTask);

        Maybe<List<Task>> stream = dao.getByOwner(owner, status);
        TestObserver<List<Task>> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedTask);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerMultiStatus() {

        final List<Task> expectedTask = DataProvider.tasksByOwnerMultiStatus();
        final String owner = expectedTask.get(0).getOwnerUid();
        final String[] status =
                new String[] { expectedTask.get(0).getStatus(), expectedTask.get(1).getStatus() };
        dao.save(expectedTask);

        Maybe<List<Task>> stream = dao.getByOwner(owner, status);
        TestObserver<List<Task>> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedTask);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTasks() {

        final List<Task> expectedTask = DataProvider.tasks();
        dao.save(expectedTask);

        Maybe<List<Task>> stream = dao.get();
        TestObserver<List<Task>> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedTask);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }
}
