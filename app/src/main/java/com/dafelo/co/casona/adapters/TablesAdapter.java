package com.dafelo.co.casona.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dafelo.co.casona.listeners.OnTableSelectedListener;
import com.dafelo.co.casona.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 6/07/16.
 */
public class TablesAdapter  extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {
    private String tables[];
    private String availability[];
    private OnTableSelectedListener onTableSelectedListener;

    public void setOnTableSelectedListener(OnTableSelectedListener onTableSelectedListener) {
        this.onTableSelectedListener = onTableSelectedListener;
    }
    @Override
    public TablesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View parentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_table_restaurant, parent, false);

        return new ViewHolder(parentView);

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TablesAdapter(String[] tables, String[] availability) {
        this.availability = availability;
        this.tables = tables;
    }


    @Override
    public void onBindViewHolder(TablesAdapter.ViewHolder holder, int position) {
        holder.mNumberTextView.setText(tables[position]);
        holder.mStateTextView.setText(availability[position]);
        holder.mImageView.setImageResource(R.drawable.dinner);
        holder.mContainer.setOnClickListener(view -> onTableSelectedListener.onTableItemClick(tables[position]));
    }

    @Override
    public int getItemCount() {
        return tables.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.list_item_icon) ImageView mImageView;
        @BindView(R.id.list_item_number_textview) TextView mNumberTextView;
        @BindView(R.id.list_item_state_textview) TextView mStateTextView;
        @BindView(R.id.table_item_list_container) LinearLayout mContainer;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
