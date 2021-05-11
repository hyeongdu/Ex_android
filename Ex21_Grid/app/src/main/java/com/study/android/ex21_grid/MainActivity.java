package com.study.android.ex21_grid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";
    String[] names = {"떡국","삼겹살","돼지갈비","꽃등심","스지","닭"};
    String[] price = {"1000","2000","3000","4000","5000","6000",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyAdapter adapter = new MyAdapter();

        GridView gridView1 = findViewById(R.id.gridView1);
        gridView1.setAdapter(adapter);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                int row = position / 2;
                int column = position % 2;
                Log.d(TAG, "ROW index : " + row + "Column index :"+ column);
                Log.d(TAG, names[row * 2 + column]);
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return  names.length;
        }
        @Override
        public Object getItem(int position){
            return  names[position];
        }

        @Override
        public long getItemId(int position){
            return  position;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView view1 = new TextView( getApplicationContext() );
            view1.setText(names[position]);
            view1.setTextSize(40.0f);
            view1.setTextColor(Color.BLUE);

            layout.addView(view1);

            TextView view2 = new TextView( getApplicationContext() );
            view2.setText(price[position]);
            view2.setTextSize(40.0f);
            view2.setTextColor(Color.RED);

            layout.addView(view2);
            return  layout;
        }

    }
}