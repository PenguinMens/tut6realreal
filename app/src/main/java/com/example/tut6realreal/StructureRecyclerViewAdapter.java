package com.example.tut6realreal;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tut6realreal.placeholder.PlaceholderContent.PlaceholderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StructureRecyclerViewAdapter extends RecyclerView.Adapter<StructureRecyclerViewAdapter.ViewHolder> implements View.OnTouchListener
{

    private final List<Structure> structureList;

    private final SelectorInterface mapInterface;

    public StructureRecyclerViewAdapter(List<Structure> items, SelectorInterface mapInterface) {
        this.structureList = items;

        this.mapInterface = mapInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    // Initialize view
    View view= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_selection,parent,false);
    // return holder
    return new ViewHolder(view, mapInterface);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("getItemCount",String.valueOf(position));
        System.out.println(structureList.get(position).getLabel());
       // holder.structureName.setText();
        holder.structureImage.setImageResource(structureList.get(position).getDrawableId());
        holder.structureImage.setTag(String.format("structureTag%d",position));
    }
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data, shadowBuilder, v, 0);
                return true;
        }
        return false;
    }
    @Override
    public int getItemCount() {
        return structureList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView structureImage;
        TextView structureName;


        public ViewHolder(@NonNull View itemView, SelectorInterface selectorInterface) {
            super(itemView);
            structureImage = itemView.findViewById(R.id.structure_image);
            structureName = itemView.findViewById(R.id.structure_name);


            itemView.setOnClickListener(new  View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(selectorInterface != null){
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION)
                        {
                            int selectedPos = getLayoutPosition();
                            notifyItemChanged(selectedPos);
                            selectorInterface.onItemClick(pos,view);
                        }
                    }

                }
            });

        }

    }
}