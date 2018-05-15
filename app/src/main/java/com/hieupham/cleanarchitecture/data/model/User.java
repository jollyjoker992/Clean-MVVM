package com.hieupham.cleanarchitecture.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;

/**
 * Created by hieupham on 5/14/18.
 */

@Entity(tableName = "User")
public class User {

    @PrimaryKey
    @Expose
    @NonNull
    private String uid;

    @Expose
    private String name;

    @Expose
    private String email;

    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
