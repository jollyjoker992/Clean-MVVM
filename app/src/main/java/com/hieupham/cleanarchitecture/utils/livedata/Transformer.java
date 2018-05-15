package com.hieupham.cleanarchitecture.utils.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import org.reactivestreams.Subscription;

/**
 * Created by hieupham on 5/18/18.
 */

public class Transformer {

    public static <T> LiveData<Resource<T>> maybe(Maybe<T> source) {
        final MutableLiveData<Resource<T>> result = new MutableLiveData<>();
        source.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                result.setValue(Resource.<T>loading(null));
            }
        }).subscribe(new Consumer<T>() {
            @Override
            public void accept(T value) throws Exception {
                result.setValue(Resource.success(value));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                result.setValue(Resource.<T>error(throwable, null));
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                result.setValue(Resource.<T>success(null));
            }
        });
        return result;
    }

    public static <T> LiveData<Resource<T>> single(Single<T> source) {
        final MutableLiveData<Resource<T>> result = new MutableLiveData<>();
        source.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                result.setValue(Resource.<T>loading(null));
            }
        }).subscribe(new Consumer<T>() {
            @Override
            public void accept(T value) throws Exception {
                result.setValue(Resource.success(value));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                result.setValue(Resource.<T>error(throwable, null));
            }
        });
        return result;
    }

    public static <T> LiveData<Resource<T>> observable(Observable<T> source) {
        final MutableLiveData<Resource<T>> result = new MutableLiveData<>();
        source.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                result.setValue(Resource.<T>loading(null));
            }
        }).subscribe(new Consumer<T>() {
            @Override
            public void accept(T value) throws Exception {
                result.setValue(Resource.success(value));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                result.setValue(Resource.<T>error(throwable, null));
            }
        });
        return result;
    }

    public static <T> LiveData<Resource<T>> flowable(Flowable<T> source) {
        final MutableLiveData<Resource<T>> result = new MutableLiveData<>();
        source.observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        result.setValue(Resource.<T>loading(null));
                    }
                })
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T value) throws Exception {
                        result.setValue(Resource.success(value));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        result.setValue(Resource.<T>error(throwable, null));
                    }
                });
        return result;
    }
}
