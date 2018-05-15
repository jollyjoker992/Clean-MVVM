package com.hieupham.cleanarchitecture.data.source.local.api;

import com.hieupham.cleanarchitecture.data.source.local.api.dao.TaskDao;
import com.hieupham.cleanarchitecture.data.source.local.api.dao.UserDao;
import com.hieupham.cleanarchitecture.utils.action.Action2;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.functions.Function;

/**
 * Created by hieupham on 5/14/18.
 */

public interface RoomApi {

    UserDao userDao();

    TaskDao taskDao();

    <T> Maybe<T> maybe(Function<RoomApi, Maybe<T>> function);

    <T> Flowable<T> flowable(Function<RoomApi, Flowable<T>> function);

    <T> Single<T> single(Function<RoomApi, Single<T>> function);

    <T> Observable<T> observable(Function<RoomApi, Observable<T>> function);

    <T> Maybe<T> maybe(Action2<MaybeEmitter<? super T>, RoomApi> action);

    <T> Flowable<T> flowable(Action2<FlowableEmitter<? super T>, RoomApi> action);

    <T> Single<T> single(Action2<SingleEmitter<? super T>, RoomApi> action);

    <T> Observable<T> observable(Action2<ObservableEmitter<? super T>, RoomApi> action);
}
