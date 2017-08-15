package cn.studyjams.s1.sj52.theatreorder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.studyjams.s1.sj52.theatreorder.data.Theatre_info;

/**
 * Created by Apc on 2016/9/1.
 */
public class TicketOrderPokerAdapter extends RecyclerView.Adapter<TicketOrderPokerAdapter.Holder> { //“订座”Tab栏中海报部分的adapter

    public MainActivity mainActi;
    static int selected_poster = -1;


    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView_poster;
        ImageView imageView_poster_bigger;

        public Holder(View itemView) {
            super(itemView);
            imageView_poster = (ImageView) itemView.findViewById(R.id.image_poster);
            imageView_poster_bigger = (ImageView) itemView.findViewById(R.id.image_poster_bigger);

        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticketorder__poker_recyclerview,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final Theatre_info theatrePosters = Theatre_info.getTheatrePosters().get(position);

        final int res = holder.imageView_poster.getContext().getResources().getIdentifier(theatrePosters.poster,"drawable",holder.imageView_poster.getContext().getPackageName());

        holder.imageView_poster.setImageResource(res);

        /**
         * 设置点击poster监听器：海报会变大，并使得整个recyclerView的背景也随之改变。
         * **/

        if (position > 1) {
            holder.imageView_poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected_poster = position;
                    mainActi.fileName_text.setText(Theatre_info.fileNames[position]);
                    Bitmap oldBitmap1 = BitmapFactory.decodeResource(holder.imageView_poster.getContext().getResources(), res);
                    Drawable drawable1_b = new BitmapDrawable(zoomBitmap(oldBitmap1, 25, 2));
                    mainActi.recyclerView_ticketOrder_poker.setBackground(drawable1_b);   //点击改变poster背景
                    notifyDataSetChanged();

                }
            });
        } else {
            holder.imageView_poster.setOnClickListener(null);
        }

        if(selected_poster == position){              //点击时，海报会变大 & 白色边框
            holder.imageView_poster.setScaleType(ImageView.ScaleType.FIT_CENTER);
            holder.imageView_poster_bigger.setVisibility(View.VISIBLE);
            holder.imageView_poster_bigger.setSelected(true);

        }else{
            holder.imageView_poster.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.imageView_poster_bigger.setVisibility(View.INVISIBLE);
        }

    }




    @Override
    public int getItemCount() {
        return Theatre_info.getTheatrePosters().size();
    }


    /**  Bitmap缩放 **/
    public static Bitmap zoomBitmap(Bitmap oldBitmap, double width, double height) { //根据用户选中的poster图，进行zoom放大bitmap背景图
        int w = oldBitmap.getWidth();
        int h = oldBitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.setScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldBitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }
}
