package com.dafelo.co.casona.order_detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafelo.co.casona.BO.FoodPlate;
import com.dafelo.co.casona.BO.OrderItem;
import com.dafelo.co.casona.R;
import com.dafelo.co.casona.adapters.DinningOrderAdapter;
import com.dafelo.co.casona.adapters.PlateListAdapter;
import com.dafelo.co.casona.listeners.OnNumberPickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * A fragment representing a single Menu detail screen.
 * on handsets.
 */
public class MenuDetailFragment extends Fragment {

    @BindView(R.id.dinning_order)
    RecyclerView mRecycleView;
    private DinningOrderAdapter mAdapter;
    @BindView(R.id.account_total)
    TextView billTotal;
    private MenuDetailViewModel menuDetailViewModel;
    /** Hold active loading observable subscriptions, so that they can be unsubscribed from when the activity is destroyed */
    private CompositeSubscription subscriptions;
    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MenuDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_detail, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        subscriptions = new CompositeSubscription();
        menuDetailViewModel = new MenuDetailViewModel();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycleView.setHasFixedSize(true);
        // use a linear layout manager
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // specify an adapter (see also next example)
         mAdapter = new DinningOrderAdapter(getActivity());
        mAdapter.setmNuberPickListener((newVal, oldVal, order) -> {
            subscriptions.add(
                    menuDetailViewModel.itemQuantityChanged(order, newVal, oldVal).subscribe()
            );
        });
        mRecycleView.setAdapter(mAdapter);

        // Bind list of posts to the RecyclerView
        menuDetailViewModel.ordersObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribe(order -> {
                    mAdapter.setItems(order.getOrders());
                    billTotal.setText
                            (String.format(getContext().getString(R.string.plate_price),
                                    order.getTotal()));
                });

        return rootView;
    }

    public void addFoodToOrder(FoodPlate foodPlate) {
        menuDetailViewModel.addOrder(foodPlate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        subscriptions.unsubscribe();
        unbinder.unbind();
    }
}
