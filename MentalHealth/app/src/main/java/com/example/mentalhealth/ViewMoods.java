package com.example.mentalhealth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewMoods extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<MoodModal> MoodModalArrayList;
    private MoodDBHelper moodDBHelper;
    private MoodAdapter MoodAdapter;
    private RecyclerView moodsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_view_moods);

        // initializing our all variables.
        MoodModalArrayList = new ArrayList<>();
        moodDBHelper = new MoodDBHelper(ViewMoods.this);

        // getting our course array
        // list from db handler class.
        MoodModalArrayList = moodDBHelper.readMoods();

        // on below line passing our array lost to our adapter class.
        MoodAdapter = new MoodAdapter(MoodModalArrayList, ViewMoods.this);
        moodsRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewMoods.this, RecyclerView.VERTICAL, false);
        moodsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        moodsRV.setAdapter(MoodAdapter);
    }
}
