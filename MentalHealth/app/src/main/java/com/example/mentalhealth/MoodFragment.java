package com.example.mentalhealth;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

/**
 * The MoodFragment class will handle the controller and mood for the data.
 *
 * MoodFragment will also delete the appropriate data where necessary.
 * MoodFragment will control and present the view for the mood data.
 *
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
    private Button  readMoodBtn, deleteAllMoodsBtn;
    public MoodDBHelper MooddbHelper;
    private final Calendar myCalendar= Calendar.getInstance();

    Adapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * empty constructor is added.
     */
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

    /**
     * Autogenerated function.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Creates the view and controls the data of update and delete.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        View view=inflater.inflate(R.layout.fragment_mood, container, false);

        final EditText userdate = (EditText) view.findViewById(R.id.date1);
        // Users can set a specific date that they want.
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {

            /**
             * Set the specific date that the user wants.
             * @param view
             * @param year
             * @param month
             * @param day
             */
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {


                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="MM/dd/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                userdate.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        // users can choose the specific date that they want.
        userdate.setOnClickListener(new View.OnClickListener() {
            /**
             * A view is presented to the user.
             * @param view
             */
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        // initializing all our variables.
        readMoodBtn = view.findViewById(R.id.idBtnReadMood);
        deleteAllMoodsBtn = view.findViewById(R.id.idBtnDeleteAllMoods);


        // creating a new dbhandler class
        // and passing our context to it.
        MooddbHelper = new MoodDBHelper(view.getContext());

        // Respond to the user clicking the button.
        readMoodBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Read all the data from the mood.
             * @param v
             */
            @Override
            public void onClick(View v) {

                ListView l;

                // getting our course array
                // list from db handler class.
                ArrayList<MoodModal> MoodModalArrayList;
                MoodModalArrayList = MooddbHelper.ReadSortByDate();
                ArrayList<String >Mood_list_data = new ArrayList<String>();

                // store it inside an arraylist.
                for(MoodModal i: MoodModalArrayList){
                    String temp ="Date: " + i.getDate() + "; Mood Rating: " + i.getMoodRating()+"; "+  "Description: "+i.getMoodDescription();

                    Mood_list_data.add(temp);
                }

                // Reverse the list order.
                Collections.reverse(Mood_list_data);

                // Create the view and present the list.
                l = view.findViewById(R.id.list);
                ArrayAdapter<String> arr;
                arr = new ArrayAdapter<String>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Mood_list_data);
                l.setAdapter(arr);
            }
        });

        // Delete the desired mood.;
        deleteAllMoodsBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * delete the specific mood entry.
             * @param v
             */
            public void onClick(View v){

                String moodDescription = userdate.getText().toString();

                // Check if the mooddescription is empty.
                if (moodDescription.isEmpty()){
                    Toast.makeText(view.getContext(), "Please add the old date you want deleted", Toast.LENGTH_SHORT).show();
                    return;
                }
                // delete the mood from the database
                MooddbHelper.deleteMood(moodDescription);

                // clear the fields.
                userdate.setText("");
            }
        });

        // Update the adaptor
        adapter=new Adapter(view.getContext());



        return view;
    }
}