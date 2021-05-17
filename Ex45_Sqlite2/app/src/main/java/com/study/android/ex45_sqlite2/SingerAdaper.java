package com.study.android.ex45_sqlite2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SingerAdaper extends BaseAdapter {
    Context context;
    ArrayList<SingerItem> items = new ArrayList<SingerItem>();

    public SingerAdaper(Context context){
        this.context = context;
    }

    public void addItem(SingerItem item){
        items.add(item);
    }

    public void removeAllItem(){
        items.clear();
    }

    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        singer_item_view view = null;
        if(convertView == null)
        {
            view = new singer_item_view(context);
        } else {
            view = (singer_item_view) convertView;
        }

        final SingerItem item = items.get(position);
        view.setName(item.getName());
        view.setAge(item.getAge());
        view.setMobile(item.getMobile());

        return view;

    }
}
