package cn.studyjams.s1.sj52.theatreorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Apc on 2016/9/14.
 */
public class MovieSeatViewUtil extends View {

    private Context mContext;
    private ZoomChangeListener OnZoomChangeListener = null;
    private int BOX_MAX_SIZE = 65;// 手动设置盒子大小
    private int SEAT_MAX_SIZE = 60;// 手动设置座位大小
    private int box_size = BOX_MAX_SIZE;
    private int seat_size = SEAT_MAX_SIZE;
    private int hang = 0;
    private int lie = 0;
    private double zoom;

    private int zoom_level = 0;
    private double zoom_temp1;
    private Bitmap SeatOk, SeatSold, SeatSelect, SeatRepair,SeatCouple;
    private boolean isFirstLoad = true;

    private int oldClick;
    public List<Integer> mySeatList;// 保存选中座位
    private List<Integer> unavailableSeatList;// 保存未选中座位

    private HashMap<Integer, Integer> seatColumn;// 用来存不规则影院时座位列的值
    private boolean isBigZoom = false;// 表示是否缩放

    private ArrayList<MovieRealTimeHallAllSeats> SeatModelList;
    private ArrayList<String> hangList;
    private ArrayList<String> lieList;
    private ArrayList<String> seatStateList;
    private ArrayList<String> seatTypeList;
    ArrayList<Integer> checkSeatNO;  //查看位置状态
    String checkSeatStatusTemp;  //查看位置状态
    private int width_1,height_1;
    public static ArrayList checkSeatStatus = new ArrayList();
    Map<Integer,String> sortSelectedSeat = new HashMap<>();


    public MovieSeatViewUtil(Context context,
                             ArrayList<MovieRealTimeHallAllSeats> SeatModelList) {
        super(context);
        mContext = context;
        this.SeatModelList = SeatModelList;
        initDate();
        SeatOk = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.movie_seat_ok);
        SeatSold = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.movie_seat_sold);
        SeatSelect = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.movie_seat_select);
        SeatRepair = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.movie_seat_repair);
        SeatCouple =  BitmapFactory.decodeResource(context.getResources(),
                R.drawable.movie_seat_couplesweet);

    }

    void initDate() {

        hangList = new ArrayList<>();
        lieList = new ArrayList<>();
        seatStateList = new ArrayList<>();
        seatTypeList = new ArrayList<>();
        checkSeatStatus.clear(); //初始化时，清除之前的选座数据

        for (int i = 0; i < SeatModelList.size(); i++) {
            String lieStr = SeatModelList.get(i).getColumnNo();
            String[] lieArr = lieStr.split(",");
            for (int j = 0; j < lieArr.length; j++) {
                hangList.add(i + "");
                lieList.add(lieArr[j]);
            }
            String seatStateStr = SeatModelList.get(i).getSeatStatus();
            String[] seatStateArr = seatStateStr.split(",");
            for (int j = 0; j < seatStateArr.length; j++) {
                seatStateList.add(seatStateArr[j]);
            }
            String seatTypeStr = SeatModelList.get(i).getSeatType();
            String[] seatTypeArr = seatTypeStr.split(",");
            for (int j = 0; j < seatTypeArr.length; j++) {
                seatTypeList.add(seatTypeArr[j]);
            }

        }

        lie = lieList.size() / SeatModelList.size();
        hang = SeatModelList.size();
        mySeatList = new ArrayList<>();
        unavailableSeatList = new ArrayList<>();
        seatColumn = new HashMap<>();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(box_size * lie, box_size * hang);
    }

    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isFirstLoad) {
            zoom_temp1 = ((ChooseSeatActivity.width * 0.9) / lie / box_size);
            double zoom_temp2 = ((ChooseSeatActivity.height / 2.5) / hang / box_size);
            if (zoom_temp1 > zoom_temp2) {
                zoom_temp1 = zoom_temp2;
            }
            box_size = (int) (box_size * zoom_temp1);
            seat_size = (int) (seat_size * zoom_temp1);
            isFirstLoad = false;
            zoom = 1.0;
            zoom_level = 1;
            OnZoomChangeListener.ZoomChange(box_size, zoom_level, isBigZoom);
        }
        box_size = (int) (box_size * zoom);
        seat_size = (int) (seat_size * zoom);

        // 可购买座位
        Bitmap seat_ok = Bitmap.createScaledBitmap(SeatOk, seat_size, seat_size - 3,
                true);
        // 红色 已售座位
        Bitmap seat_sold = Bitmap.createScaledBitmap(SeatSold, seat_size,
                seat_size - 3, true);
        // 绿色 选中座位
        Bitmap seat_select = Bitmap.createScaledBitmap(SeatSelect, seat_size,
                seat_size - 3, true);
        // 灰色 正在维修
        Bitmap seat_repair = Bitmap.createScaledBitmap(SeatRepair, seat_size,
                seat_size - 3, true);
        // 粉红心 情侣座
        Bitmap seat_couple = Bitmap.createScaledBitmap(SeatCouple, seat_size,
                seat_size - 3, true);

        Paint paint = new Paint();
        paint.setTextSize(20);// 设置字体大小
        paint.setColor(Color.parseColor("#7d8fab"));
        paint.setTypeface(Typeface.DEFAULT_BOLD);// 设置字体类型
        paint.setTextAlign(Paint.Align.CENTER);  //设置文字居中对齐
        // 画座位
        for (int i = 0; i < hang; i++) {
            int count = 0;
            for (int j = 0; j < lie; j++) {
                // 第一步 画出所有可选座位
                if ("ZL".equals(lieList.get(i * lie + j))) {
                    unavailableSeatList.add(i * lie + j);
                } else {
                    canvas.drawBitmap(seat_ok, j * (box_size), i * (box_size),
                            null);
                    // column 用来表示不规则影院时座位列的值
                    // isBigZoom是否缩放，缩放显示数字
                    int column = 1 + count++;
                    if (isBigZoom) {
                        canvas.drawText(column + "", j * (box_size) + box_size
                                        / 2 - 2, i * (box_size) + box_size / 2 - 2,
                                paint);
                    }
                    seatColumn.put(i * lie + j, column);
                }

                // 第二步 画出情侣座、已售出或者处于维修状态下的座位

                // "1"已售"2"维修 "3"情侣座
                if ("1".equals(seatStateList.get(i * lie + j))) {
                    canvas.drawBitmap(seat_sold, j * (box_size), i
                            * (box_size), null);
                    unavailableSeatList.add(i * lie + j);
                } else if ("2".equals(seatStateList.get(i * lie + j))) {
                    canvas.drawBitmap(seat_repair, j * (box_size), i
                            * (box_size), null);
                    unavailableSeatList.add(i * lie + j);
                } else if ("3".equals(seatStateList.get(i * lie + j))) {
                    canvas.drawBitmap(seat_couple, j * (box_size), i
                            * (box_size), null);
                    unavailableSeatList.add(i * lie + j);
                }

            }
        }
        // 我的座位 变成绿色
        for (int i = 0; i < mySeatList.size(); i++) {
            canvas.drawBitmap(seat_select,
                    (mySeatList.get(i) % lie) * box_size,
                    (mySeatList.get(i) / lie) * box_size, null);
        }
        this.setLayoutParams(new LinearLayout.LayoutParams(box_size * lie,
                box_size * hang));
        zoom = 1.0;
    }

    // 放大缩小view
    public void SetZoom(int zoomType) {
        if (zoomType == 0) {
            isBigZoom = true;
            box_size = BOX_MAX_SIZE;
            seat_size = SEAT_MAX_SIZE;
            zoom = 1.0;
            zoom_level = 5;
            OnZoomChangeListener.ZoomChange(box_size, zoom_level, isBigZoom);
            invalidate();
        } else if (zoomType == 1) {
            isBigZoom = false;
            box_size = (int) (box_size * zoom_temp1);
            seat_size = (int) (seat_size * zoom_temp1);
            zoom = 1.0;
            zoom_level = 1;
            OnZoomChangeListener.ZoomChange(box_size, zoom_level, isBigZoom);
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                oldClick = getClickPoint(event);
                if (!isBigZoom) {
                    isBigZoom = true;
                    box_size = BOX_MAX_SIZE;
                    seat_size = SEAT_MAX_SIZE;
                    zoom = 1.0;
                    zoom_level = 5;
                    OnZoomChangeListener
                            .ZoomChange(box_size, zoom_level, isBigZoom);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                int newClick = getClickPoint(event);
                // 点击时获取该行的座位类型数组
                String clickSeatColumn = SeatModelList.get(newClick / lie)
                        .getSeatType();
                String[] clickSeatColumnArr = clickSeatColumn.split(",");
                int seatCount = 0;
                // 计算点击时该行座位数
                for (int i = 0; i < clickSeatColumnArr.length; i++) {
                    if (!"3".equals(clickSeatColumnArr[i])) {
                        seatCount++;
                    }
                }
                if (isBigZoom) {
                    if (newClick == oldClick) {
                        if (mySeatList.contains(newClick)) {
                            if (leftEdgeDelete(newClick)) {

                                mySeatList.remove(mySeatList.indexOf(newClick));
                                mySeatList.remove(mySeatList.indexOf(newClick + 1));

                                checkSeatStatus.remove(sortSelectedSeat.get(newClick));        //删除ArrayList（用于TextView选座排序。）
                                checkSeatStatus.remove (sortSelectedSeat.get(newClick + 1));   //删除ArrayList（用于TextView选座排序。）
                                sortSelectedSeat.remove(newClick);
                                sortSelectedSeat.remove(newClick + 1);

                            } else if (leftGangwayDelete(newClick,
                                    clickSeatColumnArr)) {
                                mySeatList.remove(mySeatList.indexOf(newClick));
                                mySeatList.remove(mySeatList.indexOf(newClick + 1));

                                checkSeatStatus.remove(sortSelectedSeat.get(newClick));         //删除ArrayList（用于TextView选座排序。）
                                checkSeatStatus.remove (sortSelectedSeat.get(newClick +1));     //删除ArrayList（用于TextView选座排序。）
                                sortSelectedSeat.remove(newClick);
                                sortSelectedSeat.remove(newClick + 1);

                            } else if (rightEdgeDelete(newClick, seatCount)) {
                                mySeatList.remove(mySeatList.indexOf(newClick));
                                mySeatList.remove(mySeatList.indexOf(newClick - 1));

                                checkSeatStatus.remove(sortSelectedSeat.get(newClick));        //删除ArrayList（用于TextView选座排序。）
                                checkSeatStatus.remove (sortSelectedSeat.get(newClick -1));    //删除ArrayList（用于TextView选座排序。）
                                sortSelectedSeat.remove(newClick);
                                sortSelectedSeat.remove(newClick -1);

                                // 判断每行左边是否是走道
                            } else if (rightGangwayDelete(newClick, seatCount,
                                    clickSeatColumnArr)) {
                                mySeatList.remove(mySeatList.indexOf(newClick));
                                mySeatList.remove(mySeatList.indexOf(newClick - 1));


                                checkSeatStatus.remove(sortSelectedSeat.get(newClick));      //删除ArrayList（用于TextView选座排序。）
                                checkSeatStatus.remove (sortSelectedSeat.get(newClick -1));  //删除ArrayList（用于TextView选座排序。）
                                sortSelectedSeat.remove(newClick);
                                sortSelectedSeat.remove(newClick - 1);

                                // 去除连续座位 右边
                            } else {
                                // 去除其他座位
                                mySeatList.remove(mySeatList.indexOf(newClick));

                                checkSeatStatus.remove(sortSelectedSeat.get(newClick));      //删除ArrayList（用于TextView选座排序。）
                                sortSelectedSeat.remove(newClick);
                            }

                            updateSelectedTextViews();
                            invalidate();
                        } else if (!unavailableSeatList.contains(newClick)) {

                            if (leftEdge(newClick)) {
                                showToast();
                            } else if (leftGangway(newClick, clickSeatColumnArr)) {
                                showToast();
                            } else if (rightEdge(newClick, seatCount)) {
                                showToast();
                            } else if (rightGangway(newClick, seatCount,
                                    clickSeatColumnArr)) {
                                showToast();
                            } else if (mySeatList.size() < 4) {
                                mySeatList.add(newClick);

                                sortSelectedSeat.put(newClick,((newClick / lie + 1) + "行"
                                        + seatColumn.get(newClick) + "列"));
                                checkSeatStatus.add((newClick / lie + 1) + "行"             // 添加ArrayList（用于TextView选座排序。）
                                        + seatColumn.get(newClick) + "列");
                                updateSelectedTextViews();

                                invalidate();
                                Toast.makeText(
                                        mContext,
                                        "你选择了：" + (newClick / lie + 1) + "行"
                                                + seatColumn.get(newClick) + "列",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(mContext, "对不起，每位顾客最多只能选取4个座位！",
                                       Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
        }

        return true;

    }


    /** 选座排序并显示在textView中。**/
    private void updateSelectedTextViews() {
        ChooseSeatActivity chooseSeatActi = (ChooseSeatActivity) getContext();
        Collections.sort(checkSeatStatus);
        if(checkSeatStatus.size() == 1){
            chooseSeatActi.booking_txt1.setText((CharSequence) checkSeatStatus.get(0));
            chooseSeatActi.booking_txt2.setText("");
            chooseSeatActi.booking_txt3.setText("");
            chooseSeatActi.booking_txt4.setText("");
        }
        else if(checkSeatStatus.size() == 2){
            chooseSeatActi.booking_txt1.setText((CharSequence) checkSeatStatus.get(0));
            chooseSeatActi.booking_txt2.setText((CharSequence) checkSeatStatus.get(1));
            chooseSeatActi.booking_txt3.setText("");
            chooseSeatActi.booking_txt4.setText("");

        }
        else if(checkSeatStatus.size() == 3){
            chooseSeatActi.booking_txt1.setText((CharSequence) checkSeatStatus.get(0));
            chooseSeatActi.booking_txt2.setText((CharSequence) checkSeatStatus.get(1));
            chooseSeatActi.booking_txt3.setText((CharSequence) checkSeatStatus.get(2));
            chooseSeatActi.booking_txt4.setText("");

        }
        else if(checkSeatStatus.size() == 4){
            chooseSeatActi.booking_txt1.setText((CharSequence) checkSeatStatus.get(0));
            chooseSeatActi.booking_txt2.setText((CharSequence) checkSeatStatus.get(1));
            chooseSeatActi.booking_txt3.setText((CharSequence) checkSeatStatus.get(2));
            chooseSeatActi.booking_txt4.setText((CharSequence) checkSeatStatus.get(3));
        }
        else {
            chooseSeatActi.booking_txt1.setText("");
            chooseSeatActi.booking_txt2.setText("");
            chooseSeatActi.booking_txt3.setText("");
            chooseSeatActi.booking_txt4.setText("");
        }
    }


    /**
     * 连续座位判断 左边 1.判断每行左边是否是边缘 2.判断每行左边是否是走道且不在边缘
     */
    // 增加座位
    boolean leftEdge(int newClick) {
        if (newClick % lie < lie / 2) {
            return seatColumn.get(newClick) == 2// 在左边边缘
                    && !mySeatList.contains(newClick - 1)// 最左边已选
                    && "2".equals(seatTypeList.get(newClick));// 本身是连坐
        }
        return false;
    }

    boolean leftGangway(int newClick, String[] clickSeatColumnArr) {
        if (newClick % lie < lie / 2) {
            return seatColumn.get(newClick) != 1// 不在左边边缘第一位
                    && seatColumn.get(newClick) != 2// 不在左边边缘第二位
                    && "2".equals(clickSeatColumnArr[newClick % lie - 1])// 左边一位是连坐
                    && "3".equals(clickSeatColumnArr[newClick % lie - 2])// 左边俩位是过道
                    && !mySeatList.contains(newClick - 1)// 最左边已选
                    && "2".equals(seatTypeList.get(newClick));// 本身是连坐
        }
        return false;
    }

    /**
     * 连续座位判断 右边 1.判断每行右边是否是边缘 2.判断每行右边是否是走道且不再边缘
     */
    boolean rightEdge(int newClick, int seatCount) {
        if (newClick % lie > lie / 2) {
            return (seatColumn.get(newClick) == seatCount - 1)// 在右边第二位置
                    && !mySeatList.contains(newClick + 1)// 最右边已选
                    && "2".equals(seatTypeList.get(newClick));// 本身是连坐
        }
        return false;
    }

    boolean rightGangway(int newClick, int seatCount,
                         String[] clickSeatColumnArr) {
        if (newClick % lie > lie / 2) {
            return (seatColumn.get(newClick) != seatCount)// 不在右边第一位置
                    && (seatColumn.get(newClick) != seatCount - 1)// 不在右边第二位置
                    && "2".equals(clickSeatColumnArr[newClick % lie + 1])// 右边一位是连坐
                    && "3".equals(clickSeatColumnArr[newClick % lie + 2])// 右边俩位是过道
                    && !mySeatList.contains(newClick + 1)// 最右边已选
                    && "2".equals(seatTypeList.get(newClick));// 本身是连坐
        }
        return false;
    }

    /**
     * 删除座位 1左边 2右边
     */
    // 删除座位 左边
    boolean leftEdgeDelete(int newClick) {
        if (newClick % lie < lie / 2) {
            return seatColumn.get(newClick) == 1// 点击第一个
                    && mySeatList.contains(newClick + 1);// 如果右边连坐也已选
        }
        return false;
    }

    boolean leftGangwayDelete(int newClick, String clickSeatColumnArr[]) {
        if (newClick % lie < lie / 2) {
            return seatColumn.get(newClick) != 1// 不在左边边缘第一个
                    && ("2".equals(clickSeatColumnArr[newClick % lie])// 本身是连坐
                    && "3".equals(clickSeatColumnArr[newClick % lie - 1]) // 左边一个座位是过道
                    && mySeatList.contains(newClick + 1));// 右边第二个座位已选
        }
        return false;
    }

    // 删除座位 右边
    boolean rightEdgeDelete(int newClick, int seatCount) {
        if (newClick % lie > lie / 2) {
            return seatColumn.get(newClick) == seatCount// 右边第一个
                    && mySeatList.contains(newClick - 1);// // 左边连坐也已选
        }
        return false;
    }

    boolean rightGangwayDelete(int newClick, int seatCount,
                               String clickSeatColumnArr[]) {
        if (newClick % lie > lie / 2) {
            return (seatColumn.get(newClick) != seatCount)// 不在右边第一位置
                    && ("2".equals(clickSeatColumnArr[newClick % lie])// 本身是连坐
                    && "3".equals(clickSeatColumnArr[newClick % lie + 1]) // 右边是过道
                    && mySeatList.contains(newClick - 1));// 左边连坐也已选
        }
        return false;
    }

    void showToast() {
        Toast.makeText(mContext, "请选择连续的座位，不可留下单独空座！", Toast.LENGTH_SHORT)
                .show();
    }

    private int getClickPoint(MotionEvent event) {
        float currentXPosition = event.getX();
        float currentYPosition = event.getY();
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                if ((j * box_size) < currentXPosition
                        && currentXPosition < j * box_size + box_size
                        && (i * box_size) < currentYPosition
                        && currentYPosition < i * box_size + box_size) {
                    return i * lie + j;
                }
            }
        }
        return 0;
    }

    public interface ZoomChangeListener {
        void ZoomChange(int box_size, int zoom_level, boolean isBigZoom);
    }

    public void setZoomChangeListener(ZoomChangeListener listener) {
        OnZoomChangeListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        width_1 = w;
        height_1 = h;
    }
}
