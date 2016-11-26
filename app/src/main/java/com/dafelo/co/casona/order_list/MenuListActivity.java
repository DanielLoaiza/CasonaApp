package com.dafelo.co.casona.order_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.dafelo.co.casona.R;
import com.dafelo.co.casona.internal.di.HasComponent;
import com.dafelo.co.casona.listeners.OnItemAddedListener;
import com.dafelo.co.casona.main.BaseActivity;
import com.dafelo.co.casona.order_detail.MenuDetailFragment;
import com.dafelo.co.casona.order_detail.MenuListFragment;
import com.dafelo.co.casona.order_detail.data.entity.Food;
import com.dafelo.co.casona.order_detail.di.DaggerMenuComponent;
import com.dafelo.co.casona.order_detail.di.MenuComponent;
import com.dafelo.co.casona.order_detail.di.MenuModule;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Orders. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MenuListActivity extends BaseActivity implements OnItemAddedListener, HasComponent<MenuComponent> {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @Nullable @BindView(R.id.menu_item_list_container)
    FrameLayout menuFragmentContainer;
    @Nullable @BindView(R.id.menu_detail_container)
    FrameLayout detailFragmentContainer;
    @Nullable MenuDetailFragment menuDetailFragment;
    @Nullable MenuListFragment menuListFragment;
    private MenuComponent menuComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        this.initializeInjector();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if(savedInstanceState == null) {
            //Your RecyclerView
            if (menuFragmentContainer != null) {
                menuListFragment = new MenuListFragment();
                addFragment(R.id.menu_item_list_container, menuListFragment);
            }

            if (detailFragmentContainer != null) {
                menuDetailFragment = new MenuDetailFragment();
                addFragment(R.id.menu_detail_container, menuDetailFragment);
            }
        }
    }

    private void initializeInjector() {
        this.menuComponent = DaggerMenuComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .menuModule(new MenuModule())
                .build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemAdd(Food plate) {
        if (menuDetailFragment != null) {
            menuDetailFragment.addFoodToOrder(plate);
        }
    }

    @Override
    public MenuComponent getComponent() {
        return menuComponent;
    }
}
