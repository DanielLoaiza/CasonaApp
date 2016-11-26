package com.dafelo.co.casona.order_detail.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dafelo.co.casona.adapters.SimpleSectionedRecyclerViewAdapter;
import com.dafelo.co.casona.main.domain.interactors.DefaultSubscriber;
import com.dafelo.co.casona.main.domain.interactors.UseCase;
import com.dafelo.co.casona.order_detail.data.entity.Food;
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
        mMenuView = view;
    }

    @Override
    public void unsubscribe() {

    }

    private final class FoodListSubscriber extends DefaultSubscriber<Sections> {

        @Override public void onCompleted() {
            // do nothing
        }

        @Override public void onError(Throwable e) {
            Log.e("error", e.getMessage());
        }

        @Override public void onNext(Sections sections) {
            final int[] nextStart = {0};
            // transform the data for sectioned adapter, getting the first item for each section
           Observable<List<SimpleSectionedRecyclerViewAdapter.Section>> simpleSections = Observable.from(sections.getSections())
                   .map(section -> {
                       SimpleSectionedRecyclerViewAdapter.Section simpleSection
                               = new SimpleSectionedRecyclerViewAdapter.Section(nextStart[0], section.getName());
                       nextStart[0] += section.getPlates().size();
                       return simpleSection;
           }).toList();

            // get the plates from sections and turns it into a single List
            // this is necessary for show correctly the info into de sectioned adapter
            Observable<List<Food>> food = Observable.from(sections.getSections())
                    .concatMap(section -> Observable.from(section.getPlates()))
                    .toList();

            Observable.zip(simpleSections, food, (sections1, foods) -> {
                mMenuView.populateAdapter(sections1, foods);
                return null;
            }).subscribe();
        }
    }
}
