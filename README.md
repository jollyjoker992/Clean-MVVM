# Android Clean Architecture

This repository is built for contributing some clean architectures that make your project work perfectly, maintable, testable and extendable.

# Getting started
This repository contains some common architectures that build in different branches. You can see all available branches [here](https://github.com/jollyjoker992/android-clean-architecture/branches).

# Structure
This branch builds a MVVM structure pattern and absolutely follow its concept.

![](https://i.imgur.com/EnYDF7X.png)

The main idea for this structure is
- **View** - in Android is known as ViewController such as `Activity` or `Fragment` - knows about the ViewModel. It means ViewController has a ViewModel instance and contains logic to handle view interaction. View will send command to ViewModel but only observe data change to receive notification from ViewModel to update UI.
- **ViewModel** contains view business logic such as validation. ViewModel has no any package `android.*` unless support lifecycle package. It helps ViewModel is testable via Mockito when you wanna mock object without any Android components. UseCase is dependency of ViewModel.
- **UseCase** contains data logic processor likes *Repository*. In UseCase, we mix all data business from repositories into a complete *Reactive Flow Stream* and convert it to LiveData before exposing to *ViewModel* 
- **Model** is layer represent data or logic processor. It contains some modules, classes or units to process data in our app such as calling web service or retrieving data from local database. 


Now I will explain some concepts in this structure.

![](https://i.imgur.com/vpiYZ5n.png)

- **ViewController** represents `Activity` or `Fragment`. It's **View** layer in MVVM model. Follow above principle, we are only allowed to inject ViewModel instance or anything else that handle View logic such as permission handler, navigator, dialog manager, `RecyclerAdapter`, etc... into the `Activity` or `Fragment`
- **ViewModel**'s dependencies is Usecases or anything else that handle logic such as location handler. It follows the `Activity` or `Fragment` lifecycle and contain some callback to handle it. You can find it in `BaseViewModel`
- **Usecase** contains data logic processor likes Repositories. It mixes all complex logics in Repositories corresponding expectations and convert to `LiveData` before exposing to *ViewModel*.
- **Repository**'s dependencies is `LocalDataSource` and `RemoteDataSource` that handle logic to retrieve data from local database and remote server. We mix the data from local and remote as *Reactive Flow* (`Flowable`, `Observable`, `Maybe` or etc...) before exposing to `ViewModel`. Repository keeps original logic by Reactive Data Stream and alway ready for adapting with any structure patterns.
- `LocalDataSource` and `RemoteDataSource` exposes a Reactive Flow, **NOT** `LiveData`, they uses RxJava to handle raw logic.

# Dependency Injection
We use [Dagger 2](https://google.github.io/dagger/) for dependency injection implementation in this project.

![](https://image.ibb.co/dgttT8/DI.png)

1. We implement DI object graphes follow the Android component. So, there are 3 layers of Object Graph corresponding with the Android components. 
    - `AppComponent` contains modules that has *Application Scope* and provides all objects at the Application scope. All **Subcomponents** can use everything `AppComponent` provides. `AppComponent` has many subcomponents represent by `ActivityComponent`
    - `ActivityComponent` is implemented in Activity scope that provide everything in an `Activity`. `ActivityComponent` can use everything that are provided in Application Scope via `AppComponent`.
    - `FragmentComponent` is implemented in Fragment scope that provide everything in a `Fragment`. `FragmentComponent` can use everything that are provided in Activity scope via `ActivityComponent`
2. When you define an `Activity`, just extends `BaseAppCompatActivity`, all global variables are marked as `@Inject` is automatically injected. The similar behavior in `Fragment` when you extends `BaseSupportFragment`.

# Testing

I will explain how to test our structure in this article. 

### Local Unit Test
We use [Junit4](https://junit.org/junit4/) and [Mockito](http://site.mockito.org/) for our app testing.

Our structure is designed to separate *Android Components* such as `Context`, `Looper` from Business Handler such as *ViewModel*, *UserCase* or *Repository*, so you can write your local unit test that run on local **JVM** and do not need a physical device to run test. It optimizes your testing time. 

There are 3 main layers need to be tested via local unit test: *ViewModel*, *UseCase* and *Repository*. It's lucky for us because we have *Dependency Injection* and it helps us for easier to define what we should test our code. For example, *Local Datasource* and *Remote Datasource* is *Repository*'s dependencies, so you need them to test a *Repository*. A similar to *UseCase* and *ViewModel*.

##### 1. Repository Testing

![](https://i.imgur.com/8BBiBDm.png)

In Repository Test, we need to mock all dependencies of a real Repository. They are LocalDataSource and RemoteDataSource. Beside that to test a repo, of course, we need to mock a Repository need to be tested.
The purpose is test the logic in a repository work in a right way.

Below is simple test case

```java
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
```

##### 2. Usecase Testing

![](https://i.imgur.com/gJujBIs.png)

In an UseCase Test, we need to mock all dependencies of an UseCase, It usually is some Repositories. Of course, a mock UserCase is need to be defined in this test class. 
The purpose of this test is make the logic in UseCase works well. Typically, It's the data logic is mixed between some Repositories before exposing to ViewModel.

Below is simple test case

```java
@Test
public void verifyGetTaskByOwnerError3() {

    final String uid = "uid";
    final List<Task> tasks = DataProvider.tasks1();
    final User user = DataProvider.user2();
    final List<TaskModel> taskModels = DataProvider.taskModels1();

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
```

##### 3. ViewModel Testing

![](https://i.imgur.com/lotNjUQ.png)

ViewModel Testing need all corresponding dependencies from a real ViewModel. It typically is an UseCase.
The purpose of this test is make sure that the logic in ViewModel works well. Typically, we need to test the ViewModel's logic used from ViewController.

Example code

```java
@Test
public void verifyGetTaskByOwnerSuccess() {
    final String uid = "uid";
    final List<TaskModel> taskModels = DataProvider.taskModels1();
    final Resource<List<TaskModel>> expectedResult = Resource.success(taskModels);
    final MutableLiveData<Resource<List<TaskModel>>> liveData = new MutableLiveData<>();
    liveData.setValue(expectedResult);

    when(useCase.getTasksByOwner(uid)).thenReturn(liveData);
    viewModel.tasks().observeForever(observerTasks);
    viewModel.init(uid);

    verify(observerTasks).onChanged(expectedResult);
    assertTrue("Get tasks is success but not return success status",
            viewModel.tasks().getValue().isSuccessful());
    assertEquals(expectedResult, viewModel.tasks().getValue());
}
```

### Instrument Test
Instrument testing helps us to test our code need an Android Components such as Context, Looper ... and need to be ran in a physical Android device or an emulator.

In this artical, we only implement instrumemt unit test, not intergration or UI test, they maybe implemented later.
We have already implemented local unit test for testing your logic code in Repositories, Usecases and Viewmodels, so in this scope of instrument unit test, we need to implement unit test for database and anything else relate to Android Component such as File management, Sharepreference managerment, etc... I will explain how to test your database in instrument testing.

##### 1. Dao Testing
Verify your DAOs work perfectly by testing them with in memory database of Room. We need to create DAO test class that extend from DaoTest<T> and do not forget to init master data before setting up test in DataManager. We have example for DAO test as following.

```java
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
```

##### 2. Migration Testing
Verify all your migrations work correctly. Room supports MigrationTestHelper helps us to verify the migration perfectly. Below is the sample

```java
@Test
public void verifyMigrationFrom1_2() throws IOException {

    final User verifyUser = DataProvider.user1();

    SupportSQLiteDatabase database =
            migrationTestHelper.createDatabase(DatabaseManager.DATABASE_NAME, 1);
    insertUser(verifyUser, database);
    insertUser(DataProvider.user2(), database);
    insertUser(DataProvider.user3(), database);
    insertUser(DataProvider.user4(), database);
    database.close();

    migrationTestHelper.runMigrationsAndValidate(DatabaseManager.DATABASE_NAME, 2, true,
            MigrationManager.MIGRATION_1_2);

    DatabaseManager databaseManager =
            (DatabaseManager) MigrationUtils.getDatabaseAfterPerformingMigrations(
                    migrationTestHelper, DatabaseManager.class, DatabaseManager.DATABASE_NAME,
                    MigrationManager.MIGRATION_1_2);
    verifyUser.setSex(User.Sex.FEMALE);
    databaseManager.userDao().save(verifyUser);
    Maybe<User> stream = databaseManager.userDao().getById(verifyUser.getUid());
    TestObserver<User> observer = new TestObserver<>();
    stream.subscribe(observer);

    observer.assertValue(new Predicate<User>() {
        @Override
        public boolean test(User user) throws Exception {
            return user.getSex().equals(User.Sex.FEMALE);
        }
    });
    observer.assertComplete();
    observer.assertNoErrors();
    observer.assertTerminated();
}
```

# Contributing
See [CONTRIBUTING.md](https://github.com/jollyjoker992/android-clean-architecture/blob/master/CONTRIBUTING.md) to know exact how to contribute to this repository.

# Author
- Hieu Pham - Contact jollyjoker992@gmail.com

See list [contributors](https://github.com/jollyjoker992/android-clean-architecture/graphs/contributors) for more detail.

# License
See the [LICENSE.md](https://github.com/jollyjoker992/android-clean-architecture/blob/master/LICENSE.md) file for details


