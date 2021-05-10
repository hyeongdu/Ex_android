package com.study.android.ex09_alert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtn1Clicked(View v){
        //버튼 1 : 기본형
        com.study.android.alert.MyUtil.AlertShow(MainActivity.this, "아이디를 입력해 주세요", "알림");

    }

    public void onBtn2Clicked(View v){
        //버튼 2 : 기본형
       com.study.android.alert.MyUtil.AlertShow(MainActivity.this, "아이디를 입력해 주세요");

    }

    public void onBtn3Clicked(View v){
        //버튼 3 : 기본형
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("앱을 종료하시겠습니까")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("알림")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "ID value is "+ Integer.toString(id), Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getApplicationContext(), "ID value is "+ Integer.toString(id), Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }
                   


        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}