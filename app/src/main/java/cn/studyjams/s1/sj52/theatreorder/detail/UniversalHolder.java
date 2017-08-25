package cn.studyjams.s1.sj52.theatreorder.detail;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by 1 on 2016/12/13.
 */

public class UniversalHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews = new SparseArray<>();

        @SuppressWarnings("WeakerAccess")
        public UniversalHolder(View itemView) {

            super(itemView);
        }

        public <T extends View>T get(@IdRes int id) {  //??????????????????????????????
            View view = mViews.get(id);
            if (null == view) {
                view = itemView.findViewById(id);
                mViews.put(id, view);
            }
            //noinspection unchecked
            return (T) view;
        }

    }

