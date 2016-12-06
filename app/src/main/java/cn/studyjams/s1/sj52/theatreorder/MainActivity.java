package cn.studyjams.s1.sj52.theatreorder;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.studyjams.s1.sj52.theatreorder.detail.News1Activity;
import cn.studyjams.s1.sj52.theatreorder.detail.News2Activity;
import cn.studyjams.s1.sj52.theatreorder.detail.News3Activity;
import cn.studyjams.s1.sj52.theatreorder.detail.News4Activity;
import cn.studyjams.s1.sj52.theatreorder.detail.News5Activity;

public class MainActivity extends AppCompatActivity {

    LinearLayout layoutUI_TheatreInfo;
    LinearLayout theatreInfo;
    LinearLayout ticketOrder;
    LinearLayout  home;
    LinearLayout layoutUI_Home;
    LinearLayout layoutUI_ticketOrder;
    ImageView home_image;
    ImageView theatreInfo_image;
    ImageView ticketOrder_image;
    ImageView hotRecommendation1;
    ImageView hotRecommendation2;
    ImageView hotRecommendation3;
    TextView location_text;
    TextView fileName_text;
    TextView ticket_location_title;
    RecyclerView recyclerView_theatre;
    RecyclerView recyclerView_ticketOrder_poker;
    RecyclerView recy_ticketDate;
    RecyclerView recy_ticketActionCutting;
    TicketActionCuttingAdapter ticketActionCuttingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = (LinearLayout) findViewById(R.id.title_home);
        theatreInfo = (LinearLayout) findViewById(R.id.title_theatre_situation);
        ticketOrder = (LinearLayout) findViewById(R.id.title_order_ticket);

        layoutUI_Home = (LinearLayout) findViewById(R.id.linearUI_home);
        layoutUI_TheatreInfo = (LinearLayout) findViewById(R.id.linearUI_theatreInfo);
        layoutUI_ticketOrder = (LinearLayout) findViewById(R.id.linearUI_ticketOrder);

        home_image = (ImageView) findViewById(R.id.home);
        theatreInfo_image = (ImageView) findViewById(R.id.theatre_info);
        ticketOrder_image = (ImageView) findViewById(R.id.ticket_order);

        location_text = (TextView) findViewById(R.id.location_text);
        recyclerView_theatre = (RecyclerView) findViewById(R.id.Recy_theatreInfo);
        recyclerView_ticketOrder_poker = (RecyclerView) findViewById(R.id.Recy_ticketOrder_poker);
        recy_ticketDate = (RecyclerView) findViewById(R.id.Recy_ticketDate);
        recy_ticketActionCutting = (RecyclerView) findViewById(R.id.Recy_ticketActionCutting);

        hotRecommendation1  = (ImageView) findViewById(R.id.recommendation_1);
        hotRecommendation2 = (ImageView) findViewById(R.id.recommendation_2);
        hotRecommendation3 = (ImageView) findViewById(R.id.recommendation_3);



        /**
         * setting 1-1 home Tabbed button and its UI parts
         **/
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert home_image != null;
                home_image.setImageResource(R.drawable.home);
                theatreInfo_image.setImageResource(R.drawable.title_theatre_info);
                ticketOrder_image.setImageResource(R.drawable.title_ticket_order);
                layoutUI_Home.setVisibility(View.VISIBLE);
                layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                layoutUI_ticketOrder.setVisibility(View.INVISIBLE);



            }
        });


        /**
         * setting 1-2 theatre Tabbed button and its UI parts
         **/
        theatreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert theatreInfo_image != null;
                theatreInfo_image.setImageResource(R.drawable.theatre_info);
                home_image.setImageResource(R.drawable.home_title);
                ticketOrder_image.setImageResource(R.drawable.title_ticket_order);
                layoutUI_TheatreInfo.setVisibility(View.VISIBLE);
                layoutUI_Home.setVisibility(View.INVISIBLE);
                layoutUI_ticketOrder.setVisibility(View.INVISIBLE);

                location_text.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG); //给“无锡”字下加下划线。
                recyclerView_theatre.setHasFixedSize(true);
                recyclerView_theatre.setAdapter(new TheatreAdapter());


            }
        });

        TheatreAdapter.mainActivityThea = MainActivity.this;

        ticketActionCuttingAdapter = new TicketActionCuttingAdapter();
        ticketActionCuttingAdapter.mainActivity = this;


        /**
         * setting 1-3 ticketOrder Tabbed button and its UI parts
         **/
        ticketOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert ticketOrder_image != null;
                ticketOrder_image.setImageResource(R.drawable.ticket_order);
                home_image.setImageResource(R.drawable.home_title);
                theatreInfo_image.setImageResource(R.drawable.title_theatre_info);
                layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                layoutUI_Home.setVisibility(View.INVISIBLE);
                layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);


                fileName_text = (TextView) findViewById(R.id.filmName_selected);
                ticket_location_title = (TextView) findViewById(R.id.ticket_location_title);
                assert ticket_location_title != null;
                ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = MainActivity.this;
                recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                recy_ticketActionCutting.setAdapter( ticketActionCuttingAdapter);//场次 切换


            }
        });

    }

    /**
     * setting news details of home clicker's listeners
     **/
    public void getNewsDetails(View view){
        switch (view.getId()){
            case R.id.news_txt1:
            Intent intent1 = new Intent(MainActivity.this,News1Activity.class);
            startActivity(intent1);
            break;
            case R.id.news_txt2:
                Intent intent2 = new Intent(MainActivity.this,News2Activity.class);
                startActivity(intent2);
                break;
            case R.id.news_txt3:
                Intent intent3 = new Intent(MainActivity.this,News3Activity.class);
                startActivity(intent3);
                break;
            case R.id.news_txt4:
                Intent intent4 = new Intent(MainActivity.this,News4Activity.class);
                startActivity(intent4);
                break;
            case R.id.news_txt5:
                Intent intent5 = new Intent(MainActivity.this,News5Activity.class);
                startActivity(intent5);
                break;
        }
    }

    /**
     * setting hotRecommendation of home clicker's listeners
     **/

    public  void hotRecommendationClick(View view){
         switch (view.getId()){
             case R.id.recommendation_1:
                 Intent intent1 = new Intent(MainActivity.this,HotRecommendation1.class);
                 startActivity(intent1);
                 break;
             case R.id.recommendation_2:
                 Intent intent2 = new Intent(MainActivity.this,HotRecommendation2.class);
                 startActivity(intent2);
                 break;
             case R.id.recommendation_3:
                 Intent intent3 = new Intent(MainActivity.this,HotRecommendation3.class);
                 startActivity(intent3);
                 break;
         }
    }

}

