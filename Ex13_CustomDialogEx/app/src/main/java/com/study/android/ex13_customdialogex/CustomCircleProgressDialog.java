package com.study.android.ex13_customdialogex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

public class CustomCircleProgressDialog extends Dialog {

    private TextView progressCntTv;
    private TextView progressTextTv;

          public CustomCircleProgressDialog(@NonNull Context context)
        {
            super(context);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_customcircleprogressdialog);
        }

}