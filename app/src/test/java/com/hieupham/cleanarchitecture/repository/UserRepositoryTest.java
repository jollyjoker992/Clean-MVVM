package com.hieupham.cleanarchitecture.repository;

import com.hieupham.cleanarchitecture.data.DataProvider;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.UserRepository;
import com.hieupham.cleanarchitecture.data.source.local.UserLocalDataSource;
import com.hieupham.cleanarchitecture.data.source.remote.UserRemoteDataSource;
import io.reactivex.Maybe;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

/**
 * Created by hieupham on 5/22/18.
 */

public class UserRepositoryTest extends RepositoryTest {

    @Mock
    UserLocalDataSource localDataSource;

    @Mock
    UserRemoteDataSource remoteDataSource;

    @InjectMocks
    UserRepository repo;

    @Test
    public void verifyGetUserByIdLocalSuccess() {
        final String uid = "uid";
        final User user = DataProvider.user1();
        final TimeoutException unexpectedException = DataProvider.timeoutException();

        when(localDataSource.getUserById(uid)).thenReturn(Maybe.just(user));
        when(remoteDataSource.getUserById(uid)).thenReturn(Maybe.<User>error(unexpectedException));
        Maybe<User> obsGetUserById = repo.getUserById(uid);
        TestObserver<User> observer = new TestObserver<>();
        obsGetUserById.subscribe(observer);

        observer.assertValue(user);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetUserByIdLocalError() {
        final String uid = "uid";
        final User user = DataProvider.user1();
        final SQLException expectedException = DataProvider.sqlException();
        final TimeoutException unexpectedException = DataProvider.timeoutException();

        when(localDataSource.getUserById(uid)).thenReturn(Maybe.<User>error(expectedException));
        when(remoteDataSource.getUserById(uid)).thenReturn(Maybe.just(user));
        Maybe<User> obsGetUserById = repo.getUserById(uid);
        TestObserver<User> observer = new TestObserver<>();
        obsGetUserById.subscribe(observer);

        observer.assertError(expectedException);
        observer.assertErrorMessage(expectedException.getMessage());
        observer.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return expectedException.getClass().isInstance(throwable)
                        && !unexpectedException.getClass().isInstance(throwable);
            }
        });
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetUserByIdRemoteSuccess() {
        final String uid = "uid";
        final User user = DataProvider.user1();

        when(localDataSource.getUserById(uid)).thenReturn(Maybe.<User>empty());
        when(remoteDataSource.getUserById(uid)).thenReturn(Maybe.just(user));
        Maybe<User> obsGetUserById = repo.getUserById(uid);
        TestObserver<User> observer = new TestObserver<>();
        obsGetUserById.subscribe(observer);

        observer.assertValue(user);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetUserByIdRemoteError() {

        final String uid = "uid";
        final SQLException unexpectedException = DataProvider.sqlException();
        final TimeoutException expectedException = DataProvider.timeoutException();

        when(localDataSource.getUserById(uid)).thenReturn(Maybe.<User>empty());
        when(remoteDataSource.getUserById(uid)).thenReturn(Maybe.<User>error(expectedException));
        Maybe<User> obsGetUserById = repo.getUserById(uid);
        TestObserver<User> observer = new TestObserver<>();
        obsGetUserById.subscribe(observer);

        observer.assertError(expectedException);
        observer.assertErrorMessage(expectedException.getMessage());
        observer.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return expectedException.getClass().isInstance(throwable)
                        && !unexpectedException.getClass().isInstance(throwable);
            }
        });
        observer.assertNotComplete();
        observer.assertTerminated();
    }
}
