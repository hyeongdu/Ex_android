package com.study.android.service2;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    AlarmManager am;
    Intent intent;
    PendingIntent receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        // 예약에 의해 호출될 리시버 지정
        intent = new Intent(this, AlarmReceiver.class);
        receiver = PendingIntent.getBroadcast(this,
                             0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void onBtn1Clicked(View v) {
        // 알람 시간. 10초후
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);

        // 알람 등록
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), receiver);
    }

    public void onBtn2Clicked(View v) {
        // 60초당 한번 알람 등록 : 24 * 60 * 60 * 1000
        // kitkat 부터는 최소 시간이 1분이다.
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                60 * 1000, receiver);
    }

    public void onBtn3Clicked(View v) {
        am.cancel(receiver);
    }
}
