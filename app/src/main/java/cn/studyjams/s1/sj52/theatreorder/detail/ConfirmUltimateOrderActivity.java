package cn.studyjams.s1.sj52.theatreorder.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Collections;
import cn.studyjams.s1.sj52.theatreorder.MovieSeatViewUtil;
import cn.studyjams.s1.sj52.theatreorder.R;
import cn.studyjams.s1.sj52.theatreorder.data.Theatre_info;
import static cn.studyjams.s1.sj52.theatreorder.data.Theatre_info.*;


/**
 * Created by 1 on 2016/12/1.
 */

public class ConfirmUltimateOrderActivity extends AppCompatActivity {  //最终结账界面
    private Spinner spinner;
    TextView oldPrice;
    TextView seat_txt;
    TextView fileName;
    TextView version;
    TextView cinemaName;
    TextView date;
    TextView startTime;
    TextView projectionHallNum;
    TextView price;
    SimpleDraweeView frescoPoster;
    TextView seatCount;
    TextView seatSum;
    String seatNum;
    CheckBox checkbox_coupleSuit;
    TextView totalSum;
    Button finally_confirm;
    int seatCounts = 0;//初始票数
    int pricePer;//单价格
    int total;//消费总额
    int total_orderSum;//套餐花费
    int total_ticketSum;//票价小计额

    private int mLastSelectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ultimate_order);
        String projectionHallNums = "3号厅";
        spinner = (Spinner) findViewById(R.id.spinner);//选择框
        oldPrice = (TextView) findViewById(R.id.oldPrice);
        seat_txt = (TextView) findViewById(R.id.seat_id);//座位框
        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //原价文字中间 加横线
        frescoPoster = (SimpleDraweeView) findViewById(R.id.frescoPoster);//海报框
        fileName = (TextView) findViewById(R.id.filmName_id); //片名框
        version = (TextView) findViewById(R.id.version_id);//版本框
        cinemaName = (TextView) findViewById(R.id.cinema_id);//影院名
        date = (TextView) findViewById(R.id.date_id); //日期框
        startTime = (TextView) findViewById(R.id.startTime_id);//场次框
        projectionHallNum = (TextView) findViewById(R.id.projectionHall_id);//厅号框
        price = (TextView) findViewById(R.id.price_id);//票价框
        seatCount = (TextView) findViewById(R.id.seatCount_id);//票数框
        seatSum = (TextView) findViewById(R.id.seatSum_id);//票价小计框
        totalSum = (TextView) findViewById(R.id.totalSum_id);//总额
        checkbox_coupleSuit = (CheckBox) findViewById(R.id.checkbox_coupleSuit);// 套餐复选框
        finally_confirm = (Button) findViewById(R.id.finally_confirm); //确定下单

        Intent intent = getIntent();
        if (intent != null) {
            String fileTitle = intent.getStringExtra("filmTitle");//获取用户已选的电影名
            fileName.setText(fileTitle);
            String versions = intent.getStringExtra("version");//获取用户已选的版本
            version.setText(versions);
            if (!TextUtils.isEmpty(fileTitle)) {//若有，则获取用户已选电影 海报
                Resources resources = getResources();
                for (int i = 0; i < getTheatrePosters().size(); ) {
                    Theatre_info theatre_info = getTheatrePosters().get(i);
                    if (theatre_info.fileName.equals(fileTitle)) {
                        String posterRes = theatre_info.poster;//海报uri
                        frescoPoster.setImageURI(Uri.parse("res:///" + resources.getIdentifier(posterRes, "drawable", getPackageName())));
                        break;
                    } else {
                        i++;
                    }
                }
            }

            String cinemaNames = intent.getStringExtra("cinemaName");//获取用户已选的影院名
            cinemaName.setText(cinemaNames);
            String dates = intent.getStringExtra("date");//获取用户已选的日期
            date.setText(dates);
            String startTimes = intent.getStringExtra("startTime");//获取用户已选的日期
            startTime.setText(startTimes);
            projectionHallNums = intent.getStringExtra("projectionHallNum");
            projectionHallNum.setText(projectionHallNums);//获取用户已选影厅号
        }

        if (!TextUtils.isEmpty(projectionHallNums)) { //设定单价
            if (projectionHallNums.equals("2号厅")) {
                pricePer = 35;
            } else {
                pricePer = 38;
            }
        }
        price.setText(pricePer + "元");


        /**设置用户已选座位号**/
        seatNum = "";
        String tempSeatNum;
        if (MovieSeatViewUtil.checkSeatStatus.size() > 0) {
            Collections.sort(MovieSeatViewUtil.checkSeatStatus);
            if (MovieSeatViewUtil.checkSeatStatus.size() == 1) {
                seatNum = (String) MovieSeatViewUtil.checkSeatStatus.get(0);
                seatCounts = 1;
            } else {
                seatNum = (String) MovieSeatViewUtil.checkSeatStatus.get(0);
                seatCounts = 1;
                for (int i = 1; i < MovieSeatViewUtil.checkSeatStatus.size(); i++) {
                    tempSeatNum = (String) MovieSeatViewUtil.checkSeatStatus.get(i);
                    seatNum += "," + tempSeatNum;
                    seatCounts = seatCounts + 1;
                }
            }
            seat_txt.setText(seatNum);
            seatNum = "";
        }

        seatCount.setText(seatCounts + "张");//获取票数
        total_ticketSum = seatCounts * pricePer;
        seatSum.setText(total_ticketSum + "");//设定票价小结
        total = total_ticketSum + total_orderSum;
        totalSum.setText(total + "");   //获取用消费总额户

        /**
         * 设置 checkBox复选框 事件监听器
         * **/
        checkbox_coupleSuit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mLastSelectedIndex <= 0) {
                        spinner.setSelection(1);
                    }
                } else if (!isChecked) {
                    spinner.setSelection(0);
                }
            }
        });


        /**
         * 设置 下拉选择框 事件监听器
         * **/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLastSelectedIndex = position;
                if (getResources().getStringArray(R.array.spinner)[position].equals("0 份")) {
                    notifyTotalSumChanged(0); //通知“消费总额”数据更新
                    checkbox_coupleSuit.setChecked(false);//使checkbox 不处于“√”状态

                } else if (getResources().getStringArray(R.array.spinner)[position].equals("1 份")) {
                    notifyTotalSumChanged(1);
                    checkbox_coupleSuit.setChecked(true);//使checkbox 处于“√”状态

                } else if (getResources().getStringArray(R.array.spinner)[position].equals("2 份")) {
                    notifyTotalSumChanged(2);
                    checkbox_coupleSuit.setChecked(true);

                } else if (getResources().getStringArray(R.array.spinner)[position].equals("3 份")) {
                    notifyTotalSumChanged(3);
                    checkbox_coupleSuit.setChecked(true);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        /**
         * 设定 确定下单按钮的监听事件
         **/
        finally_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //弹出确认对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmUltimateOrderActivity.this);
                builder.setIcon(R.mipmap.ic_launcher);//添加ICON
                builder.setTitle("确定");                //添加标题
                builder.setMessage("你确定要购票吗？");     //添加MSG

                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int whichButton) {
                        // 点击响应
                        Toast.makeText(ConfirmUltimateOrderActivity.this, "已下单",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int whichButton) {
                        // 点击响应
                        Toast.makeText(ConfirmUltimateOrderActivity.this,"已取消", Toast.LENGTH_LONG).show();
                    }
                });
                builder.create();
                builder.show();

            }
        });

    }

    /**
     * 通知消费总额内容改变
     **/
    protected void notifyTotalSumChanged(int total_order) {
        total = total_order * 30 + total_ticketSum;
        totalSum.setText(total + "");
    }
}