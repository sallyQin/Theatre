package cn.studyjams.s1.sj52.theatreorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class HotRecommendation2 extends AppCompatActivity {
    WebView hotRecommendation_txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_recommendation2);
        WebView hotRecommendation_txt2 = (WebView) findViewById(R.id.webView_hotRecommendation_txt2);
        hotRecommendation_txt2.loadUrl("file:///android_asset/recommendations_2.html");  //加载assets下的热门推荐"七月与安生"内容。
    }
}
