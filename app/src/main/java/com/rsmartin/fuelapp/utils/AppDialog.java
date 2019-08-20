package com.rsmartin.fuelapp.utils;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import javax.inject.Singleton;

@Singleton
public class AppDialog {

    private static AlertDialog.Builder dialogBuilder = null;

    public static void showDialog(final Activity activity, final int title, final int message,
                                  final boolean finalizeActivity) {
        if (activity == null) {
            return;
        }
        if (dialogBuilder == null) {
            dialogBuilder = new AlertDialog.Builder(activity)
                    .setCancelable(false)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            dialogBuilder = null;
                            if (finalizeActivity) {
                                activity.finish();
                            }
                        }
                    });
            dialogBuilder.create().show();
        }
    }

    public static void showDialog(final Activity activity, final String title, final String message,
                                  final boolean finalizeActivity) {
        if (activity == null) {
            return;
        }

        if (dialogBuilder == null) {
            dialogBuilder = new AlertDialog.Builder(activity)
                    .setCancelable(false)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            dialogBuilder = null;
                            if (finalizeActivity) {
                                activity.finish();
                            }
                        }
                    });
            dialogBuilder.create().show();
        }
    }

}
