package cn.studyjams.s1.sj52.theatreorder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.studyjams.s1.sj52.theatreorder.data.Theatre_info;

/**
 * Created by Apc on 2016/9/2.
 */
public class TicketOrderDateAdapter extends RecyclerView.Adapter {
    TextView date_text;
    View underline;
    protected static String date_done ="今日";

    int selected;

   public class ViewHolder extends RecyclerView.ViewHolder{

       public ViewHolder(View itemView) {
           
           super(itemView);
       }
   }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticketorder_date_recyclerview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        date_text = (TextView) holder.itemView.findViewById(R.id.date_text);
        underline = holder.itemView.findViewById(R.id.underline);

        date_text.setText(""+ Theatre_info.Date[position]);

     //  date_text.setSelected(selected == position);
        
        if (selected == position) {
            date_text.setSelected(true);
        } else {
            date_text.setSelected(false);
        }


       date_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                   date_done ="今日";
                }
                else {
                   date_done ="明日";
                }

                selected = position;
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return Theatre_info.Date.length;
    }
}
