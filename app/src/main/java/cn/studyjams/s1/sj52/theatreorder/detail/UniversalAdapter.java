package cn.studyjams.s1.sj52.theatreorder.detail;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.studyjams.s1.sj52.theatreorder.data.ComingSoonContentProvider;


/**
 * Created by 1 on 2016/12/13.
 */

public abstract class UniversalAdapter extends RecyclerView.Adapter<UniversalHolder>  {
    private int mItemRes;

    public UniversalAdapter(@LayoutRes int itemRes) {
        mItemRes = itemRes;
    }

    @Override
    public UniversalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemRes,parent,false);
        UniversalHolder universalHolder = new UniversalHolder(view);
        view.setTag(universalHolder);
       // view.setOnClickListener(this);
        return universalHolder;
    }

}
