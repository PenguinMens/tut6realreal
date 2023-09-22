package com.example.tut6realreal;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.example.tut6realreal.placeholder.PlaceholderContent.PlaceholderItem;


import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMapRecyclerViewAdapter extends RecyclerView.Adapter<MyMapRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<MapElement> mapData;

    private final MapInterface mapInterface;
    private final MapData mapStats;
    public MyMapRecyclerViewAdapter(ArrayList<MapElement>items,MapInterface mapInterface, MapData stats) {
        this.mapData = items;
        this.mapStats = stats;
        this.mapInterface = mapInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    // Initialize view
    View view= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.grid_cell,parent,false);
    int size = parent.getMeasuredHeight() / mapStats.HEIGHT + 1;
    // return holder
    return new ViewHolder(view,size, mapInterface);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.top_left.setImageResource(mapData.get(position).getNorthWest());
        holder.top_right.setImageResource(mapData.get(position).getNorthEast());
        holder.bot_left.setImageResource(mapData.get(position).getSouthWest());
        holder.bot_right.setImageResource(mapData.get(position).getSouthEast());
        if(mapData.get(position).getStructure() != null){
            Log.d("StucturePos",String.valueOf(mapData.get(position).getStructure().getLabel()));
            holder.overlayIcon.setImageResource(mapData.get(position).getStructure().getDrawableId());
        }
        else{
            holder.overlayIcon.setImageResource(android.R.color.transparent);
        }
    }

    @Override
    public int getItemCount() {
        return mapData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView top_left;
        ImageView top_right;
        ImageView bot_left;
        ImageView bot_right;
        ImageView overlayIcon;

        public ViewHolder(View itemView,int size, MapInterface mapInterface) {
            super(itemView);
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;
            Log.d("Grid Size: ",String.valueOf(size));
            top_left = itemView.findViewById(R.id.top_left_cell);
            top_right = itemView.findViewById(R.id.top_right_cell);
            bot_left = itemView.findViewById(R.id.bot_left_cell);
            bot_right = itemView.findViewById(R.id.bot_right_cell);
            overlayIcon = itemView.findViewById(R.id.overlayIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mapInterface != null) {
                        int pos = getAbsoluteAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            int selectedPos = getLayoutPosition();
                            mapInterface.onItemClick(pos, view);
                            notifyItemChanged(selectedPos);
                        }
                    }
                }
            });
        }

    }
}