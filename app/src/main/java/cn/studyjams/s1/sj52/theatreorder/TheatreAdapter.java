package cn.studyjams.s1.sj52.theatreorder;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.studyjams.s1.sj52.theatreorder.data.Theatre_info;


/**
 * Created by Apc on 2016/8/29.
 */
public class TheatreAdapter extends RecyclerView.Adapter {
    public static MainActivity mainActivityThea;



    ImageView imageView_arrow;
    ImageView image_arrow1;
    ImageView image_arrow2;
    ImageView image_arrow3;
    ImageView image_arrow4;
    ImageView image_arrow5;

    View theatre_location_favorite;
    View theatre_location1;
    View theatre_location2;
    View theatre_location3;
    View theatre_location4;
    View theatre_location5;

    LinearLayout liangxiDis_1;
    LinearLayout liangxiDis_2;
    LinearLayout liangxiDis_3;
    LinearLayout liangxiDis_4;
    LinearLayout xishanDis_1;
    LinearLayout xishanDis_2;
    LinearLayout huishanDis_1;
    LinearLayout huishanDis_2;
    LinearLayout binghuDis_1;
    LinearLayout binghuDis_2;
    LinearLayout xinwuDis_1;
    LinearLayout xinwuDis_2;


    protected static boolean flagFavoriteArrow = true;
    protected static boolean flagArrow1 = true;
    protected static boolean flagArrow2 = true;
    ;
    protected static boolean flagArrow3 = true;
    protected static boolean flagArrow4 = true;
    protected static boolean flagArrow5 = true;


    public static String theatreName_selected;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theatreinfo_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        theatre_location_favorite = holder.itemView.findViewById(R.id.theatre_location_favorite);
        theatre_location1 = holder.itemView.findViewById(R.id.theatre_location1);
        theatre_location2 = holder.itemView.findViewById(R.id.theatre_location2);
        theatre_location3 = holder.itemView.findViewById(R.id.theatre_location3);
        theatre_location4 = holder.itemView.findViewById(R.id.theatre_location4);
        theatre_location5 = holder.itemView.findViewById(R.id.theatre_location5);
        imageView_arrow = (ImageView) holder.itemView.findViewById(R.id.arrow_theatre_recycler_favorite);
        image_arrow1 = (ImageView) holder.itemView.findViewById(R.id.arrow_theatre_recycler_1);
        image_arrow2 = (ImageView) holder.itemView.findViewById(R.id.arrow_theatre_recycler_2);
        image_arrow3 = (ImageView) holder.itemView.findViewById(R.id.arrow_theatre_recycler_3);
        image_arrow4 = (ImageView) holder.itemView.findViewById(R.id.arrow_theatre_recycler_4);
        image_arrow5 = (ImageView) holder.itemView.findViewById(R.id.arrow_theatre_recycler_5);


        liangxiDis_1 = (LinearLayout) holder.itemView.findViewById(R.id.liangxiDis_1);
        liangxiDis_2 = (LinearLayout) holder.itemView.findViewById(R.id.liangxiDis_2);
        liangxiDis_3 = (LinearLayout) holder.itemView.findViewById(R.id.liangxiDis_3);
        liangxiDis_4 = (LinearLayout) holder.itemView.findViewById(R.id.liangxiDis_4);
        xishanDis_1 = (LinearLayout) holder.itemView.findViewById(R.id.xishanDis_1);
        xishanDis_2 = (LinearLayout) holder.itemView.findViewById(R.id.xishanDis_2);
        huishanDis_1 = (LinearLayout) holder.itemView.findViewById(R.id.huishanDis_1);
        huishanDis_2 = (LinearLayout) holder.itemView.findViewById(R.id.huishanDis_2);
        binghuDis_1 = (LinearLayout) holder.itemView.findViewById(R.id.binghuDis_1);
        binghuDis_2 = (LinearLayout) holder.itemView.findViewById(R.id.binghuDis_2);
        xinwuDis_1 = (LinearLayout) holder.itemView.findViewById(R.id.xinwuDis_1);
        xinwuDis_2 = (LinearLayout) holder.itemView.findViewById(R.id.xinwuDis_2);

        /**
         * setting buttons' listener for all theatres and record the theatreName  which are selected separately
         * */
        theatre_location_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[0];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换

            }
        });

        liangxiDis_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[1];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

               mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

               mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });

        liangxiDis_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[2];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换



            }
        });

        liangxiDis_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[3];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换



            }
        });

        liangxiDis_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[4];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换



            }
        });

        xishanDis_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[5];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });

        xishanDis_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[6];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });

        huishanDis_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[7];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });

        huishanDis_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[8];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });
        binghuDis_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[9];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });

       binghuDis_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[10];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });

        xinwuDis_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[11];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });

        xinwuDis_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreName_selected = Theatre_info.theatreName[12];
                mainActivityThea.layoutUI_ticketOrder.setVisibility(View.VISIBLE);
                mainActivityThea.layoutUI_Home.setVisibility(View.INVISIBLE);
                mainActivityThea.layoutUI_TheatreInfo.setVisibility(View.INVISIBLE);
                mainActivityThea.ticketOrder_image.setImageResource(R.drawable.ticket_order);
                mainActivityThea.home_image.setImageResource(R.drawable.home_title);
                mainActivityThea.theatreInfo_image.setImageResource(R.drawable.title_theatre_info);

                mainActivityThea.fileName_text = (TextView) mainActivityThea.findViewById(R.id.filmName_selected);
                mainActivityThea.ticket_location_title = (TextView) mainActivityThea.findViewById(R.id.ticket_location_title);
                assert mainActivityThea.ticket_location_title != null;
                mainActivityThea.ticket_location_title.setText(TheatreAdapter.theatreName_selected);

                TicketOrderPokerAdapter ticketOrderPokerAdapter =new TicketOrderPokerAdapter();
                ticketOrderPokerAdapter.mainActi = mainActivityThea;
                mainActivityThea.recyclerView_ticketOrder_poker.setAdapter(ticketOrderPokerAdapter);//海报 切换

                mainActivityThea.recy_ticketDate.setAdapter(new TicketOrderDateAdapter() );//日期 切换

                mainActivityThea. recy_ticketActionCutting.setAdapter( mainActivityThea.ticketActionCuttingAdapter);//场次 切换


            }
        });



        /**
         * setting 5 arrow up or down buttons' listener separately
         * */
        imageView_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagFavoriteArrow) {
                    imageView_arrow.setBackgroundResource(android.R.drawable.arrow_up_float);
                    theatre_location_favorite.setVisibility(View.VISIBLE);
                    flagFavoriteArrow = false;
                } else {
                    imageView_arrow.setBackgroundResource(android.R.drawable.arrow_down_float);
                    theatre_location_favorite.setVisibility(View.GONE);
                    flagFavoriteArrow = true;
                }
            }
        });


        image_arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagArrow1) {
                    image_arrow1.setBackgroundResource(android.R.drawable.arrow_up_float);
                    theatre_location1.setVisibility(View.VISIBLE);
                    flagArrow1 = false;
                } else {
                    image_arrow1.setBackgroundResource(android.R.drawable.arrow_down_float);
                    theatre_location1.setVisibility(View.GONE);
                    flagArrow1 = true;
                }
            }
        });


        image_arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagArrow2) {
                    image_arrow2.setBackgroundResource(android.R.drawable.arrow_up_float);
                    theatre_location2.setVisibility(View.VISIBLE);
                    flagArrow2 = false;
                } else {
                    image_arrow2.setBackgroundResource(android.R.drawable.arrow_down_float);
                    theatre_location2.setVisibility(View.GONE);
                    flagArrow2 = true;
                }
            }
        });


        image_arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagArrow3) {
                    image_arrow3.setBackgroundResource(android.R.drawable.arrow_up_float);
                    theatre_location3.setVisibility(View.VISIBLE);
                    flagArrow3 = false;
                } else {
                    image_arrow3.setBackgroundResource(android.R.drawable.arrow_down_float);
                    theatre_location3.setVisibility(View.GONE);
                    flagArrow3 = true;
                }
            }
        });


        image_arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagArrow4) {
                    image_arrow4.setBackgroundResource(android.R.drawable.arrow_up_float);
                    theatre_location4.setVisibility(View.VISIBLE);
                    flagArrow4 = false;
                } else {
                    image_arrow4.setBackgroundResource(android.R.drawable.arrow_down_float);
                    theatre_location4.setVisibility(View.GONE);
                    flagArrow4 = true;
                }
            }
        });


        image_arrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagArrow5) {
                    image_arrow5.setBackgroundResource(android.R.drawable.arrow_up_float);
                    theatre_location5.setVisibility(View.VISIBLE);
                    flagArrow5 = false;
                } else {
                    image_arrow5.setBackgroundResource(android.R.drawable.arrow_down_float);
                    theatre_location5.setVisibility(View.GONE);
                    flagArrow5 = true;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }


}

