# Android Clean Architecture

This repository is built for contributing some clean architectures that make your project work perfectly, maintable, testable and extendable.

# Getting started
This repository contains some common architectures that build in different branches. You can see all available branches [here](https://github.com/jollyjoker992/android-clean-architecture/branches).

# Structure
This branch builds a MVVM structure pattern and absolutely follow its concept.

![](https://davidguerrerodiaz.files.wordpress.com/2015/10/screen-shot-2015-12-05-at-10-59-55.png?w=380&h=266)
The main idea for this structure is
- **View** - in Android is known as ViewController such as `Activity` or `Fragment` - knows about the ViewModel. It means ViewController has a ViewModel instance and contains logic to handle view interaction. View will send command to ViewModel but only observe data change to receive notification from ViewModel to update UI.
- **ViewModel** contains view business logic such as validation. ViewModel has no any package `android.*` unless support lifecycle package. It helps ViewModel is testable via Mockito when you wanna mock object without any Android components. Logic processor or Data processor such as **Repository** is injected to ViewModel. 
- **Model** is layer represent data or logic processor. It contains some modules, classes or units to process data in our app such as calling web service or retrieving data from local database. 


Now I will explain some concepts in this structure.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

- **ViewController** represents `Activity` or `Fragment`. It's **View** layer in MVVM model. Follow above principle, we are only allowed to inject ViewModel instance or anything else that handle View logic such as permission handler, navigator, dialog manager, `RecyclerAdapter`, etc... into the `Activity` or `Fragment`
- **ViewModel**'s dependencies is Data processor or anything else that handle logic such as location handler. It follows the `Activity` or `Fragment` lifecycle and contain some callback to handle it. You can find it in `BaseViewModel`
- **Repository**'s dependencies is `LocalDataSource` and `RemoteDataSource` that handle logic to retrieve data from local database and remote server. We mix the data from local and remote as *Reactive Flow* (`Flowable`, `Observable`, `Maybe` or etc...) and transform it into `LiveData` before exposing to `ViewModel`
- `LocalDataSource` and `RemoteDataSource` exposes a Reactive Flow, **NOT** `LiveData`, they uses RxJava to handle raw logic.


### Dependency Injection
We use [Dagger 2](https://google.github.io/dagger/) for dependency injection implementation in this project.

![](https://image.ibb.co/dgttT8/DI.png)

1. We implement DI object graphes follow the Android component. So, there are 3 layers of Oject Graph corresponding with the Android components. 
    - `AppComponent` contains modules that has *Application Scope* and provides all objects at the Application scope. All **Subcomponents** can use everything `AppComponent` provides. `AppComponent` has many subcomponents represent by `ActivityComponent`
    - `ActivityComponent` is implemented in Activity scope that provide everything in an `Activity`. `ActivityComponent` can use everything that are provided in Application Scope via `AppComponent`.
    - `FragmentComponent` is implemented in Fragment scope that provide everything in a `Fragment`. `FragmentComponent` can use everything that are provided in Activity scope via `ActivityComponent`



# Contributing
See [CONTRIBUTING.md](https://github.com/jollyjoker992/android-clean-architecture/blob/master/CONTRIBUTING.md) to know exact how to contribute to this repository.

# Author
- Hieu Pham - Framgia Inc - Contact jollyjoker992@gmail.com

See list [contributors](https://github.com/jollyjoker992/android-clean-architecture/graphs/contributors) for more detail.

# License
See the [LICENSE.md](https://github.com/jollyjoker992/android-clean-architecture/blob/master/LICENSE.md) file for details


