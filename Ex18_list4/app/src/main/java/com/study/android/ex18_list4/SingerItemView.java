package com.study.android.ex18_list4;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout
{
    TextView textView1;
    TextView textView2;
    ImageView imageView1;

    public SingerItemView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item_view, this, true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        imageView1 = findViewById(R.id.imageView1);

    }

    public void setName(String menu){
        textView1.setText(menu);
    }
    public void setAge(String price){
        textView2.setText(price);
    }
    public void setImage(int imgNum){
        imageView1.setImageResource(imgNum);
    }

}
