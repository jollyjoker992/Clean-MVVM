package com.hieupham.cleanarchitecture.util;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by hieupham on 5/22/18.
 */

public class RxImmediateSchedulerRule implements TestRule {

    private Scheduler immediate = new Scheduler() {
        @Override
        public Disposable scheduleDirect(@NonNull Runnable run, long delay,
                @NonNull TimeUnit unit) {
            return super.scheduleDirect(run, 0, unit);
        }

        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(new ScheduledThreadPoolExecutor(1) {
                @Override
                public void execute(Runnable command) {
                    command.run();
                }
            });
        }
    };

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setInitIoSchedulerHandler(schedulerFunction(immediate));
                RxJavaPlugins.setInitComputationSchedulerHandler(schedulerFunction(immediate));
                RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerFunction(immediate));
                RxJavaPlugins.setInitSingleSchedulerHandler(schedulerFunction(immediate));
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunction(immediate));

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }

    private Function<Callable<Scheduler>, Scheduler> schedulerFunction(final Scheduler scheduler) {
        return new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        };
    }
}
