package cn.studyjams.s1.sj52.theatreorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class HotRecommendation3 extends AppCompatActivity {

    WebView hotRecommendation_txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_recommendation3);
        hotRecommendation_txt3 = (WebView) findViewById(R.id.webView_hotRecommendation_txt3);
        hotRecommendation_txt3.loadUrl("file:///android_asset/recommendations_3.html");  //加载assets下的热门推荐"冰川时代5"内容。
    }
}
