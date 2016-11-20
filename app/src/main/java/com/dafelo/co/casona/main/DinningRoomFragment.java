package com.dafelo.co.casona.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafelo.co.casona.R;
import com.dafelo.co.casona.adapters.TablesAdapter;
import com.dafelo.co.casona.listeners.OnTableSelectedListener;
import com.dafelo.co.casona.order_list.MenuListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by root on 18/11/16.
 */

public class DinningRoomFragment extends Fragment {

    @BindView(R.id.tables_recycler_view)
    RecyclerView mRecyclerView;
    private Unbinder unbinder;


    public DinningRoomFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DinningRoomFragment newInstance() {
        return new DinningRoomFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.tables_restaurant, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(),2));
        String[] tables = {"10", "20", "30", "40","50","60"};
        String[] availability = {"available", "available", "in use", "available","in use", "available"};
        // specify an adapter (see also next example)
        TablesAdapter mAdapter = new TablesAdapter(tables, availability);
        mAdapter.setOnTableSelectedListener(table -> {
            Intent intent = new Intent(getActivity(), MenuListActivity.class);
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
