package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import com.hieupham.cleanarchitecture.R;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.feature.BaseAppCompatActivity;
import com.hieupham.cleanarchitecture.feature.DialogManager;
import com.hieupham.cleanarchitecture.feature.Navigator;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskListActivity extends BaseAppCompatActivity
        implements TaskListAdapter.OnItemClickListener {

    @Inject
    ViewModel viewModel;

    @Inject
    Navigator<TaskListActivity> navigator;

    @Inject
    DialogManager dialogManager;

    @Inject
    TaskListAdapter adapter;

    @BindView(R.id.recycler_tasks)
    RecyclerView recyclerTasks;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.init("uid");
        getLifecycle().addObserver(viewModel);
        recyclerTasks.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        viewModel.tasks().observe(this, observerTasks());
    }

    @Override
    public int layoutRes() {
        return R.layout.activity_tasklist;
    }

    @Override
    public void onItemClicked(Task task) {
        Toast.makeText(this, task.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private Observer<Resource<List<Task>>> observerTasks() {
        return new Observer<Resource<List<Task>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Task>> resource) {
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
