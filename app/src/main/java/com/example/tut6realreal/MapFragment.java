package com.example.tut6realreal;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tut6realreal.placeholder.PlaceholderContent;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class MapFragment extends Fragment implements  MapInterface{

    // TODO: Customize parameters
    private int mColumnCount = 1;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MapFragment newInstance(int columnCount) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MapFragment() {
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
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        MapData mapData = MapData.get();

        RecyclerView recyclerView = view.findViewById(R.id.mapRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),mapData.HEIGHT,GridLayoutManager.HORIZONTAL,false));
        ArrayList<MapElement> mapElementsList = new ArrayList<MapElement>();
        for(int j=0; j<mapData.WIDTH;j++){
            for(int i=0; i<mapData.HEIGHT; i++){
                mapElementsList.add(mapData.get(i,j));
                System.out.println(mapData.get(i,j));
                Log.d("j",String.valueOf(i));
            }
            Log.d("i",String.valueOf(j));
        }
        Log.d("Map size",String.valueOf(mapElementsList.size()));
        MyMapRecyclerViewAdapter adapter = new MyMapRecyclerViewAdapter(mapElementsList,this,mapData);
        // Set the adapter
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int pos, View view) {

    }
}