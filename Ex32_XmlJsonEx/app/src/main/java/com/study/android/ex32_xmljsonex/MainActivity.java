package com.study.android.ex32_xmljsonex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtn1Clicked(View v){
        getJsonData1();
    }

    public void onBtn2Clicked(View v){
        getJsonData2();
    }

    public void onBtn3Clicked(View v){
        getJsonData3();
    }

    public void onBtn4Clicked(View v){
        getXmlData1();
    }

    private void getJsonData1(){
        // {"number":[1,2,3,4,5]}
        String json4arr = "{\"number\":[1,2,3,4,5]}";
        try{
            JSONObject jobj = new JSONObject(json4arr);
            JSONArray jarr = jobj.getJSONArray("number");
            for(int i = 0; i<jarr.length();i++){
                int tmp=jarr.getInt(i);
                Log.d(TAG, "Json Data : " + tmp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getJsonData2(){
        // {"color":{"top":"red","bottom":"black","left":"blue","right":"green"}}

        String json4arr = "{\"color\":{\"top\":\"red\",\"bottom\":\"black\",\"left\":\"blue\",\"right\":\"green\"}}";
        try{
            JSONObject jobj = new JSONObject(json4arr);
            JSONObject color = jobj.getJSONObject("color");
           if(color.has("left")){
               String top_color = color.getString("left");
               Log.d(TAG, "left color : " + top_color);
           }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getJsonData3() {
        // {"menu": {"id": "file", "value": "File", "popup": { "menuitem": [ {"value": "New", "onclick": "CreateNewDoc()"}, {"value": "Open", "onclick": "OpenDoc()"}, {"value": "Close", "onclick": "CloseDoc()"}]}}}

        String jString = "{\"menu\": {\"id\": \"file\", \"value\": \"File\", \"popup\": { \"menuitem\": [ {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"}, {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"}, {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}]}}}";
        try {
            JSONObject jObject = new JSONObject(jString);
            JSONObject menuObject = jObject.getJSONObject("menu");

            String attributeId = menuObject.getString("id");
            String attributeValue = menuObject.getString("value");
            JSONObject popupObject = menuObject.getJSONObject("popup");

            JSONArray menuitemArray = popupObject.getJSONArray("menuitem");
            for (int i = 0; i < menuitemArray.length(); i++) {

                Log.d(TAG, "length : " + menuitemArray.length());
                Log.d(TAG, "value:" + menuitemArray.getJSONObject(i).getString("value"));
                Log.d(TAG, "onclick:" + menuitemArray.getJSONObject(i).getString("onclick"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getXmlData1() {

        try {
            ArrayList<String> aNumber = new ArrayList<String>();
            ArrayList<String> aWord   = new ArrayList<String>();
            ArrayList<String> aMean   = new ArrayList<String>();

            // XML ????????? ????????? ?????? ??????
            int event = 0;
            String currentTag = null;
            Stack<String> tagStack = new Stack<String>();

            // ?????? ?????? ??????! ?????? ????????? ????????? ?????????
            // <word>aaa<any>any text</any>bbb</word>?????? bbb??? ????????? ????????? ?????????.
            boolean useStack = true;

            // XML ??????
            XmlPullParser parser = getResources().getXml(R.xml.test);

            // ?????? ??????
            while((event = parser.next()) != XmlPullParser.END_DOCUMENT) {

                switch(event) {

                    case XmlPullParser.START_TAG:
                        // ?????? ????????? ????????? currentTag??? ??????
                        if(useStack && currentTag != null) {
                            tagStack.push(currentTag);
                        }
                        currentTag = parser.getName();
                        break;

                    case XmlPullParser.TEXT:
                        // currentTag??? ??????????????? ?????? ????????????...
                        if(currentTag != null) {

                            if (currentTag.equals("number")) {
                                String value = parser.getText();
                                aNumber.add(value);
                            } else if (currentTag.equals("word")) {
                                String value = parser.getText();
                                aWord.add(value);
                            } else if (currentTag.equals("mean")) {
                                String value = parser.getText();
                                aMean.add(value);
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        // ?????? ????????? ????????? currentTag ?????????
                        if(useStack && tagStack.size() > 0) {
                            currentTag = tagStack.pop();
                        }
                        else {
                            currentTag = null;
                        }
                        break;

                    default:
                        break;
                }
            }

            for(int i = 0; i < aNumber.size(); i++)
            {
                Log.d(TAG, "number : " + aNumber.get(i));
                Log.d(TAG, "word   : " + aWord.get(i));
                Log.d(TAG, "mean   : " + aMean.get(i));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }



    }

}