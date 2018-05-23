package com.hieupham.cleanarchitecture.feature.tasklist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hieupham.cleanarchitecture.R;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import java.util.List;

/**
 * Created by hieupham on 5/17/18.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private List<TaskModel> tasks;
    private OnItemClickListener listener;

    void setTasks(@NonNull List<TaskModel> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    void addTasks(@NonNull List<TaskModel> tasks) {
        this.tasks.addAll(tasks);
        notifyItemInserted(this.tasks.size());
    }

    void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tasklist, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_des)
        ImageView imageDes;

        @BindView(R.id.text_view_title)
        TextView textViewTitle;

        @BindView(R.id.text_view_status)
        TextView textViewStatus;

        @BindView(R.id.text_view_owner)
        TextView textViewOwner;

        @Nullable
        OnItemClickListener listener;

        TaskModel task;

        ViewHolder(View view, @Nullable OnItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
        }

        void bind(@NonNull TaskModel task) {
            this.task = task;
            Glide.with(imageDes.getContext())
                    .load(this.task.getUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageDes);
            textViewTitle.setText(this.task.getTitle());
            textViewStatus.setText(this.task.getStatus().toUpperCase());
            textViewOwner.setText(this.task.getUser().getName());
        }

        @OnClick(R.id.layout_root)
        @Optional
        void onItemClicked() {
            if (listener == null) return;
            listener.onItemClicked(task);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(TaskModel task);
    }
}
