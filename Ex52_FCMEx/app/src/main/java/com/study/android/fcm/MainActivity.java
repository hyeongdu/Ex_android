package com.study.android.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.log);

        // 앱이 실행할 때 알림 메시지가 있었으면 데이터를 표시하도록 함.
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {

            for (String key : intent.getExtras().keySet()) {
                String value = intent.getExtras().getString(key);
                Log.d(TAG, "Noti - " + key + ":" + value );
            }
            /*
            Noti - google.delivered_priority:high
            Noti - google.sent_time:null
            Noti - google.ttl:null
            Noti - google.original_priority:high
            Noti - from:296042452497
            Noti - google.message_id:0:1537056511730816%46105f7f46105f7f
            Noti - message:여기는 처리하고자 하는 내용입니다.
            Noti - collapse_key:com.study.android.fcm
            */
            Log.d(TAG, "알림 메시지가 있어요");
            // 알림을 클릭해서 앱이 실행되었을 때 메시지를 표시한다.
            // 알림 클릭 없이 앱을 바로 실행
            // -- 데이터가 표시되지 않는다.
            // -- 시스템 알림에 알림은 그대로 남아 있다.
            //String contents = intent.getExtras().getString("message");
            String contents = intent.getStringExtra("message");
            if ( contents != null ) {
                processIntent(contents);
            }
        }

        getRegistrationId();
    }

    public void getRegistrationId() {
        println("getRegistrationId() 호출됨.");

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        Log.d(TAG, "RegID:"+token);
                        println("regId : " + token);
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        println("onNewIntent() called.");

        if (intent != null) {
            String contents = intent.getStringExtra("message");
            processIntent(contents);
        }
    }

    private void processIntent(String contents) {
        Log.d(TAG, "["+contents+"]");
        println("DATA : " + contents);
    }

    public void println(String data) {
        log.append(data + "\n");
    }

}
