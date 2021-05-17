package com.study.android.fcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView log;
    String regId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = (TextView)findViewById(R.id.log);

        //앱이 실행할 때 알림 메시지가 있으면 데이터를 표시하도록 함
        Intent intent = getIntent();
        if(intent!=null && intent.getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG,"NOTI - " + key + ":" + value);
            }

            Log.d(TAG, "알림 메시지가 있어요");
            String contents = intent.getStringExtra("message");
            if(contents != null){
                processIntent(contents);
            }
        }

        getRegistrationId();
    }

    public void getRegistrationId(){
        println("getRegistrationId() 호출됨");

        regId = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "RegID : " + regId);
        println("regId : " + regId);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        println("onNewIntent() called");
        if(intent != null){
            String contents = intent.getStringExtra("message");
            processIntent(contents);
        }
    }

    private void processIntent(String contents){
        println("DATA : " + contents);
    }

    public void println(String data) {

        log.append(data + "\n");
    }


}