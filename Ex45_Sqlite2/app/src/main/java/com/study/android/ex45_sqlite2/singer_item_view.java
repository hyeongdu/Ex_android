package com.study.android.ex45_sqlite2;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class singer_item_view extends LinearLayout {
    TextView textView1;
    TextView textView2;
    TextView textView3;

  public singer_item_view(Context context){
      super(context);

      LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
      inflater.inflate(R.layout.activity_singer_item_view, this, true);

      textView1 = findViewById(R.id.textView1);
      textView2 = findViewById(R.id.textView2);
      textView3 = findViewById(R.id.textView3);
  }

  public void setName(String name){
      textView1.setText(name);
  }

    public void setAge(int age){
        textView2.setText(String.valueOf(age));
    }

    public void setMobile(String mobile){
        textView3.setText(mobile);
    }
}