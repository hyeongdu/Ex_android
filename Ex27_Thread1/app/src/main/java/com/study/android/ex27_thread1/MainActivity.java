package com.study.android.ex27_thread1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    TextView textView1;
    Button button1;
    ProgressHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new ProgressHandler();
        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

    }
    public void onBtn1Clicked(View v){
        button1.setEnabled(false);
        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread{
        public  void run(){
            for(int i=0; i<20; i++){
                Log.d(TAG, "Request Thread .." + i);

                //쓰레드에서 메인 쓰레드의 객체로의 접근은 불가능
             // textView1.setText("Request Thread .." + i);
                Message msg = handler.obtainMessage()  ;

                Bundle bundle = new Bundle();
                bundle.putString("data1", "Request Thread .. : " + i);
                bundle.putString("data2", String.valueOf(i));
                msg.setData(bundle);

                handler.sendMessage(msg);

                try{
                    Thread.sleep(1000);

                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            //핸드러에 메세지가 전달되면 원하는 동작 처리

            Bundle bundle = msg.getData();
            String data1 = bundle.getString("data1");
            String data2 = bundle.getString("data2");

            textView1.setText(data1);

            if(data2.equals("19")){
                textView1.setText("쓰레드 테스트");
                button1.setEnabled(true);

            }else {
                button1.setEnabled(false);
            }

        }
    }

}