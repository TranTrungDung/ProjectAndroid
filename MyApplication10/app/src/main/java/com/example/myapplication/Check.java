package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Check {
    private Activity activity;
    private AlertDialog dialog;
    Check(Activity myActivity)
    {
    activity= myActivity;
    }
    void checkDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.tick_product,null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    void  dismissDialog(){
        dialog.dismiss();
    }
}
