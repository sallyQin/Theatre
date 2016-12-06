package cn.studyjams.s1.sj52.theatreorder;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import cn.studyjams.s1.sj52.theatreorder.data.HaiAnTheatreInfo;
import cn.studyjams.s1.sj52.theatreorder.detail.ConfirmUltimateOrderActivity;
public class ChooseSeatActivity extends AppCompatActivity {
    TextView time;
    TextView actionCutting;
    TextView filmTitle;
    TextView day_txt;
    TextView actionCutting_Title;
    TextView booking_txt1;
    TextView booking_txt2;
    TextView booking_txt3;
    TextView booking_txt4;
    Button button_chooseSeat;
    String strTitle;//记录订票影片名
    String version;//记录电影版本
    String cinemaName;//记录影院名
    String dateStr;//记录日期
    String startTime;//记录订票时间
    String projectionHallNum = "3号厅";//记录厅号（默认为"3号厅"）

    public static int width;// 屏幕宽度
    public static int height;// 屏幕高度
    private ProgressDialog proDialog;
    TextView hallName;// 影院的影厅名称
    private ImageView mSwitchZoom;// 发大缩小按键
    private MovieSeatViewUtil seatView;
    private boolean setZoom = false;// 设置view的放大缩小
    ArrayList<MovieRealTimeHallAllSeats> SeatModelListHall2;   //2号厅座位图数据
    ArrayList<MovieRealTimeHallAllSeats> SeatModelListHall3;   //3号厅座位图数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat);
        HaiAnTheatreInfo data1 = getIntent().getParcelableExtra("data");
        String projectionHall1 = data1.projectionHall;
        button_chooseSeat = (Button) findViewById(R.id.button_chooseSeat);


        /** 设置“跳转完成选座”按钮的事件监听器 **/
        button_chooseSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSeatActivity.this, ConfirmUltimateOrderActivity.class);
                intent.putExtra("filmTitle", strTitle);
                intent.putExtra("version",version);
                intent.putExtra("cinemaName",cinemaName);
                intent.putExtra("date",dateStr);
                intent.putExtra("startTime",startTime);
                intent.putExtra("projectionHallNum",projectionHallNum);
                if(TextUtils.isEmpty(booking_txt1.getText().toString()) && TextUtils.isEmpty(booking_txt2.getText().toString()) &&
                    TextUtils.isEmpty(booking_txt3.getText().toString()) && TextUtils.isEmpty(booking_txt4.getText().toString())){
                    View view = LayoutInflater.from(ChooseSeatActivity.this).inflate(R.layout.my_toast_chooseseat_hint, (ViewGroup) findViewById(R.id.chooseSeat_layout),false);
                    Toast toast =  Toast.makeText(ChooseSeatActivity.this,"提示：请先选择座位！",Toast.LENGTH_LONG);
                    toast.setView(view);
                    toast.show();
            }else {
                    startActivity(intent);
                }
        }});


        /**若是3，加载“3号厅”布局 **/
        if (projectionHall1.equals("3号厅")) {
            add();        //添加座位号信息
            initView();   //初始化影院订座主图

        }
        /**若是2，加载“2号厅”布局 **/
        else if (projectionHall1.equals("2号厅")){
            add2HallingInfo();
            initViewHall2();
            projectionHallNum = "2号厅";
        }
        /**其它默认情况：加载“3号厅”布局**/
        else{
            add();
            initView();
        }

        booking_txt1 = (TextView) findViewById(R.id.booking_text1);
        booking_txt2 = (TextView) findViewById(R.id.booking_text2);
        booking_txt3 = (TextView) findViewById(R.id.booking_text3);
        booking_txt4 = (TextView) findViewById(R.id.booking_text4);
        time = (TextView) findViewById(R.id.time_txt);
        actionCutting = (TextView) findViewById(R.id.ActionCutting_txt);
        filmTitle = (TextView) findViewById(R.id.filmTitle);
        actionCutting_Title = (TextView) findViewById(R.id.actionCutting_Title);

        Intent intent = getIntent();
        strTitle = intent.getStringExtra("filmTitle");//设置跳转过来的 订票影片名
        filmTitle.setText(strTitle);

        day_txt = (TextView) findViewById(R.id.day_txt);     //设置跳转过来的 订票日期
        if (day_txt != null) {
            day_txt.setText(TicketOrderDateAdapter.date_done);
            dateStr = day_txt.getText().toString();
        }

        HaiAnTheatreInfo data = intent.getParcelableExtra("data");
        startTime = data.startTime;
        version = data.edition;

        time.setText(startTime);                                  //设置跳转过来的 订票时间

        actionCutting.setText(version);                         //设置跳转过来的 订票场次

        cinemaName = intent.getStringExtra("ticket_location_title");
        actionCutting_Title.setText(cinemaName);       //设置传过来的 订票页面标题名(影院名)
    }


    /** 设置初始视图界面 **/
    private void initView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); //取得的屏幕宽、高维度
        width = (displayMetrics.widthPixels);
        height = (displayMetrics.heightPixels);
        hallName = (TextView) findViewById(R.id.movie_buy_hall_info);
        mSwitchZoom = (ImageView) findViewById(R.id.setViewZoom);

        proDialog = ProgressDialog.show(ChooseSeatActivity.this, "加载",  //加载进度条
                "加载数据中，请稍候...", true, true);
        proDialog.setCanceledOnTouchOutside(false);

        HaiAnTheatreInfo data = getIntent().getParcelableExtra("data");  //设置跳转过来的 影厅名
        String projectionHall = data.projectionHall;
        hallName.setText(projectionHall);

        //点击放大缩小（座位图）图标的监听器
        mSwitchZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setZoom) {
                    seatView.SetZoom(0);
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tosmall));
                    setZoom = true;
                } else  {
                    seatView.SetZoom(1);
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tobig));
                    setZoom = false;
                }
            }
        });
        new Thread(runnable).start();

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {   //（Handler根据接收的消息,处理UI更新） 取消进程条并更新UI。
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case 1:
                    proDialog.dismiss();
                    updateUIHall3();
                    break;
            }
        }
    };

    /**
     * 显示 3号厅 所有座位
     */
    protected void updateUIHall3() {

        LinearLayout myView = (LinearLayout) findViewById(R.id.seatViewCont);
        if (myView != null) {
            myView.removeAllViews();
        }
        seatView = new MovieSeatViewUtil(this, SeatModelListHall3);
        if (myView != null) {
            myView.addView(seatView);
        }
        seatView.setZoomChangeListener(new MovieSeatViewUtil.ZoomChangeListener() {
            public void ZoomChange(int box_size, int zoom_level,
                                   boolean isBigZoom) {
                if (!isBigZoom) {  //点击缩小
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tobig));
                    setZoom = false;
                } else {
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tosmall));
                    setZoom = true;
                }
                LinearLayout myView1 = (LinearLayout) findViewById(R.id.seatRaw);
                assert myView1 != null;
                myView1.removeAllViews();
                for (int i = 0; i < SeatModelListHall3.size(); i++) {
                    TextView textRow = new TextView(ChooseSeatActivity.this);
                    textRow.setText(i + 1 + "");
                    textRow.setTextSize(2 * zoom_level);
                    textRow.setTextColor(Color.parseColor("#7d8fab"));
                    textRow.setGravity(Gravity.CENTER_HORIZONTAL);
                    textRow.setPadding(0, box_size / 5, 0, 0);
                    textRow.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, box_size));
                    myView1.addView(textRow);
                }
            }
        });

    }

    Runnable runnable = new Runnable() {
        public void run() {
            Message msg = handler.obtainMessage();
            msg.arg1 = 1;
            handler.sendMessage(msg);
        }
    };

    protected void add() {
        SeatModelListHall3 = new ArrayList<>();
        MovieRealTimeHallAllSeats seat1 = new MovieRealTimeHallAllSeats();
        seat1.setLocNo("01");
        seat1.setHallNo("HM10001006806");
        seat1.setHallName("3号厅");
        seat1.setRowNo("1");
        seat1.setSeatImgRow("01");
        seat1.setColumnNo("ZL,ZL,ZL,ZL,1,2,ZL,ZL,5,6,7,8,9,10,11,12,13,ZL,ZL,16,17,ZL,ZL,ZL,ZL");
        seat1.setSeatType("3,3,3,3,2,2,1,1,2,2,1,1,1,1,1,2,2,1,1,2,2,3,3,3,3");
        seat1.setSeatNo(",,,,06010105,06010106,06010107,06010108,06010109,06010110,06010111,06010112,06010113,06010114,06010115,06010116,06010117,06010118,06010119,06010120,06010121,06010122,06010123,,");
        seat1.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall3.add(seat1);

        MovieRealTimeHallAllSeats seat2 = new MovieRealTimeHallAllSeats();
        seat2.setLocNo("01");
        seat2.setHallNo("HM10001006806");
        seat2.setHallName("3号厅");
        seat2.setRowNo("2");
        seat2.setSeatImgRow("01");
        seat2.setColumnNo("ZL,ZL,ZL,ZL,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,ZL,ZL,ZL,ZL");
        seat2.setSeatType("3,3,3,3,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,3,3,3,3");
        seat2.setSeatNo(",,,,06010205,06010206,06010207,06010208,06010209,06010210,06010211,06010212,06010213,06010214,06010215,06010216,06010217,06010218,06010219,06010220,06010221,06010222,06010223,,");
        seat2.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall3.add(seat2);

        MovieRealTimeHallAllSeats seat3 = new MovieRealTimeHallAllSeats();
        seat3.setLocNo("01");
        seat3.setHallNo("HM10001006806");
        seat3.setHallName("3号厅");
        seat3.setRowNo("3");
        seat3.setSeatImgRow("01");
        seat3.setColumnNo("ZL,ZL,ZL,ZL,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,ZL,ZL,ZL,ZL");
        seat3.setSeatType("3,3,3,3,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,3,3,3,3");
        seat3.setSeatNo(",,,,06010305,06010306,06010307,06010308,06010309,06010310,06010311,06010312,06010313,06010314,06010315,06010316,06010317,06010318,06010319,06010320,06010321,06010322,06010323,,");
        seat3.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall3.add(seat3);

        MovieRealTimeHallAllSeats seat4 = new MovieRealTimeHallAllSeats();
        seat4.setLocNo("01");
        seat4.setHallNo("HM10001006806");
        seat4.setHallName("3号厅");
        seat4.setRowNo("4");
        seat4.setSeatImgRow("01");
        seat4.setColumnNo("1,2,ZL,ZL,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,ZL,ZL,24,25");
        seat4.setSeatType("2,2,3,3,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,3,3,2,2");
        seat4.setSeatNo(",,,,06010405,06010406,06010407,06010408,06010409,06010410,06010411,06010412,06010413,06010414,06010415,06010416,06010417,06010418,06010419,06010420,06010421,06010422,06010423,,");
        seat4.setSeatStatus("1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1");
        SeatModelListHall3.add(seat4);

        MovieRealTimeHallAllSeats seat5 = new MovieRealTimeHallAllSeats();
        seat5.setLocNo("01");
        seat5.setHallNo("HM10001006806");
        seat5.setHallName("3号厅");
        seat5.setRowNo("5");
        seat5.setSeatImgRow("01");
        seat5.setColumnNo("1,2,ZL,ZL,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,ZL,ZL,24,25");
        seat5.setSeatType("2,2,3,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,3,2,2");
        seat5.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,06010514,06010515,06010516,06010517,06010518,06010519,06010520,06010521,06010522,06010523,,");
        seat5.setSeatStatus("0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0");
        SeatModelListHall3.add(seat5);

        MovieRealTimeHallAllSeats seat6 = new MovieRealTimeHallAllSeats();
        seat6.setLocNo("01");
        seat6.setHallNo("HM10001006806");
        seat6.setHallName("3号厅");
        seat6.setRowNo("6");
        seat6.setSeatImgRow("01");
        seat6.setColumnNo("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25");
        seat6.setSeatType("2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2");
        seat6.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,06010514,06010515,06010516,06010517,06010518,06010519,06010520,06010521,06010522,06010523,,");
        seat6.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall3.add(seat6);

        MovieRealTimeHallAllSeats seat7 = new MovieRealTimeHallAllSeats();
        seat7.setLocNo("01");
        seat7.setHallNo("HM10001006806");
        seat7.setHallName("3号厅");
        seat7.setRowNo("7");
        seat7.setSeatImgRow("01");
        seat7.setColumnNo("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25");
        seat7.setSeatType("2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2");
        seat7.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,06010514,06010515,06010516,06010517,06010518,06010519,06010520,06010521,06010522,06010523,,");
        seat7.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall3.add(seat7);

    }

    private void initViewHall2() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); //取得的屏幕宽、高维度
        width = (displayMetrics.widthPixels);
        height = (displayMetrics.heightPixels);
        hallName = (TextView) findViewById(R.id.movie_buy_hall_info);
        mSwitchZoom = (ImageView) findViewById(R.id.setViewZoom);

        proDialog = ProgressDialog.show(ChooseSeatActivity.this, "加载",  //加载进度条
                "加载数据中，请稍候...", true, true);
        proDialog.setCanceledOnTouchOutside(false);

        HaiAnTheatreInfo data = getIntent().getParcelableExtra("data");  //设置跳转过来的 影厅名
        String projectionHall = data.projectionHall;
        hallName.setText(projectionHall);

        //点击放大缩小（座位图）图标的监听器
        mSwitchZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setZoom) {
                    seatView.SetZoom(0);
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tosmall));
                    setZoom = true;
                } else  {
                    seatView.SetZoom(1);
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tobig));
                    setZoom = false;
                }
            }
        });
        new Thread(runnable2).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler2 = new Handler() {   //（Handler根据接收的消息,处理UI更新） 取消进程条并更新UI。
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 1:
                    proDialog.dismiss();
                    updateUIHall2();
                    break;
            }
        }
    };

    /**
     * 显示 2号厅 所有座位
     */
    protected void updateUIHall2() {

        LinearLayout myView = (LinearLayout) findViewById(R.id.seatViewCont);
        if (myView != null) {
            myView.removeAllViews();
        }
        seatView = new MovieSeatViewUtil(this, SeatModelListHall2);
        if (myView != null) {
            myView.addView(seatView);
        }
        seatView.setZoomChangeListener(new MovieSeatViewUtil.ZoomChangeListener() {
            public void ZoomChange(int box_size, int zoom_level,
                                   boolean isBigZoom) {
                if (!isBigZoom) {  //点击缩小
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tobig));
                    setZoom = false;
                } else {
                    mSwitchZoom.setImageDrawable(getResources().getDrawable(
                            R.drawable.movie_tosmall));
                    setZoom = true;
                }
                LinearLayout myView1 = (LinearLayout) findViewById(R.id.seatRaw);
                assert myView1 != null;
                myView1.removeAllViews();
                for (int i = 0; i < SeatModelListHall2.size(); i++) {
                    TextView textRow = new TextView(ChooseSeatActivity.this);
                    textRow.setText(i + 1 + "");
                    textRow.setTextSize(2 * zoom_level);
                    textRow.setTextColor(Color.parseColor("#7d8fab"));
                    textRow.setGravity(Gravity.CENTER_HORIZONTAL);
                    textRow.setPadding(0, box_size / 5, 0, 0);
                    textRow.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, box_size));
                    myView1.addView(textRow);
                }
            }
        });
   }


    Runnable runnable2 = new Runnable() {
        public void run() {
            Message msg = handler2.obtainMessage();
            msg.arg1= 1;
            handler2.sendMessage(msg);
        }
    };



    void add2HallingInfo() {
        SeatModelListHall2 = new ArrayList<>();
        MovieRealTimeHallAllSeats seat1 = new MovieRealTimeHallAllSeats();
        seat1.setLocNo("01");
        seat1.setHallNo("HM10001006806");
        seat1.setHallName("2号厅");
        seat1.setRowNo("1");
        seat1.setSeatImgRow("01");
        seat1.setColumnNo("ZL,1,2,3,4,5,6,7,8,9,10,11,12,13,ZL");
        seat1.setSeatType("3,2,2,1,1,1,1,1,1,1,1,1,2,2,3");
        seat1.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat1.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall2.add(seat1);

        MovieRealTimeHallAllSeats seat2 = new MovieRealTimeHallAllSeats();
        seat2.setLocNo("01");
        seat2.setHallNo("HM10001006806");
        seat2.setHallName("2号厅");
        seat2.setRowNo("2");
        seat2.setSeatImgRow("01");
        seat2.setColumnNo("ZL,1,2,3,4,5,6,7,8,9,10,11,12,13,ZL");
        seat2.setSeatType("3,2,2,1,1,1,1,1,1,1,1,1,2,2,3");
        seat2.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat2.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall2.add(seat2);

        MovieRealTimeHallAllSeats seat3 = new MovieRealTimeHallAllSeats();
        seat3.setLocNo("01");
        seat3.setHallNo("HM10001006806");
        seat3.setHallName("2号厅");
        seat3.setRowNo("3");
        seat3.setSeatImgRow("01");
        seat3.setColumnNo("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        seat3.setSeatType("2,2,1,1,1,1,1,1,1,1,1,1,1,2,2");
        seat3.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat3.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall2.add(seat3);

        MovieRealTimeHallAllSeats seat4 = new MovieRealTimeHallAllSeats();
        seat4.setLocNo("01");
        seat4.setHallNo("HM10001006806");
        seat4.setHallName("2号厅");
        seat4.setRowNo("4");
        seat4.setSeatImgRow("01");
        seat4.setColumnNo("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        seat4.setSeatType("2,2,1,1,1,1,1,1,1,1,1,1,1,2,2");
        seat4.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat4.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall2.add(seat4);

        MovieRealTimeHallAllSeats seat5 = new MovieRealTimeHallAllSeats();
        seat5.setLocNo("01");
        seat5.setHallNo("HM10001006806");
        seat5.setHallName("2号厅");
        seat5.setRowNo("5");
        seat5.setSeatImgRow("01");
        seat5.setColumnNo("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        seat5.setSeatType("2,2,1,1,1,1,1,1,1,1,1,1,1,2,2");
        seat5.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat5.setSeatStatus("0,0,0,0,0,0,0,1,1,1,0,0,0,0,0");
        SeatModelListHall2.add(seat5);

        MovieRealTimeHallAllSeats seat6 = new MovieRealTimeHallAllSeats();
        seat6.setLocNo("01");
        seat6.setHallNo("HM10001006806");
        seat6.setHallName("2号厅");
        seat6.setRowNo("6");
        seat6.setSeatImgRow("01");
        seat6.setColumnNo("ZL,ZL,ZL,1,2,3,4,5,6,7,8,9,ZL,ZL,ZL");
        seat6.setSeatType("3,3,3,1,2,1,1,1,1,1,2,3,3,3,1");
        seat6.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat6.setSeatStatus("0,0,0,0,0,0,1,1,0,0,0,0,0,0,0");
        SeatModelListHall2.add(seat6);

        MovieRealTimeHallAllSeats seat7 = new MovieRealTimeHallAllSeats();
        seat7.setLocNo("01");
        seat7.setHallNo("HM10001006806");
        seat7.setHallName("2号厅");
        seat7.setRowNo("7");
        seat7.setSeatImgRow("01");
        seat7.setColumnNo("ZL,ZL,1,2,3,4,5,6,7,8,9,10,11,ZL,ZL");
        seat7.setSeatType("3,3,1,2,1,1,1,1,1,1,1,2,3,3,1");
        seat7.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat7.setSeatStatus("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        SeatModelListHall2.add(seat7);

        MovieRealTimeHallAllSeats seat8 = new MovieRealTimeHallAllSeats();
        seat8.setLocNo("01");
        seat8.setHallNo("HM10001006806");
        seat8.setHallName("2号厅");
        seat8.setRowNo("8");
        seat8.setSeatImgRow("01");
        seat8.setColumnNo("ZL,1,2,3,4,5,6,7,8,9,10,11,12,13,ZL");
        seat8.setSeatType("3,1,1,1,2,1,1,1,1,1,1,1,2,3,1");
        seat8.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,");
        seat8.setSeatStatus("0,3,3,0,0,0,0,0,0,0,0,0,3,3,0");
        SeatModelListHall2.add(seat8);

        MovieRealTimeHallAllSeats seat9 = new MovieRealTimeHallAllSeats();
        seat9.setLocNo("01");
        seat9.setHallNo("HM10001006806");
        seat9.setHallName("2号厅");
        seat9.setRowNo("9");
        seat9.setSeatImgRow("01");
        seat9.setColumnNo("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        seat9.setSeatType("2,2,1,1,1,1,1,1,1,1,1,1,2,1,1");
        seat9.setSeatNo(",,,,06010505,06010506,06010507,06010508,06010509,06010510,06010511,06010512,06010513,,");
        seat9.setSeatStatus("3,3,0,0,0,0,0,0,0,0,0,0,0,3,3");
        SeatModelListHall2.add(seat9);
    }
}