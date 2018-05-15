package com.hieupham.cleanarchitecture.feature;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

/**
 * Created by hieupham on 5/17/18.
 */

public class DialogManager {

    private FragmentActivity activity;

    public DialogManager(@NonNull FragmentActivity activity) {
        this.activity = activity;
    }

    public void showError(@NonNull Throwable throwable) {
        new AlertDialog.Builder(activity).setTitle("Error")
                .setMessage(throwable.getMessage())
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
