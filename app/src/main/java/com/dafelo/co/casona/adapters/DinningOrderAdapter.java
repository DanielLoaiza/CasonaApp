package com.dafelo.co.casona.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.dafelo.co.casona.BO.FoodPlate;
import com.dafelo.co.casona.BO.OrderItem;
import com.dafelo.co.casona.R;
import com.dafelo.co.casona.listeners.OnItemAddedListener;
import com.dafelo.co.casona.listeners.OnItemRemovedListener;
import com.dafelo.co.casona.listeners.OnNumberPickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 19/11/16.
 */

public class DinningOrderAdapter extends RecyclerView.Adapter<DinningOrderAdapter.ViewHolder> {

    private List<OrderItem> order;
    private OnNumberPickListener mNumberPickListener;
    private OnItemRemovedListener onItemRemovedListener;
    private Context mContext;

    public DinningOrderAdapter(Context context) {
        mContext = context;
    }

    public void setmNuberPickListener(OnNumberPickListener mNumberPickListener) {
        this.mNumberPickListener = mNumberPickListener;
    }

    public void setItems(List<OrderItem> order)
    {
        if (order == null)
        {
            return;
        }
        this.order = new ArrayList<>(order);
        notifyDataSetChanged();
    }

    public void addLast(OrderItem o) {
        order.add(o);
        notifyItemInserted(order.size() -1);
    }

    public void setOnItemrRemovedListener(OnItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View parentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dinning_order_item, parent, false);

        return new DinningOrderAdapter.ViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemName.setText(order.get(position).getPlate().getName());
        holder.itemQuantity.setValue(order.get(position).getQuantity());
        holder.itemQuantity.setOnValueChangedListener((picker, oldVal, newVal) -> {
            mNumberPickListener.onNumberChange(newVal, oldVal, order.get(position));
        });
        holder.itemTotal.setText(
                String.format(
                        mContext.getString(R.string.plate_price),
                        order.get(position).getTotal())
        );
        holder.deleteButton.setOnClickListener(view ->
                onItemRemovedListener.onItemRemove(order.get(position).getPlate()));
    }

    @Override
    public int getItemCount() {
        if(order != null) {
            return order.size();
        } else {
            return 0;
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.order_item_name) TextView itemName;
        @BindView(R.id.order_item_quantity) NumberPicker itemQuantity;
        @BindView(R.id.order_item_total) TextView itemTotal;
        @BindView(R.id.item_order_cancel) Button deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemQuantity.setMinValue(1);
            itemQuantity.setMaxValue(200);

        }
    }
}
