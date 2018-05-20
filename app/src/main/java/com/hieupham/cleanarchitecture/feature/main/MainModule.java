package com.hieupham.cleanarchitecture.feature.main;

import com.hieupham.cleanarchitecture.FragmentBuilderModule;
import com.hieupham.cleanarchitecture.feature.ActivityScope;
import com.hieupham.cleanarchitecture.feature.Navigator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/20/18.
 */

@Module(includes = FragmentBuilderModule.class)
public class MainModule {

    @Provides
    @ActivityScope
    ViewModel provideViewModel() {
        return new MainViewModel();
    }

    @Provides
    @ActivityScope
    Navigator<MainActivity> provideNavigator(MainActivity activity) {
        return new Navigator<>(activity);
    }
}
