package com.gitwatch.gitwatch.infrastructure.github.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

public class AlertHelper {
    public static void showInfoAlert(final Context context, String title, String message, String buttonText, @Nullable final Class targetActivity){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);

        dialog.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (targetActivity == null) {
                    return;
                }
                final Class activity = targetActivity;
                context.startActivity(new Intent(context, activity));
            }
        });

        dialog.show();
    }
}
