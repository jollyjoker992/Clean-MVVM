package com.hieupham.cleanarchitecture.data.source.local.api;

import com.hieupham.cleanarchitecture.data.source.local.api.dao.TaskDao;
import com.hieupham.cleanarchitecture.data.source.local.api.dao.UserDao;
import com.hieupham.cleanarchitecture.utils.action.Action2;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hieupham on 5/14/18.
 */

public class RoomApiImpl implements RoomApi {

    private DatabaseManager databaseManager;

    public RoomApiImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public UserDao userDao() {
        return databaseManager.userDao();
    }

    @Override
    public TaskDao taskDao() {
        return databaseManager.taskDao();
    }

    @Override
    public <T> Maybe<T> maybe(Function<RoomApi, Maybe<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Maybe.error(e);
        }
    }

    @Override
    public <T> Flowable<T> flowable(Function<RoomApi, Flowable<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Flowable.error(e);
        }
    }

    @Override
    public <T> Single<T> single(Function<RoomApi, Single<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Single.error(e);
        }
    }

    @Override
    public <T> Observable<T> observable(Function<RoomApi, Observable<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Observable.error(e);
        }
    }

    @Override
    public <T> Maybe<T> maybe(final Action2<MaybeEmitter<? super T>, RoomApi> action) {
        return Maybe.create(new MaybeOnSubscribe<T>() {
            @Override
            public void subscribe(MaybeEmitter<T> e) throws Exception {
                action.call(e, RoomApiImpl.this);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Flowable<T> flowable(final Action2<FlowableEmitter<? super T>, RoomApi> action) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> e) throws Exception {
                action.call(e, RoomApiImpl.this);
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Single<T> single(final Action2<SingleEmitter<? super T>, RoomApi> action) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> e) throws Exception {
                action.call(e, RoomApiImpl.this);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Observable<T> observable(
            final Action2<ObservableEmitter<? super T>, RoomApi> action) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                action.call(e, RoomApiImpl.this);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }
}
