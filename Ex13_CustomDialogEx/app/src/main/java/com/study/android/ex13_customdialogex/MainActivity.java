package com.study.android.ex13_customdialogex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "111111111111");
    }

    public void onBtn1Clicked(View v){
        final Dialog loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.custom_dialog);
        loginDialog.setTitle("로그인 화면");


        final EditText username = loginDialog.findViewById(R.id.editText1);
        final EditText password = loginDialog.findViewById(R.id.editText2);

        Button login = loginDialog.findViewById(R.id.button3);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(username.getText().toString().trim().length() > 0
                && password.getText().toString().trim().length() > 0){
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                    loginDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "다시 입력하세요", Toast.LENGTH_LONG).show();
                }

            }
        });
        Button cancel = loginDialog.findViewById(R.id.button4);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loginDialog.dismiss();
            }
        });
        loginDialog.show();
    }

    public void onBtn2Clicked(View v){
        CustomCircleProgressDialog customCircleProgressDialog = new CustomCircleProgressDialog(MainActivity.this);
        customCircleProgressDialog.setCancelable(true);
        customCircleProgressDialog.show();
    }


}