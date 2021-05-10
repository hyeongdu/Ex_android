package com.study.android.ex01_hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    public void onBtn2Clikcked(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("BackData", "회원 정보 수정 :강감찬2-1");
        setResult(10, intent);

        finish();

    }

    public void onBtn3Clikcked(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("BackData", " 회원 정보 추가 : 강감찬2-2");
        setResult(11, intent);

        finish();

    }


}