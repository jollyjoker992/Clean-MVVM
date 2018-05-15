package com.hieupham.cleanarchitecture.data.source.local;

import com.hieupham.cleanarchitecture.data.source.local.api.RoomApi;
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

public abstract class LocalDataSource {

    private RoomApi roomApi;

    LocalDataSource(RoomApi roomApi) {
        this.roomApi = roomApi;
    }

    protected <T> Maybe<T> maybe(Function<RoomApi, Maybe<T>> function) {
        return roomApi.maybe(function);
    }

    protected <T> Flowable<T> flowable(Function<RoomApi, Flowable<T>> function) {
        return roomApi.flowable(function);
    }

    protected <T> Single<T> single(Function<RoomApi, Single<T>> function) {
        return roomApi.single(function);
    }

    protected <T> Observable<T> observable(Function<RoomApi, Observable<T>> function) {
        return roomApi.observable(function);
    }

    protected <T> Maybe<T> maybe(Action2<MaybeEmitter<? super T>, RoomApi> action) {
        return roomApi.maybe(action);
    }

    protected <T> Flowable<T> flowable(Action2<FlowableEmitter<? super T>, RoomApi> action) {
        return roomApi.flowable(action);
    }

    protected <T> Single<T> single(Action2<SingleEmitter<? super T>, RoomApi> action) {
        return roomApi.single(action);
    }

    protected <T> Observable<T> observable(Action2<ObservableEmitter<? super T>, RoomApi> action) {
        return roomApi.observable(action);
    }
}
