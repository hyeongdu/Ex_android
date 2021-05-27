package com.study.android.ex54_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    SingerAdapter adapter;
    RecyclerView mRecyclerView;
    int nCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new SingerAdapter(this);
        SingerItem item1 = new SingerItem("아메리카노", "10",R.drawable.face1);
        adapter.addItem(item1);
        SingerItem item2 = new SingerItem("엄청", "20",R.drawable.face1);
        adapter.addItem(item2);
        SingerItem item3 = new SingerItem("달게", "30",R.drawable.face1);
        adapter.addItem(item3);

        mRecyclerView = findViewById(R.id.recyclerView1);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setItemClick(new SingerAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "selected : " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

     nCount = 1;
    }

    public void onBtn1Clicked(View v){
      nCount ++ ;

        SingerItem item = new SingerItem("홍길동" + nCount, "22", R.drawable.face1);
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }
}