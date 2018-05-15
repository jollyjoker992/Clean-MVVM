package com.hieupham.cleanarchitecture.feature.taskdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hieupham.cleanarchitecture.R;
import com.hieupham.cleanarchitecture.feature.BaseSupportFragment;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskDetailFragment extends BaseSupportFragment {

    @Inject
    ViewModel viewModel;

    public static TaskDetailFragment newInstance() {
        return new TaskDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        getLifecycle().addObserver(viewModel);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int layoutRes() {
        return R.layout.fragment_taskdetail;
    }
}
