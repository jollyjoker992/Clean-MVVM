package com.hieupham.cleanarchitecture.feature.taskdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hieupham.cleanarchitecture.R;
import com.hieupham.cleanarchitecture.feature.BaseSupportFragment;
import com.hieupham.cleanarchitecture.feature.BaseViewModel;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskDetailFragment extends BaseSupportFragment {

    private static final String TASK = "TASK";

    @Inject
    ViewModel viewModel;

    @BindView(R.id.image_des)
    ImageView imageDes;

    @BindView(R.id.text_view_title)
    TextView textViewTitle;

    @BindView(R.id.text_view_status)
    TextView textStatus;

    public static TaskDetailFragment newInstance(@NonNull TaskModel task) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TASK, task);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TaskModel task = getArguments().getParcelable(TASK);
        if (task == null) return;
        bindData(task);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_taskdetail;
    }

    @Override
    protected BaseViewModel viewModel() {
        return viewModel;
    }

    private void bindData(@NonNull TaskModel task) {
        Glide.with(imageDes.getContext())
                .load(task.getUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(imageDes);
        textViewTitle.setText(task.getTitle());
        textStatus.setText(task.getStatus().toUpperCase());
    }
}
