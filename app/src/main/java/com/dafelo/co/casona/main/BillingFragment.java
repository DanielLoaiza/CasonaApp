package com.dafelo.co.casona.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafelo.co.casona.R;

/**
 * Created by root on 18/11/16.
 */

public class BillingFragment extends Fragment {


    public BillingFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BillingFragment newInstance() {
        return new BillingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.billing_restaurant, container, false);
    }
}