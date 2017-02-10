package io.mindjet.jetgear.adapter;

import android.content.Context;

import io.mindjet.jetgear.viewholder.BaseViewHolder;

/**
 * Created by Jet on 2/10/17.
 */

public class ListAdapter<B, T extends BaseViewHolder> extends BaseAdapter<T> {

    public ListAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
