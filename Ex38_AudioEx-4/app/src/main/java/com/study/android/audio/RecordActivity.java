package com.study.android.audio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecordActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    private static String RECORDED_FILE;

    MediaPlayer player;
    MediaRecorder recorder;

    // 권한 체크 후 사용자에 의해 취소되었다면 다시 요청
    String[] permissions= new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(new Date());
//        File sdcard = Environment.getExternalStorageDirectory();
        File sdcard = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(sdcard, "recorded_" + timeStamp + ".mp3");
        RECORDED_FILE = file.getAbsolutePath();
        Log.d(TAG, RECORDED_FILE);

        if (!checkPermissions()) {
            Toast.makeText(getApplicationContext(),
                    "권한 설정을 해주셔 앱이 정상 동작합니다.",
                    Toast.LENGTH_LONG).show();
        }
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
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }

        recorder = new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        recorder.setOutputFile(RECORDED_FILE);

        try {
            Toast.makeText(getApplicationContext(), "녹음을 시작합니다.",
                           Toast.LENGTH_LONG).show();

            recorder.prepare();
            recorder.start();
        } catch (Exception ex) {
            Log.d(TAG, "Exception : ", ex);
        }
    }

    public void onBtn2Clicked(View v) {
        if (recorder == null)
            return;

        recorder.stop();
        recorder.release();
        recorder = null;

        Toast.makeText(getApplicationContext(), "녹음이 중지되었습니다.",
                       Toast.LENGTH_LONG).show();
    }

    public void onBtn3Clicked(View v) {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }

        Toast.makeText(getApplicationContext(), "녹음된 파일을 재생합니다.",
                       Toast.LENGTH_LONG).show();
        try {
            player = new MediaPlayer();

            player.setDataSource(RECORDED_FILE);
            player.prepare();
            player.start();
        } catch (Exception e) {
            Log.d(TAG, "Audio play failed.", e);
        }
    }

    public void onBtn4Clicked(View v) {
        if (player == null)
            return;

        Toast.makeText(getApplicationContext(), "재생이 중지되었습니다.",
                       Toast.LENGTH_LONG).show();

        player.stop();
        player.release();
        player = null;
    }

}
