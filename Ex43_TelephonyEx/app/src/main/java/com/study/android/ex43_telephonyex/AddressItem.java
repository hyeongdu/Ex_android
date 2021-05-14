package com.study.android.ex43_telephonyex;

import android.graphics.Bitmap;

public class AddressItem {

    private String name;
    private String telNum;
    private Bitmap photo;

    public AddressItem(String name, String telNum, Bitmap photo){
        this.name = name;
        this.telNum = telNum;
        this.photo = photo;
    }

    public String getName() { return name;}
    public void setName(String name) {this.name = name;}


    public String getTelnum() { return telNum;}
    public void setTelnum(String telNum) {this.telNum = telNum;}


    public Bitmap getResId() { return photo;}
    public void getResId(Bitmap photo) { this.photo = photo;}
}
