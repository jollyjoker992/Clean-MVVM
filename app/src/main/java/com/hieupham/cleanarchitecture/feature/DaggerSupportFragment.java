package com.hieupham.cleanarchitecture.feature;

import android.content.Context;
import android.support.v4.app.Fragment;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class DaggerSupportFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
