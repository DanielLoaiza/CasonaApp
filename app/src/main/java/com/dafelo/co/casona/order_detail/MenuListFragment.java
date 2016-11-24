package com.dafelo.co.casona.order_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dafelo.co.casona.BO.FoodPlate;
import com.dafelo.co.casona.R;
import com.dafelo.co.casona.adapters.PlateListAdapter;
import com.dafelo.co.casona.adapters.SimpleSectionedRecyclerViewAdapter;
import com.dafelo.co.casona.helpers.DividerItemDecoration;
import com.dafelo.co.casona.listeners.OnItemAddedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by root on 19/11/16.
 */

public class MenuListFragment extends Fragment {

    @BindView(R.id.menu_list) RecyclerView mRecyclerView;
    private Unbinder unbinder;
    private OnItemAddedListener mCallback;
    private PlateListAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MenuListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        // MOCK DATA
        List<FoodPlate> food = new ArrayList<>();
        food.add(new FoodPlate("Bandeja Paisa", 20000));
        food.add(new FoodPlate("Sancocho de Gallina", 15000));
        food.add(new FoodPlate("Lomo Hawaiano", 18000));
        food.add(new FoodPlate("Filete de pollo", 17000));
        food.add(new FoodPlate("Trucha al ajillo", 15000));
        //Your RecyclerView.Adapter
        mAdapter = new PlateListAdapter(getActivity(), food);
        mAdapter.setOnItemAddedListener(this::sendItemToActivity);

        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Especialidades"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(2,"Tipicos"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(4,"Varios"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(getActivity(),R.layout.section,
                R.id.section_text, mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);

        return rootView;
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

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void sendItemToActivity(FoodPlate plate) {
        if(mCallback != null) {
            mCallback.onItemAdd(plate);
        }
    }
}
