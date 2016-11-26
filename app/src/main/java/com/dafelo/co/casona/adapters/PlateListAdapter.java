package com.dafelo.co.casona.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dafelo.co.casona.R;
import com.dafelo.co.casona.listeners.OnItemAddedListener;
import com.dafelo.co.casona.order_detail.data.entity.Food;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlateListAdapter extends RecyclerView.Adapter<PlateListAdapter.ViewHolder> {

    private final Context mContext;
    private List<Food> mData;
    private OnItemAddedListener onItemAddedListener;

    public void add(Food s,int position) {
        position = position == -1 ? getItemCount()  : position;
        mData.add(position,s);
        notifyItemInserted(position);
    }

    public void itemChanged(Food s) {
        int position = mData.indexOf(s);
        Log.e("indexOf", String.valueOf(position));
        if(position > -1) {
            notifyItemChanged(position);
        }
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public PlateListAdapter(Context context, List<Food> data) {
        mContext = context;
        this.mData = data;
    }

    public void setOnItemAddedListener(OnItemAddedListener onItemAddedListener) {
        this.onItemAddedListener = onItemAddedListener;
    }

    public PlateListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.menu_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlateListAdapter.ViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        String price = String.format(mContext.getString(R.string.plate_price), mData.get(position).getPrice());
        holder.price.setText(price);
        holder.addButton.setOnClickListener(view -> {
            onItemAddedListener.onItemAdd(mData.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.plate_name) TextView name;
        @BindView(R.id.plate_price) TextView price;
        @BindView(R.id.add_button) ImageButton addButton;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}


