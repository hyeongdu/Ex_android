package com.study.android.ex40_receiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //권한 체크 후 사용자에 의해 취소되었다면 다시 요청

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.READ_SMS}, 1);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
        }
    }
}