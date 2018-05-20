package com.hieupham.cleanarchitecture.feature;

import android.content.Context;
import android.support.v4.app.Fragment;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/15/18.
 */

/**
 * The {@link Fragment} base class for implementing dependency injection using Dagger 2 <br>
 * You only need to extends this class from another support fragments, all member variables that are
 * marked with @{@link Inject} annotation will be injected automatically.
 */
public abstract class DaggerSupportFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
