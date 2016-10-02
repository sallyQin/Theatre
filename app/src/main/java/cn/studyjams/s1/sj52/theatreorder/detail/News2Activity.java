package cn.studyjams.s1.sj52.theatreorder.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import cn.studyjams.s1.sj52.theatreorder.R;

public class News2Activity extends AppCompatActivity {

    WebView webView_context2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news2);
        webView_context2 = (WebView) findViewById(R.id.webView_context2);
        assert webView_context2 != null;
        webView_context2.loadUrl("file:///android_asset/summer_summary.html");
    }
}
