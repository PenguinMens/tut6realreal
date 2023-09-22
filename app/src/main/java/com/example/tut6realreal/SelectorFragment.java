package com.example.tut6realreal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SelectorFragment extends Fragment implements  SelectorInterface{

    // TODO: Customize parameters
    private int mColumnCount = 1;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SelectorFragment newInstance(int columnCount) {
        SelectorFragment fragment = new SelectorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SelectorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selector, container, false);
        StructureData structureData = StructureData.get();
        System.out.println(structureData.get(0));
        List<Structure> structureList = structureData.getList();
        for (Structure structure : structureList) {
            System.out.println(structure);
        }
        RecyclerView recyclerView = view.findViewById(R.id.selectorRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        StructureRecyclerViewAdapter adapter = new StructureRecyclerViewAdapter(structureList,this);
        // Set the adapter
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int pos, View view) {

    }
}