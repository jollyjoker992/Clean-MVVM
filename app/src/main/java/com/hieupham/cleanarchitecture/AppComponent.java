package com.hieupham.cleanarchitecture;

import android.app.Application;
import com.hieupham.cleanarchitecture.data.source.RepositoryModule;
import com.hieupham.cleanarchitecture.data.source.remote.api.NetworkModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by hieupham on 5/14/18.
 */

@Component(modules = {
        AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilderModule.class,
        RepositoryModule.class, NetworkModule.class
})
@AppScope
public interface AppComponent extends AndroidInjector<MvvmApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
