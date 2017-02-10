package io.mindjet.jetgear.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.viewholder.BaseViewHolder;

/**
 * Created by Jet on 2/10/17.
 */

public abstract class BaseAdapter<T extends BaseViewHolder> extends RecyclerView.Adapter<T> {

    private LayoutInflater inflater;

    public BaseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent);
        return (T) new BaseViewHolder(view);
    }

}
