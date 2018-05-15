package com.hieupham.cleanarchitecture.utils.action;

/**
 * Created by hieupham on 5/16/18.
 */

public interface Action2<T, R> extends Action {

    void call(T t, R r);
}
