package com.study.android.ex01_hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    String sName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        //인텐트에 전달된 데이터 구하기
        Intent intent = getIntent();
        sName = intent.getStringExtra("CustomerName");
    }

    public void onBtn1Clikcked(View v)
    {
      //전달된 데이터 보여주기
        Toast.makeText(getApplicationContext(), "CustomerName : " + sName, Toast.LENGTH_SHORT).show();
    }

    public void onBtn2Clikcked(View v)
    {
     Intent intent = new Intent();
     intent.putExtra("BackData", "강감찬");
     setResult(10, intent);

     finish();

    }



}