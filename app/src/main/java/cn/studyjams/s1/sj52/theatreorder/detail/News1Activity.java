package cn.studyjams.s1.sj52.theatreorder.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import cn.studyjams.s1.sj52.theatreorder.R;

/**
 * Created by Apc on 2016/9/12.
 */
public class News1Activity extends AppCompatActivity{
    WebView webView_context1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news1);
        webView_context1 = (WebView) findViewById(R.id.webView_context1);
        assert webView_context1 != null;
        webView_context1.loadUrl("file:///android_asset/9.html");

    }
}
