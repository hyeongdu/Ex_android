package com.study.android.ex03_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다음과 같이 원하는 xml을 선택적으로 사용할 수 있습니다.
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main2);
    }
}