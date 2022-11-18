package com.example.mentalhealth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoodFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // creating variables for our edittext, button and dbhandler
    private EditText moodRatingEdt, moodDescriptionEdt;
    private Button addMoodBtn, readMoodBtn, deleteAllMoodsBtn;
    public MoodDBHelper MooddbHelper;

    RecyclerView rc;
    Adapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoodFragment newInstance(String param1, String param2) {
        MoodFragment fragment = new MoodFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        ArrayList<MoodModal> MoodModalArrayList;
        View view=inflater.inflate(R.layout.fragment_mood, container, false);

        //moodRatingEdt = view.findViewById(R.id.idEdtMoodRating);
        //moodDescriptionEdt = view.findViewById(R.id.idEdtMoodDescription);
        //moodrc.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // initializing all our variables.
        moodRatingEdt = view.findViewById(R.id.idEdtMoodRating);
        moodDescriptionEdt = view.findViewById(R.id.idEdtMoodDescription);

        addMoodBtn = view.findViewById(R.id.idBtnAddMood);
        readMoodBtn = view.findViewById(R.id.idBtnReadMood);
        deleteAllMoodsBtn = view.findViewById(R.id.idBtnDeleteAllMoods);

        // creating a new dbhandler class
        // and passing our context to it.
        MooddbHelper = new MoodDBHelper(view.getContext());

        // below line is to add on click listener for our add course button.
        addMoodBtn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              // below line is to get data from all edit text fields.
                                              String moodRating = moodRatingEdt.getText().toString();
                                              String moodDescription = moodDescriptionEdt.getText().toString();
                                              // validating if the text fields are empty or not.
                                              if (moodRating.isEmpty() && moodDescription.isEmpty()) {
                                                  Toast.makeText(view.getContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                                                  return;
                                              }

                                              // on below line we are calling a method to add new
                                              // course to sqlite data and pass all our values to it.
                                              MooddbHelper.addNewMood(moodRating, moodDescription);

                                              // after adding the data we are displaying a toast message.
                                              Toast.makeText(view.getContext(), "Mood has been added.", Toast.LENGTH_SHORT).show();
                                              moodRatingEdt.setText("");
                                              moodDescriptionEdt.setText("");

                                          }
                                      });

        readMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(v.getContext(), ViewMoods.class);
                startActivity(i);
            }
        });

        deleteAllMoodsBtn.setOnClickListener(new View.OnClickListener() {
            public void deleteDatabase(View v){
                MooddbHelper.
            }
        });

        // initializing our all variables.
        MoodModalArrayList = new ArrayList<>();

        ListView l;
        // getting our course array
        // list from db handler class.
        MoodModalArrayList = MooddbHelper.readMoods();

        ArrayList<String >Mood_list_data = new ArrayList<String>();
        for(MoodModal i: MoodModalArrayList){
            String temp = i.getId() +" " +i.getMoodDescription();
            Mood_list_data.add(temp);
        }
       // System.out.print(MoodModalArrayList.get(1).toString());
       l = view.findViewById(R.id.list);
       ArrayAdapter<String> arr;
       arr = new ArrayAdapter<String>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Mood_list_data);
       l.setAdapter(arr);

       adapter=new Adapter(view.getContext());

       //moodrc.setAdapter(adapter);

        return view;
    }
}