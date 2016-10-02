package cn.studyjams.s1.sj52.theatreorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by apc on 16/10/2.
 */
public class HotRecommendation1  extends AppCompatActivity{

    WebView hotRecommendation_txt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_recommendation1);
        hotRecommendation_txt1 = (WebView) findViewById(R.id.webView_hotRecommendation_txt1);
        hotRecommendation_txt1.loadUrl("file:///android_asset/recommendations_1.html");  //加载assets下的热门推荐"湄公河行动"内容。

    }
}
