package com.hieupham.cleanarchitecture.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import com.google.gson.annotations.Expose;
import java.util.UUID;

/**
 * Created by hieupham on 5/14/18.
 */

@Entity(tableName = "Task", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "owner_uid", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "reviewer_uid", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)
})
public class Task implements Parcelable {

    @StringDef({
            Status.TODO, Status.IN_PROGRESS, Status.RESOLVED, Status.DONE, Status.REVIEW,
            Status.TESTING, Status.REOPEN
    })
    public @interface Status {
        String TODO = "todo";
        String IN_PROGRESS = "inprogress";
        String RESOLVED = "resolved";
        String DONE = "done";
        String REVIEW = " review";
        String TESTING = "testing";
        String REOPEN = "reopen";
    }

    @PrimaryKey
    @Expose
    @NonNull
    private String uid;

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    @ColumnInfo(name = "owner_uid")
    private String ownerUid;

    @Expose
    @ColumnInfo(name = "reviewer_uid")
    private String reviewerUid;

    @Expose
    private String status;

    @Expose
    private String url;

    private Task() {
        uid = UUID.randomUUID().toString();
    }

    public Task(String title, String ownerUid, String status, String url) {
        this();
        this.title = title;
        this.ownerUid = ownerUid;
        this.status = status;
        this.url = url;
    }

    protected Task(Parcel in) {
        uid = in.readString();
        title = in.readString();
        description = in.readString();
        ownerUid = in.readString();
        reviewerUid = in.readString();
        status = in.readString();
        url = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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

    public void setStatus(@Status String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
