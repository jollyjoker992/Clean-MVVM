package com.hieupham.cleanarchitecture.feature.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hieupham.cleanarchitecture.R;
import com.hieupham.cleanarchitecture.feature.BaseAppCompatActivity;
import com.hieupham.cleanarchitecture.feature.BaseViewModel;
import com.hieupham.cleanarchitecture.feature.Navigator;
import com.hieupham.cleanarchitecture.feature.tasklist.TaskListFragment;
import javax.inject.Inject;

public class MainActivity extends BaseAppCompatActivity {

    @Inject
    ViewModel viewModel;

    @Inject
    Navigator<MainActivity> navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.replaceFragment(R.id.layout_root, TaskListFragment.newInstance(), false);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseViewModel viewModel() {
        return viewModel;
    }
}
