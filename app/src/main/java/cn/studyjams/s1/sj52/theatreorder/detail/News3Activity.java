package cn.studyjams.s1.sj52.theatreorder.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import cn.studyjams.s1.sj52.theatreorder.R;

public class News3Activity extends AppCompatActivity {
    WebView webView_context3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news3);
        webView_context3 = (WebView) findViewById(R.id.webView_context3);
        webView_context3.loadUrl("file:///android_asset/8.html");
    }
}
