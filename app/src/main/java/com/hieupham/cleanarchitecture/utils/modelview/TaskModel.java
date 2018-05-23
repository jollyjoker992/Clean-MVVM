package com.hieupham.cleanarchitecture.utils.modelview;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.model.User;

/**
 * Created by hieupham on 5/22/18.
 */

public class TaskModel implements Parcelable {

    @Expose
    @NonNull
    private String uid;

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private String ownerUid;

    @Expose
    private String reviewerUid;

    @Expose
    private String status;

    @Expose
    private String url;

    @Expose
    private User user;

    public static TaskModel from(@NonNull Task task, @Nullable User user) {
        TaskModel taskModel = new TaskModel();
        taskModel.uid = task.getUid();
        taskModel.title = task.getTitle();
        taskModel.description = task.getDescription();
        taskModel.ownerUid = task.getOwnerUid();
        taskModel.reviewerUid = task.getReviewerUid();
        taskModel.status = task.getStatus();
        taskModel.url = task.getUrl();
        taskModel.user = user;
        return taskModel;
    }

    public TaskModel() {
    }

    protected TaskModel(Parcel in) {
        uid = in.readString();
        title = in.readString();
        description = in.readString();
        ownerUid = in.readString();
        reviewerUid = in.readString();
        status = in.readString();
        url = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<TaskModel> CREATOR = new Creator<TaskModel>() {
        @Override
        public TaskModel createFromParcel(Parcel in) {
            return new TaskModel(in);
        }

        @Override
        public TaskModel[] newArray(int size) {
            return new TaskModel[size];
        }
    };

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getReviewerUid() {
        return reviewerUid;
    }

    public void setReviewerUid(String reviewerUid) {
        this.reviewerUid = reviewerUid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(@Task.Status String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(ownerUid);
        dest.writeString(reviewerUid);
        dest.writeString(status);
        dest.writeString(url);
        dest.writeParcelable(user, flags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskModel)) return false;

        TaskModel taskModel = (TaskModel) o;

        if (!uid.equals(taskModel.uid)) return false;
        if (title != null ? !title.equals(taskModel.title) : taskModel.title != null) return false;
        if (description != null ? !description.equals(taskModel.description)
                : taskModel.description != null) {
            return false;
        }
        if (ownerUid != null ? !ownerUid.equals(taskModel.ownerUid) : taskModel.ownerUid != null) {
            return false;
        }
        if (reviewerUid != null ? !reviewerUid.equals(taskModel.reviewerUid)
                : taskModel.reviewerUid != null) {
            return false;
        }
        if (status != null ? !status.equals(taskModel.status) : taskModel.status != null) {
            return false;
        }
        if (url != null ? !url.equals(taskModel.url) : taskModel.url != null) return false;
        return user != null ? user.equals(taskModel.user) : taskModel.user == null;
    }

    @Override
    public int hashCode() {
        int result = uid.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ownerUid != null ? ownerUid.hashCode() : 0);
        result = 31 * result + (reviewerUid != null ? reviewerUid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
