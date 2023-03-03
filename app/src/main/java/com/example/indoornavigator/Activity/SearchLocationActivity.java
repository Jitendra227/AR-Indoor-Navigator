package com.example.indoornavigator.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.indoornavigator.Adapter.RoomRecycleAdapter;
import com.example.indoornavigator.Model.RoomDataModel;
import com.example.indoornavigator.R;

import java.util.ArrayList;

public class SearchLocationActivity extends AppCompatActivity {

    private RoomRecycleAdapter roomRecycleAdapter;
    private ArrayList<RoomDataModel> dataset;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        searchView = findViewById(R.id.searchView_action_bar);
        searchView.clearFocus();

        fillRoomList();
        setUpRecyclerview();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        ArrayList<RoomDataModel> filteredList = new ArrayList<>();
        for(RoomDataModel item: dataset){
            if (item.getRoomName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else {
            roomRecycleAdapter.setFilteredList(filteredList);
        }
    }

    private void fillRoomList() {
        dataset = new ArrayList<>();
        dataset.add(new RoomDataModel("gf001", "office", "Ground Floor"));
        dataset.add(new RoomDataModel("gf101", "room 101", "first Floor"));
        dataset.add(new RoomDataModel("gf303", "auditorium", "third Floor"));
        dataset.add(new RoomDataModel("gf502", "ise lab", "fifth Floor"));
        dataset.add(new RoomDataModel("gf209", "staff room", "second Floor"));
        dataset.add(new RoomDataModel("gf107", "library", "first Floor"));
        dataset.add(new RoomDataModel("gf201", "gym", "second Floor"));
        dataset.add(new RoomDataModel("gf006", "computer lab", "Ground Floor"));
    }

    private void setUpRecyclerview() {
        RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        roomRecycleAdapter = new RoomRecycleAdapter(dataset);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(roomRecycleAdapter);
    }
}