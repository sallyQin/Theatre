package cn.studyjams.s1.sj52.theatreorder.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import cn.studyjams.s1.sj52.theatreorder.R;

/**
 * Created by Apc on 2016/9/13.
 */
public class News5Activity extends AppCompatActivity{
    WebView webView_context5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news5);
        webView_context5 = (WebView) findViewById(R.id.webView_context5);
        webView_context5.loadUrl("file:///android_asset/Top10.html");
    }
}
