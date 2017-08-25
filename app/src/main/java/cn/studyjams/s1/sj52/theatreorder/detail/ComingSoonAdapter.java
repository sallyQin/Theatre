package cn.studyjams.s1.sj52.theatreorder.detail;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

import cn.studyjams.s1.sj52.theatreorder.R;
import cn.studyjams.s1.sj52.theatreorder.data.ComingSoonContentProvider;


/**
 * Created by 1 on 2017/8/21.
 */

class  ComingSoonAdapter extends UniversalAdapter implements LoaderManager.LoaderCallbacks {
    public static ComingSoonFilmsActivity  mActivity;
    Cursor cursor;
    List<Integer> list_august = new ArrayList<>();
    List<Integer> list_sept = new ArrayList<>();
    int like_times;
    public ComingSoonAdapter(@LayoutRes int itemRes) {
        super(itemRes);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
         return new CursorLoader(mActivity, ComingSoonContentProvider.URI,null,"Months = ?",
                 new String[]{mActivity.selected_Month}, null);
    }

    @Override
    public void onLoadFinished(Loader loader,Object data) {
        if(cursor!= data){
            if(cursor != null){
                cursor.close();
            }
        }
        cursor = (Cursor) data;
        notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader loader) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = null;
    }


    @Override
    public void onBindViewHolder(final UniversalHolder holder, final int position) {
        SimpleDraweeView coming_details_pic  = (SimpleDraweeView) holder.itemView.findViewById(R.id.coming_details_pic);
        TextView coming_title_txt = (TextView) holder.itemView.findViewById(R.id.coming_title_txt);
        TextView coming_date_txt = (TextView) holder.itemView.findViewById(R.id.coming_time_txt);
        final FrameLayout likes_coming  = (FrameLayout) holder.itemView.findViewById(R.id.likes_coming);

        String sMonths = "";
        if (cursor.moveToPosition(position)) {
            String picName = cursor.getString(cursor.getColumnIndex("imageName"));
            String titleName = cursor.getString(cursor.getColumnIndex("title"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            like_times = cursor.getInt(cursor.getColumnIndex("likes"));
            sMonths = cursor.getString(cursor.getColumnIndex("Months"));
            coming_details_pic.setImageResource(holder.itemView.getResources().getIdentifier(picName,"drawable",holder.itemView.getContext().getPackageName()));//设置图片
            coming_title_txt.setText(titleName);//设置片名
            coming_date_txt.setText(date);//设置上映日期
        }

            if(like_times == 1){
                likes_coming.setSelected(true);
            } else{
                likes_coming.setSelected(false);
        }


        final String finalSMonths = sMonths;
        likes_coming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positions = holder.getAdapterPosition();
                ContentValues values =  new ContentValues();
                cursor.moveToPosition(positions);
                if(finalSMonths.equals("august")){
                    if(cursor.getInt(cursor.getColumnIndex("likes")) == 1){
                        values.put("likes", 0);
                        mActivity.getContentResolver().update(ComingSoonContentProvider.URI, values,"id = ?",new String[]{"" + cursor.getInt(cursor.getColumnIndex("id"))});
                    }else {
                        values.put("likes", 1);
                        mActivity.getContentResolver().update(ComingSoonContentProvider.URI, values,"id = ?",new String[]{"" + cursor.getInt(cursor.getColumnIndex("id"))});
                    }
                }else{
                    if(cursor.getInt(cursor.getColumnIndex("likes")) == 1){
                        values.put("likes", 0);
                        mActivity.getContentResolver().update(ComingSoonContentProvider.URI,values,"id = ?",new String[]{"" +cursor.getInt(cursor.getColumnIndex("id"))});
                    }else {
                        values.put("likes", 1);
                        mActivity.getContentResolver().update(ComingSoonContentProvider.URI,values,"id = ?",new String[]{"" +cursor.getInt(cursor.getColumnIndex("id"))});
                    }
                }

                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        if (cursor != null) {  /** normal case */
            return cursor.getCount();
        } else {                /** mCursor == null, rare case */
            return 0;
        }
    }
}
