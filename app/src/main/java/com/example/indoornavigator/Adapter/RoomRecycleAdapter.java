package com.example.indoornavigator.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indoornavigator.Model.RoomDataModel;
import com.example.indoornavigator.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class RoomRecycleAdapter extends RecyclerView.Adapter<RoomRecycleAdapter.MyViewHolder> {
    private ArrayList<RoomDataModel> dataSet = null;
    private ArrayList<RoomDataModel> fullList= null;

    public RoomRecycleAdapter(ArrayList<RoomDataModel> itemList) {
        this.dataSet = itemList;
        fullList = new ArrayList<>(itemList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_location;
        TextView tv_room_name, tv_floor_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_location = itemView.findViewById(R.id.iv_location);
            tv_room_name = itemView.findViewById(R.id.tv_room_name);
            tv_floor_name = itemView.findViewById(R.id.tv_Floor_name);
        }
    }

    @NonNull
    @Override
    public RoomRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomRecycleAdapter.MyViewHolder holder, int position) {
        RoomDataModel currentItem = dataSet.get(position);
        holder.tv_room_name.setText(currentItem.getRoomName());
        holder.tv_floor_name.setText(currentItem.getFloorName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

//    public Filter  getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String charString = constraint.toString();
//                ArrayList<RoomDataModel> filteredList = new ArrayList<>();
//                if (constraint == null || constraint.length() == 0) {
//                    filteredList.addAll(fullList);
//                } else {
//                    String filterpattern = constraint.toString().toLowerCase().trim();
//                    for (RoomDataModel item : fullList) {
//                        if (item.getRoomName().toLowerCase().contains(filterpattern)) {
//                            filteredList.add(item);
//                        }
//                    }
//                }
//                FilterResults results = new FilterResults();
//                results.values = filteredList;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                dataSet.clear();
//                dataSet.addAll((ArrayList) results.values);
//            }
//        };
//    }

    public void setFilteredList(ArrayList<RoomDataModel> filteredList) {
        this.dataSet = filteredList;
        notifyDataSetChanged();
    }
}
