package cn.studyjams.s1.sj52.theatreorder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.studyjams.s1.sj52.theatreorder.data.HaiAnTheatreInfo;

import static cn.studyjams.s1.sj52.theatreorder.R.id.ticket_location_title;

/**
 * Created by Apc on 2016/9/2.
 */
public class TicketActionCuttingAdapter extends RecyclerView.Adapter<TicketActionCuttingAdapter.Holder> {

    MainActivity mainActivity;

    public class Holder extends RecyclerView.ViewHolder{

        TextView startTime;
        TextView endTime;
        TextView edition;
        TextView hall;
        TextView price;

        public Holder(View itemView) {
            super(itemView);
            startTime = (TextView) itemView.findViewById(R.id.startTime);
            endTime = (TextView) itemView.findViewById(R.id.endTime);
            edition = (TextView) itemView.findViewById(R.id.edition);
            hall = (TextView) itemView.findViewById(R.id.projectionHall);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticketorder_actioncutting_recyclerview,parent,false);
        Holder viewHolder = new Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final HaiAnTheatreInfo data = HaiAnTheatreInfo.getHaiAnDieYingChongChong5Info().get(position);


        holder.startTime.setText(data.startTime);
        holder.endTime.setText(data.endTime + "散场");
        holder.edition.setText(data.edition);
        holder.hall.setText(data.projectionHall);
        holder.price.setText(""+data.price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //设置值传递

                Intent intent = new Intent(mainActivity,ChooseSeatActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("filmTitle",mainActivity.fileName_text.getText());
                intent.putExtra("ticket_location_title",mainActivity.ticket_location_title.getText());

                if(!TextUtils.isEmpty(mainActivity.ticket_location_title.getText().toString())){ //若影院名为选，则不能跳转到订座页面
                    mainActivity.startActivity(intent);
                }else{ //否则，提示。(自定义toast)
                    View view = LayoutInflater.from(mainActivity).inflate(R.layout.my_toast, (ViewGroup) mainActivity.findViewById(R.id.main_layout),false);
                    Toast toast = Toast.makeText(mainActivity,"提示：请先选择影院！",Toast.LENGTH_SHORT);
                    toast.setView(view);
                    toast.show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return HaiAnTheatreInfo.getHaiAnDieYingChongChong5Info().size();
    }
}
