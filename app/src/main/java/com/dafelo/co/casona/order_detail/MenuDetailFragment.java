package com.dafelo.co.casona.order_detail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dafelo.co.casona.R;
import com.dafelo.co.casona.adapters.DinningOrderAdapter;
import com.dafelo.co.casona.listeners.OnItemAddedListener;
import com.dafelo.co.casona.order_detail.data.datasource.OrderDataStoreFactory;
import com.dafelo.co.casona.order_detail.data.entity.Food;
import com.dafelo.co.casona.order_detail.data.repository.OrderDataRepository;

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
    @BindView(R.id.save_order)
    Button saveOrder;
    private MenuDetailViewModel menuDetailViewModel;
    /** Hold active loading observable subscriptions, so that they can be unsubscribed from when the activity is destroyed */
    private CompositeSubscription subscriptions;
    private Unbinder unbinder;
    private OnItemAddedListener mCallback;

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
        // a bit messy without DI
        menuDetailViewModel = new MenuDetailViewModel(
                new OrderDataRepository(
                        new OrderDataStoreFactory()));
        saveOrder.setOnClickListener(view -> menuDetailViewModel.saveOrder());
        setupRecycleView();
        setupBindings();

        return rootView;
    }

    public void addFoodToOrder(Food foodPlate) {
        menuDetailViewModel.addOrder(foodPlate);
    }

    public void setupBindings() {
        try {
            menuDetailViewModel.ordersObservable().observeOn(AndroidSchedulers.mainThread())
                    .subscribe(order -> {
                        mAdapter.setItems(order.getOrders());
                        billTotal.setText
                                (String.format(getContext().getString(R.string.plate_price),
                                        order.getTotal()));
                    }, Throwable::printStackTrace);

            menuDetailViewModel.isOrderSaveObservable().observeOn(AndroidSchedulers.mainThread())
                    .subscribe(orderSaved -> {
                        if(orderSaved) {
                            this.itemAddFinished();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupRecycleView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setNestedScrollingEnabled(false);
        // use a linear layout manager
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // specify an adapter (see also next example)
        mAdapter = new DinningOrderAdapter(getActivity());
        mAdapter.setOnItemrRemovedListener(menuDetailViewModel::removeOrder);

        mAdapter.setmNuberPickListener((newVal, oldVal, order) ->
                menuDetailViewModel.itemQuantityChanged(order, newVal, oldVal)
        );
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnItemAddedListener) {
            try {
                mCallback = (OnItemAddedListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString()
                        + " must implement OnItemAddedListener");
            }
        }
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
    }

    public void itemAddFinished() {
        mCallback.itemAddFinished();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        subscriptions.unsubscribe();
        unbinder.unbind();
    }
}
