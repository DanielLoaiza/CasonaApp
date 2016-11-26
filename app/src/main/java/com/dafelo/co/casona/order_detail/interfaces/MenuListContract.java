package com.dafelo.co.casona.order_detail.interfaces;

import com.dafelo.co.casona.adapters.SimpleSectionedRecyclerViewAdapter;
import com.dafelo.co.casona.main.presenter.BasePresenter;
import com.dafelo.co.casona.main.presenter.BaseView;
import com.dafelo.co.casona.order_detail.data.entity.Food;

import java.util.List;

/**
 * Created by root on 24/11/16.
 */

public interface MenuListContract {
    interface View extends BaseView {

        void populateAdapter(List<SimpleSectionedRecyclerViewAdapter.Section>sections,
                             List<Food> food);
    }

     interface Presenter extends BasePresenter<View> {
        void getFoodList();
    }
}
