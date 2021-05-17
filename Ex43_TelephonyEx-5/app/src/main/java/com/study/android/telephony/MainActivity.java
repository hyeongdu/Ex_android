package com.study.android.telephony;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    // 권한 체크 후 사용자에 의해 취소되었다면 다시 요청
    String[] permissions = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG       // 들어오는 전화 번호 확인
    };

    TextView txtTelStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkPermissions()) {
            Toast.makeText(getApplicationContext(),
                    "권한 설정을 해주셔 앱이 정상 동작합니다.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //manager.getCallState()    // 음성통화 상태
        //manager.getDataState()    // 데이터통신 상태 조회

        txtTelStatus = findViewById(R.id.textView);
        manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE
                | PhoneStateListener.LISTEN_DATA_ACTIVITY);
    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    1 );
            return false;
        }
        return true;
    }

    public void onBtn1Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01074693800"));
        startActivity(intent);
    }

    public void onBtn2Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01074693800"));
        startActivity(intent);
    }

    public void onBtn3Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01074693800"));
        startActivity(intent);
    }

    public void onBtn4Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), ContactsListActivity.class);
        startActivity(intent);
    }

    PhoneStateListener listener = new PhoneStateListener() {
        public void onCallStateChanged(int state, String incomingNumber) {
            String s1 = "";
            String s2 = "";
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE :
                    // 통화중이 아닐 때
                    s1 = "통화 대기중";
                    s2 = "";
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // 통화중
                    s1 = "통화중";
                    s2 = incomingNumber;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // 전화 벨이 울릴 때
                    s1 = "따르릉";
                    s2 = incomingNumber;
                    break;
            }

            String s3 = "상태:  " + s1 + " / 전화번호: " + s2;
            txtTelStatus.setText(s3);
            Log.d(TAG, s3);
        }

        public void onDataActivity(int direction) {
            //Log.d(TAG, "방향 : " + direction);
        }
    };
}
