package com.study.android.ex29_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ProgressBar mProgress1;
    int mProgressStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress1 = findViewById(R.id.progressBar);
    }
    public void onBtn1Clicked(View v){

        //싱크 시작
        new CounterTask().execute(0);
    }
    class CounterTask extends AsyncTask<Integer, Integer, Integer>{
        protected void onPreExecute(){
        }
        protected  Integer doInBackground(Integer... value){
            while(mProgressStatus <100){
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                }
                mProgressStatus++;

                //더한 값이 바로 들어감
                publishProgress(mProgressStatus);
            }
            return  mProgressStatus;
        }
        protected  void onProgressUpdate(Integer... value){
            mProgress1.setProgress(mProgressStatus);
        }
        protected void onPostExecute(Integer result){
            mProgress1.setProgress(mProgressStatus);
        }
    }
}