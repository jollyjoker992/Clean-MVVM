package com.hieupham.cleanarchitecture.feature;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class BaseAppCompatActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
    }

    @LayoutRes
    public abstract int layoutRes();
}
