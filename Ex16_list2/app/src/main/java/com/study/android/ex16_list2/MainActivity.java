package com.study.android.ex16_list2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] menu = {"아메리카노", "카페라떼", "바닐라라떼","감자튀김", "고구마튀김", "돼지국밥","막걸리", "오므라이스"};
    String[] price = {"1000", "2000", "3000","4000", "5000", "6000","7000", "8000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = (ListView)findViewById(R.id.listView1);

        //2단계
        final MyAdapter adapter = new MyAdapter();
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "selected : " + menu[position], Toast.LENGTH_SHORT).show();;
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {return menu.length;}

        @Override
        public Object getItem(int position){   return menu[position]; }

        public  long getItemId(int position){   return position;    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            TextView view1 = new TextView(getApplicationContext());
            view1.setText(menu[position]);
            view1.setTextSize(40.0f);
            view1.setTextColor(Color.BLUE);
            //return view1;

            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            layout.addView(view1);

            TextView view2 = new TextView(getApplicationContext());
            view2.setText(price[position]);
            view2.setTextSize(40.0f);
            view2.setTextColor(Color.RED);

            layout.addView(view2);

            return layout;

        }

    }
}