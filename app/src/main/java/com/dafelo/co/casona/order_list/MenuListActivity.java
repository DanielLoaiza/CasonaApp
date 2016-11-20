package com.dafelo.co.casona.order_list;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.dafelo.co.casona.BO.FoodPlate;
import com.dafelo.co.casona.R;
import com.dafelo.co.casona.listeners.OnItemAddedListener;
import com.dafelo.co.casona.order_detail.MenuDetailFragment;
import com.dafelo.co.casona.order_detail.MenuListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Orders. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MenuListActivity extends AppCompatActivity implements OnItemAddedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @Nullable @BindView(R.id.menu_item_list_container)
    FrameLayout menuFragmentContainer;
    @Nullable @BindView(R.id.menu_detail_container)
    FrameLayout detailFragmentContainer;
    @Nullable MenuDetailFragment menuDetailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if(savedInstanceState == null) {
            //Your RecyclerView
            if (menuFragmentContainer != null) {
                MenuListFragment fragment = new MenuListFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.menu_item_list_container, fragment)
                        .commit();
            }

            if (detailFragmentContainer != null) {
                 menuDetailFragment = new MenuDetailFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.menu_detail_container, menuDetailFragment)
                        .commit();
            }
        }
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
    public void onItemAdd(FoodPlate plate) {
        if (menuDetailFragment != null) {
            menuDetailFragment.addFoodToOrder(plate);
        }
    }
}
