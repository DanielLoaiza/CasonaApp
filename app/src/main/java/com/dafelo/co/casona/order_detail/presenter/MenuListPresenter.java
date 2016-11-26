package com.dafelo.co.casona.order_detail.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dafelo.co.casona.adapters.SimpleSectionedRecyclerViewAdapter;
import com.dafelo.co.casona.main.domain.interactors.DefaultSubscriber;
import com.dafelo.co.casona.main.domain.interactors.UseCase;
import com.dafelo.co.casona.order_detail.data.entity.Food;
import com.dafelo.co.casona.order_detail.data.entity.Section;
import com.dafelo.co.casona.order_detail.data.entity.Sections;
import com.dafelo.co.casona.order_detail.interfaces.MenuListContract;
import java.util.List;
import javax.inject.Named;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by root on 24/11/16.
 */

public class MenuListPresenter implements MenuListContract.Presenter {

    @NonNull
    private MenuListContract.View mMenuView;
    private final UseCase getFoodListUserCase;

    @Inject
    public MenuListPresenter(@Named("foodList") UseCase getFoodListUserCase) {
        this.getFoodListUserCase = getFoodListUserCase;
    }

    @Override
    public void getFoodList() {
        this.getFoodListUserCase.execute(new FoodListSubscriber());
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void setView(MenuListContract.View view) {

    }

    @Override
    public void unsubscribe() {

    }

    private final class FoodListSubscriber extends DefaultSubscriber<Sections> {

        @Override public void onCompleted() {
            // do nothing
        }

        @Override public void onError(Throwable e) {
            mMenuView.showError(e);
        }

        @Override public void onNext(Sections sections) {
           Log.e("data", "data");
        }
    }
}
