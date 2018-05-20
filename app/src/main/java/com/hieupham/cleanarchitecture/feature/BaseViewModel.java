package com.hieupham.cleanarchitecture.feature;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;

/**
 * Created by hieupham on 5/14/18.
 */

public abstract class BaseViewModel<T extends BaseUseCase> implements LifecycleObserver {

    protected T useCase;

    public BaseViewModel(@Nullable T useCase) {
        this.useCase = useCase;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        dispose();
    }

    private void dispose() {
        if (useCase == null) return;
        useCase.dispose();
    }
}
