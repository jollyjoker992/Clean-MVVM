package com.hieupham.cleanarchitecture.repository;

import com.hieupham.cleanarchitecture.data.DataProvider;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.data.source.local.TaskLocalDataSource;
import com.hieupham.cleanarchitecture.data.source.remote.TaskRemoteDataSource;
import io.reactivex.Maybe;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

/**
 * Created by hieupham on 5/22/18.
 */

public class TaskRepositoryTest extends RepositoryTest {

    @Mock
    TaskRemoteDataSource remoteDataSource;

    @Mock
    TaskLocalDataSource localDataSource;

    @InjectMocks
    TaskRepository repository;

    @Test
    public void verifyGetTaskByOwnerLocalSuccess() {
        // Given
        final String uid = "uid";
        final List<Task> tasks = DataProvider.tasks1();
        when(localDataSource.getByOwner(uid)).thenReturn(Maybe.just(tasks));
        Maybe<List<Task>> obsGetTaskByOwner = repository.getTasksByOwner(uid);

        // When
        TestObserver<List<Task>> observer = new TestObserver<>();
        obsGetTaskByOwner.subscribe(observer);

        // Then
        observer.assertValue(tasks);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerLocalError() {
        final String uid = "uid";
        final SQLException exception = DataProvider.sqlException();
        when(localDataSource.getByOwner(uid)).thenReturn(Maybe.<List<Task>>error(exception));

        Maybe<List<Task>> obsGetTaskByOwner = repository.getTasksByOwner(uid);
        TestObserver<List<Task>> observer = new TestObserver<>();
        obsGetTaskByOwner.subscribe(observer);

        observer.assertError(exception.getClass());
        observer.assertErrorMessage(exception.getMessage());
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerRemoteSuccess() {
        final String uid = "uid";
        final List<Task> tasks = DataProvider.tasks1();
        when(localDataSource.getByOwner(uid)).thenReturn(Maybe.just(DataProvider.emptyTasks()));
        when(remoteDataSource.getTasksByOwner(uid)).thenReturn(Maybe.just(tasks));

        Maybe<List<Task>> obsGetTaskByOwner = repository.getTasksByOwner(uid);
        TestObserver<List<Task>> observer = new TestObserver<>();
        obsGetTaskByOwner.subscribe(observer);

        observer.assertValue(tasks);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerRemoteError() {
        final String uid = "uid";
        final TimeoutException exception = DataProvider.timeoutException();
        when(localDataSource.getByOwner(uid)).thenReturn(Maybe.just(DataProvider.emptyTasks()));
        when(remoteDataSource.getTasksByOwner(uid)).thenReturn(Maybe.<List<Task>>error(exception));

        Maybe<List<Task>> obsGetTaskByOwner = repository.getTasksByOwner(uid);
        TestObserver<List<Task>> observer = new TestObserver<>();
        obsGetTaskByOwner.subscribe(observer);

        observer.assertError(exception.getClass());
        observer.assertErrorMessage(exception.getMessage());
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByIdLocalSuccess() {
        final String id = "id";
        final Task task1 = DataProvider.task1();
        final Task task2 = DataProvider.task2();
        when(localDataSource.getById(id)).thenReturn(Maybe.just(task1));
        when(remoteDataSource.getTaskById(id)).thenReturn(Maybe.just(task2));

        Maybe<Task> obsGetTaskById = repository.getTaskById(id);
        TestObserver<Task> observer = new TestObserver<>();
        obsGetTaskById.subscribe(observer);

        observer.assertValue(task1);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByIdLocalError() {
        final String id = "id";
        final Task task = DataProvider.task1();
        final SQLException expectedException = DataProvider.sqlException();
        final TimeoutException unexpectedException = DataProvider.timeoutException();
        when(localDataSource.getById(id)).thenReturn(Maybe.<Task>error(expectedException));
        when(remoteDataSource.getTaskById(id)).thenReturn(Maybe.just(task));

        Maybe<Task> obsGetTaskById = repository.getTaskById(id);
        TestObserver<Task> observer = new TestObserver<>();
        obsGetTaskById.subscribe(observer);

        observer.assertError(expectedException.getClass());
        observer.assertErrorMessage(expectedException.getMessage());
        observer.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return !unexpectedException.getClass().isInstance(throwable);
            }
        });
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByIdRemoteSuccess() {
        final String id = "id";
        final Task task = DataProvider.task2();
        when(localDataSource.getById(id)).thenReturn(Maybe.<Task>empty());
        when(remoteDataSource.getTaskById(id)).thenReturn(Maybe.just(task));

        Maybe<Task> obsGetTaskById = repository.getTaskById(id);
        TestObserver<Task> observer = new TestObserver<>();
        obsGetTaskById.subscribe(observer);

        observer.assertValue(task);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByIdRemoteError() {
        final String id = "id";
        final SQLException unexpectedException = DataProvider.sqlException();
        final TimeoutException expectedException = DataProvider.timeoutException();
        when(localDataSource.getById(id)).thenReturn(Maybe.<Task>empty());
        when(remoteDataSource.getTaskById(id)).thenReturn(Maybe.<Task>error(expectedException));

        Maybe<Task> obsGetTaskById = repository.getTaskById(id);
        TestObserver<Task> observer = new TestObserver<>();
        obsGetTaskById.subscribe(observer);

        observer.assertError(expectedException.getClass());
        observer.assertErrorMessage(expectedException.getMessage());
        observer.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return !unexpectedException.getClass().isInstance(throwable);
            }
        });
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void verifySaveTaskSuccess() {

    }

    @Test
    public void verifySaveTaskRemoteError() {

    }

    @Test
    public void verifySaveTaskLocalError() {

    }

    @Test
    public void verifyUpdateTaskStatusSuccess() {

    }

    @Test
    public void verifyUpdateTaskStatusRemoteError() {

    }

    @Test
    public void verifyUpdateTaskStatusLocalError() {

    }
}
