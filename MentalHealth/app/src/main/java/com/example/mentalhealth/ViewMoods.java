package com.example.mentalhealth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewMoods extends Fragment {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<MoodModal> MoodModalArrayList;
    private MoodDBHelper moodDBHelper;
    private MoodAdapter MoodAdapter;
    private RecyclerView moodsRV;
    private ViewGroup container;
    private LayoutInflater inflater;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_activity_view_moods);
        View view=inflater.inflate(R.layout.fragment_activity_view_moods, container, false);
        // initializing our all variables.
        MoodModalArrayList = new ArrayList<>();
        moodDBHelper = new MoodDBHelper(view.getContext());

        // getting our course array
        // list from db handler class.
        MoodModalArrayList = moodDBHelper.readMoods();

        // on below line passing our array lost to our adapter class.
        MoodAdapter = new MoodAdapter(MoodModalArrayList, view.getContext());
        moodsRV = view.findViewById(R.id.idRVMoods);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        moodsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        moodsRV.setAdapter(MoodAdapter);
    }
}
