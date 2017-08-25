package cn.studyjams.s1.sj52.theatreorder.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.studyjams.s1.sj52.theatreorder.R;

public class ComingSoonFilmsActivity extends AppCompatActivity {
    TextView August_title, Sept_title;
    Boolean selected_Title_August = false;
    Boolean selected_Title_Sept = false;
    RecyclerView recyclerV_comingSoon;
    ComingSoonAdapter comingSoonAdapter;
    String selected_Month = "august";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon_films);
        August_title = (TextView) findViewById(R.id.August_title);
        Sept_title = (TextView) findViewById(R.id.Sept_title);

        recyclerV_comingSoon = (RecyclerView) findViewById(R.id.recyclerV_comingSoon);
        ComingSoonAdapter.mActivity = this;
        comingSoonAdapter = new ComingSoonAdapter(R.layout.coming_details_recyclerview);
        recyclerV_comingSoon.setAdapter(comingSoonAdapter);
        getSupportLoaderManager().restartLoader(0,null,comingSoonAdapter);//开启loader
        setAugustSelected();


        August_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected_Title_August) {
                    setAugustSelected();
                }
            }
        });

        Sept_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected_Title_Sept) {
                    setSeptSelected();
                }
            }
        });

    }

    protected void setAugustSelected() { //设置当8月份为被选中状态
        August_title.setPressed(true);
        August_title.setSelected(true);
        Sept_title.setPressed(false);
        Sept_title.setSelected(false);
        selected_Title_August = true;
        selected_Title_Sept = false;
        selected_Month = "august";
        getSupportLoaderManager().restartLoader(0,null,comingSoonAdapter);//开启loader
    }

    protected void setSeptSelected() { //设置当9月份为被选中状态
        August_title.setPressed(false);
        August_title.setSelected(false);
        Sept_title.setPressed(true);
        Sept_title.setSelected(true);
        selected_Title_August = false;
        selected_Title_Sept = true;
        selected_Month = "sept";
        getSupportLoaderManager().restartLoader(0,null,comingSoonAdapter);//开启loader
    }
}
