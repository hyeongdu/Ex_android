package com.study.android.ex30_webview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

public class CustomProgressDialog extends Dialog {

    public CustomProgressDialog(@NonNull Context context)
    {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_progress_dialog);
    }
}