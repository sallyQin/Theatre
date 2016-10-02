package cn.studyjams.s1.sj52.theatreorder.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import cn.studyjams.s1.sj52.theatreorder.R;

public class News4Activity extends AppCompatActivity {

    WebView webView_context4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news4);
        webView_context4 = (WebView) findViewById(R.id.webView_context4);
        assert webView_context4 != null;
        webView_context4.loadUrl("file:///android_asset/7.html");
    }
}
