package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.hieupham.cleanarchitecture.R;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.feature.BaseSupportFragment;
import com.hieupham.cleanarchitecture.feature.BaseViewModel;
import com.hieupham.cleanarchitecture.feature.DialogManager;
import com.hieupham.cleanarchitecture.feature.Navigator;
import com.hieupham.cleanarchitecture.feature.taskdetail.TaskDetailFragment;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskListFragment extends BaseSupportFragment
        implements TaskListAdapter.OnItemClickListener {

    @Inject
    ViewModel viewModel;

    @Inject
    Navigator<TaskListFragment> navigator;

    @Inject
    DialogManager dialogManager;

    @Inject
    TaskListAdapter adapter;

    @BindView(R.id.recycler_tasks)
    RecyclerView recyclerTasks;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.init("uid");
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_tasklist;
    }

    @Override
    protected void initComponents() {
        recyclerTasks.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected BaseViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected void observe() {
        viewModel.tasks().observe(this, observerTasks());
    }

    @Override
    public void onItemClicked(TaskModel task) {
        navigator.replaceFragment(R.id.layout_root, TaskDetailFragment.newInstance(task), true);
    }

    private Observer<Resource<List<TaskModel>>> observerTasks() {
        return new Observer<Resource<List<TaskModel>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<TaskModel>> resource) {
                if (resource == null) return;
                progressBar.setVisibility(View.GONE);
                if (resource.isSuccessful()) {
                    adapter.setTasks(resource.data());
                } else if (resource.isError()) {
                    dialogManager.showError(resource.throwable());
                } else if (resource.isLoading()) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        };
    }
}
