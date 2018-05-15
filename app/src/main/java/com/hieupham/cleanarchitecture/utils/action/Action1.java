package com.hieupham.cleanarchitecture.utils.action;

/**
 * Created by hieupham on 5/16/18.
 */

public interface Action1<T> extends Action {

    void call(T t);
}
