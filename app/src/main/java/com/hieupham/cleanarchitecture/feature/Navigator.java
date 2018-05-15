package com.hieupham.cleanarchitecture.feature;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by hieupham on 5/14/18.
 */

public class Navigator<T> {

    @Nullable
    private Fragment fragment;
    private FragmentActivity activity;

    public Navigator(@NonNull T host) {
        if (host instanceof FragmentActivity) {
            this.activity = (FragmentActivity) host;
        } else if (host instanceof Fragment) {
            this.fragment = (Fragment) host;
            this.activity = ((Fragment) host).getActivity();
        }
    }

    public void addFragment(@IdRes int container, Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment)
                .commitAllowingStateLoss();
    }

    public void startActivity(Class clazz) {
        activity.startActivity(new Intent(activity, clazz));
    }
}
