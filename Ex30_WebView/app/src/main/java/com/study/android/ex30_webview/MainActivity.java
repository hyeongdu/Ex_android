package com.study.android.ex30_webview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  static final String TAG = "lecture";

    private final Handler handler = new Handler();
    public static CustomProgressDialog progressDialog = null ;
    WebView web;
    EditText etMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = findViewById(R.id.web1);
        web.clearCache(true);                                      // 캐쉬 지우기
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); // 캐쉬 사용하지 않기
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDefaultTextEncodingName("UTF-8");
        etMessage = findViewById(R.id.etMessage);

        web.loadUrl("https://www.google.com");
        web.setWebViewClient(new myWebView());
        web.setWebChromeClient(new myWebChromeClient());
        web.setHorizontalScrollBarEnabled(false); // 세로 scroll 제거
        web.setVerticalScrollBarEnabled(false);    // 가로 scroll 제거
        web.addJavascriptInterface(new JavaScriptBridge(), "android");

    }

    public void btnLocalHtml(View v){
        //local html 부르기
        web.loadUrl("file:///android_asset/jsexam.html");
    }

    public void btnWebHtml(View v){
        //web html 부르기
        web.loadUrl("https://www.google.com");


    }

    public void btnJSCall(View v){
        web.loadUrl("javascript:setMessage('" + etMessage.getText()+"')");
    }

    public void btnStringLoad(View v){
        //String 웹뷰로 부르기
            String summary = "<html>" +
                    "<head>" +
                    "<meta charset=\"utf-8\">" +
                    "</head>" +
                    "<body>" +
                    "Hello~ 아메리카노" +
                    "<img src = 'https://goo.gl/LvIXUL'>" +
                    "</body>" +
                    "</html>" ;
            web.loadData(summary, "text/html; charset=UTF-8", null);
    }

    //이전키 눌렀을 때 webview 히스토리 back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()){
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        web.stopLoading();
    }

    // 웹뷰에서 자바스크립트의 Alert/Confirm을 보여 줄 수 있도록 한다.
    private class myWebChromeClient extends WebChromeClient
    {

        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                                 final android.webkit.JsResult result){
            result.confirm();

            new AlertDialog.Builder(view.getContext())
                    //.setIcon(R.drawable.icon);
                    //.setTitle(R.string.title_activity_main)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new AlertDialog.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which) {
                                    result.confirm();
                                }
                            })
                    .setCancelable(true)
                    .create()
                    .show();

            return true;
        };

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final android.webkit.JsResult result)
        {
            new AlertDialog.Builder(view.getContext())
                    //.setTitle(R.string.title_activity_main)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    result.confirm();
                                }
                            })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    result.cancel();
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();

            return true;
        };


    }


    // 안드로이드 자체 WebView가 아닌 내가 만든 WebView 직접 사용한다고 명시
    private class myWebView extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            if (url.startsWith("http://")) {
                view.loadUrl(url);
                return true;
            }
            if (url.startsWith("https://")) {
                view.loadUrl(url);
                return true;
            }
            if (url.startsWith("mailto:")) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                startActivity(i);
                return true;
            }
            if (url.startsWith("sms:")) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                startActivity(i);
                return true;
            }
            if (url.startsWith("tel:")) {
                int permissionCheck = ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.CALL_PHONE);
                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(getApplicationContext(),
                            "전화걸기를 사용하시려면 먼저 권한을 승인해 주세요.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                    startActivity(i);
                }

                return true;
            }
            if (url.startsWith("geo:")) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
                return true;
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
          progressDialog = new CustomProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            super.onReceivedError(view, errorCode, description, failingUrl);

            Toast.makeText(getApplicationContext(), "loading Error : " + description, Toast.LENGTH_SHORT).show();

            if(progressDialog!=null){
                progressDialog.dismiss();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url){
            if(progressDialog!=null){
                progressDialog.dismiss();
            }
        }

    }



    // 자바스크립트에서 Call할 클래스 함수
    final class JavaScriptBridge {

        @JavascriptInterface
        public void test(){
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "테스트", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Parameter must be final
        @JavascriptInterface
        public void testParams(final String arg){
            handler.post(new Runnable() {
                public void run() {
                    Log.d(TAG, "setMessage("+arg+")");
                    Toast.makeText(getApplicationContext(), "테스트파라미터:"+arg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}