package com.hieupham.cleanarchitecture.database.dao;

import com.hieupham.cleanarchitecture.database.data.DataProvider;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.local.api.dao.UserDao;
import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;
import java.util.List;
import org.junit.Test;

/**
 * Created by hieupham on 5/28/18.
 */

public class UserDaoTest extends DaoTest<UserDao> {

    @Test
    public void verifySaveUser() {

        final User expectedUser = DataProvider.user1();
        dao.delete();

        dao.save(expectedUser);
        Maybe<User> stream = dao.getById(expectedUser.getUid());
        TestObserver<User> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedUser);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifySaveUsers() {

        final List<User> expectedUsers = DataProvider.users();
        dao.delete();

        dao.save(expectedUsers);
        Maybe<List<User>> stream = dao.get();
        TestObserver<List<User>> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedUsers);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyGetById() {

        final User expectedUser = DataProvider.user1();

        Maybe<User> stream = dao.getById(expectedUser.getUid());
        TestObserver<User> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(expectedUser);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }
}
