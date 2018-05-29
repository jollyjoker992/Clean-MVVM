package com.hieupham.cleanarchitecture.feature.tasklist;

import com.hieupham.cleanarchitecture.data.DataProvider;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.data.source.UserRepository;
import com.hieupham.cleanarchitecture.feature.UseCaseTest;
import com.hieupham.cleanarchitecture.utils.livedata.Transformer;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import io.reactivex.Maybe;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

/**
 * Created by hieupham on 5/22/18.
 */

public class TaskListUseCaseTest extends UseCaseTest {

    @Mock
    Transformer transformer;

    @Mock
    TaskRepository taskRepo;

    @Mock
    UserRepository userRepo;

    @InjectMocks
    TaskListUseCase useCase;

    @Test
    public void verifyGetTaskByOwnerSuccess() {

        final String uid = "uid";
        final List<Task> tasks = DataProvider.tasks1();
        final User user = DataProvider.user1();
        final List<TaskModel> taskModels = DataProvider.taskModelsByUser();

        when(taskRepo.getTasksByOwner(uid)).thenReturn(Maybe.just(tasks));
        when(userRepo.getUserById(uid)).thenReturn(Maybe.just(user));
        Maybe<List<TaskModel>> source = useCase.obsTasksByOwnerTest(uid);
        TestObserver<List<TaskModel>> observer = new TestObserver<>();
        source.subscribe(observer);

        observer.assertValue(taskModels);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerError1() {

        final String uid = "uid";
        final SQLException expectedException = DataProvider.sqlException();

        when(taskRepo.getTasksByOwner(uid)).thenReturn(Maybe.<List<Task>>error(expectedException));
        Maybe<List<TaskModel>> source = useCase.obsTasksByOwnerTest(uid);
        TestObserver<List<TaskModel>> observer = new TestObserver<>();
        source.subscribe(observer);

        observer.assertError(expectedException);
        observer.assertErrorMessage(expectedException.getMessage());
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerError2() {

        final String uid = "uid";
        final SQLException expectedException = DataProvider.sqlException();
        final List<Task> tasks = DataProvider.tasks1();

        when(taskRepo.getTasksByOwner(uid)).thenReturn(Maybe.just(tasks));
        when(userRepo.getUserById(uid)).thenReturn(Maybe.<User>error(expectedException));
        Maybe<List<TaskModel>> source = useCase.obsTasksByOwnerTest(uid);
        TestObserver<List<TaskModel>> observer = new TestObserver<>();
        source.subscribe(observer);

        observer.assertError(expectedException);
        observer.assertErrorMessage(expectedException.getMessage());
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetTaskByOwnerError3() {

        final String uid = "uid";
        final List<Task> tasks = DataProvider.tasks1();
        final User user = DataProvider.user2();
        final List<TaskModel> taskModels = DataProvider.taskModelsByUser();

        when(taskRepo.getTasksByOwner(uid)).thenReturn(Maybe.just(tasks));
        when(userRepo.getUserById(uid)).thenReturn(Maybe.just(user));
        Maybe<List<TaskModel>> source = useCase.obsTasksByOwnerTest(uid);
        TestObserver<List<TaskModel>> observer = new TestObserver<>();
        source.subscribe(observer);

        observer.assertValue(new Predicate<List<TaskModel>>() {
            @Override
            public boolean test(List<TaskModel> result) throws Exception {
                return !taskModels.equals(result);
            }
        });
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }
}
