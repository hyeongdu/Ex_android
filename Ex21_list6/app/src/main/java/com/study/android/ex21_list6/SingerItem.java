package com.study.android.ex21_list6;

public class SingerItem
{
    private String name;
    private String telNum;
    private int resId;

    public SingerItem(String name, String telNum, int resId){
        this.name = name;
        this.telNum = telNum;
        this.resId = resId;
    }

    public String getName(){ return name ;}
    public void setName(String name) { this.name = name;}
    public String getTelNum(){ return telNum;}
    public void setTelNum(String telNum) {this.telNum=telNum;}
    public int getResId(){return resId;}

    public void setResId(int resId) {
        this.resId = resId;
    }
}
