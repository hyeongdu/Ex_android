package com.study.android.ex28_thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    TextView textView1;
    Handler handler;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        textView1 = findViewById(R.id.textView1);
        progressBar1 = findViewById(R.id.progressBar1);
    }

    public void onBtn1Clicked(View v){

        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread{
        public  void run(){
            for(int i=0; i<100; i++){
                Log.d(TAG, "Request Thread .." + i);
                //별도의 핸들러 클래스를 만들지 않고 바로 처리한다
                //앞의 예제처럼 핸들러 바깥의 변수와 안쪽의 변수는 사용 할 수 없다
                final int index = i;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setText("Request Thread .. " + index);
                        progressBar1.incrementProgressBy(1);
                    }
                });

                if(i==99)
                    textView1.setText("쓰레드 시험 종료");


                try{
                    Thread.sleep(200);

                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}