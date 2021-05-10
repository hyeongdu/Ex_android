package com.study.android.ex01_hello;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View v)
            {
                Log.d(TAG,"로그 출력");
                Toast.makeText(getApplicationContext(), "긴 토스트", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onButton2Clicked(View v)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));  //암시적
        startActivity(intent);
    }

    public void onButton3Clicked(View v)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01059183963"));
        startActivity(intent);
    }

    public void onButton4Clicked(View v)
    {
       EditText editText = findViewById(R.id.editText);

       String str = editText.getText().toString();

        TextView textView = findViewById(R.id.textView);
        textView.setText(str);

        Toast.makeText(getApplicationContext(), "EditText : " + str, Toast.LENGTH_SHORT).show();
    }
        //새로운 액티비티 만들기
    public void onButton5Clicked(View v)
    {
        Intent intent = new Intent(getApplicationContext(), NewActivity.class);   //명시적
        intent.putExtra("CustomerName", "홍길동");
       // startActivity(intent);
        startActivityForResult(intent, 1);
    }
    //두번째 액티비티
    public void onButton6Clicked(View v)
    {
        Intent intent = new Intent(getApplicationContext(), HelloActivity.class);   //명시적
        intent.putExtra("CustomerName", "홍길동2");
        // startActivity(intent);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "콜백 함수 호출됨");
        if(requestCode == 1 && resultCode == 10)
        {
            String sData ="";
            String str = "OnActivityResult() called : "+ requestCode + " : " + resultCode;
            sData = data.getStringExtra("BackData");
            str = str + " : " + sData;
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

        }

        if(requestCode == 2)
        {
            if(resultCode == 10)
            {
                Log.d(TAG, "수정하셨습니다");
                Toast.makeText(getApplicationContext(), "수정하셨습니다", Toast.LENGTH_SHORT).show();
            }
           else if(resultCode == 11)
            {
                Log.d(TAG, "회원가입 완료");
                Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
            }
        }
    }


}