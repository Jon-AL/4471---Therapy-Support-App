package com.example.mentalhealth;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link add_mood_fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * References:
 *  1. https://stackoverflow.com/questions/43476261/android-seekbar-coding
 *  2. https://www.geeksforgeeks.org/how-to-create-and-add-data-to-sqlite-database-in-android/
 */
public class add_mood_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView textProgress, descriptionProgress;
    private final Calendar myCalendar= Calendar.getInstance();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int moodvalue, color;
    private Button submit_button, update_button;
    public add_mood_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_mood_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static add_mood_fragment newInstance(String param1, String param2) {
        add_mood_fragment fragment = new add_mood_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * Autogenerated code
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * This function affects and presents the views.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Establish the views, inputs, times, database, and seek bar.
        View promptsView = inflater.inflate(R.layout.add_mood_fragment, container, false);
        MoodDBHelper moodDBHelper = new MoodDBHelper(promptsView.getContext());
        SeekBar simpleSeekBar;
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.input);
        final EditText userdate = (EditText) promptsView
                .findViewById(R.id.date);

        // Initialize the buttons and descriptions required for the entries.
        simpleSeekBar=promptsView.findViewById(R.id.seekBar);
        textProgress = promptsView.findViewById(R.id.progress);
        descriptionProgress = promptsView.findViewById(R.id.progress_description);
        submit_button = promptsView.findViewById(R.id.button);
        update_button = promptsView.findViewById(R.id.updatebutton);


        // Seek bar logic
        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            /**
             * Progressive change of the seek bar as the user slides it from left to right or vice versa.
             * @param seekBar
             * @param i
             * @param fromUser
             */
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                updateView( i);
                setColor(i);
                setMoodValue(i);
                seekBar.setBackgroundColor(getColor());
            }

            /**
             * Auto Generated
             * @param seekBar
             */

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            /**
             * Auto generated
             * @param seekBar
             */
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



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

        // send the data to the data base and clear current outputs.
        submit_button.setOnClickListener(new View.OnClickListener() {
            /**
             * Submit data and clear the established outputs.
             * @param view
             */
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(userdate.getText().toString())&& TextUtils.isEmpty(userInput.getText().toString())) {
                    Toast.makeText(promptsView.getContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(userdate.getText().toString())){
                    Toast.makeText(promptsView.getContext(), "Please choose a date", Toast.LENGTH_SHORT).show();
                    return;
                }

                moodDBHelper.addNewMood(getMoodvalue(), userdate.getText().toString(), userInput.getText().toString());
                userdate.setText("");
                userInput.setText("");

            }
        });

       // Specific updates can be presented to the data.
        update_button.setOnClickListener(new View.OnClickListener(){
            /**
             * Allow users to click and then update the specific mood.
             * Add to the database and then clear the fields.
             * @param view
             */
            // validating if the text fields are empty or not.

            public void onClick(View view){
                if (TextUtils.isEmpty(userdate.getText().toString())&& TextUtils.isEmpty(userInput.getText().toString())) {
                    Toast.makeText(promptsView.getContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(userdate.getText().toString())){
                    Toast.makeText(promptsView.getContext(), "Please choose a date", Toast.LENGTH_SHORT).show();
                    return;
                }
                moodDBHelper.updateMoods(getMoodvalue(), userdate.getText().toString(), userInput.getText().toString(), userdate.getText().toString());
                userdate.setText("");
                userInput.setText("");
            }
        });
        return promptsView;
    }

    /**
     * Setter method for mood value
     * @param value
     */
    public void setMoodValue(int value){
        this.moodvalue = value;
    }

    /**
     * getter method for mood value
     * @return
     */
    public int getMoodvalue(){
        return this.moodvalue;
    }

    /**
     * Set the specific color based on mood.
     * @param mood_color
     */
    public void setColor(int mood_color) {
        if (mood_color == 1 || mood_color == 0 || mood_color == 9 || mood_color == 10) {
            this.color = Color.RED;
        } else if (mood_color == 2 || mood_color == 3 || mood_color == 7 || mood_color == 8) {
            this.color = Color.rgb(255, 165, 0);
        } else {
            this.color = Color.GREEN;
        }
    }

    /**
     * Getter method for color.
     * @return
     */
    public int getColor() {
        return this.color;
    }

    /**
     * A description will be collected from the switch cases. Each switch case has a
     * specific set of short descriptions, long descriptions, and color.
     * @param moodInt
     */
    public void updateView(int moodInt) {
        String shortDescription, longDescription, chosen;
        chosen = "Slidebar";
        longDescription = "";
        switch (moodInt) {
            case 0:
                shortDescription = "Severe Depression";
                longDescription = "Endless suicidal thoughts, no way out, no movement. Everything is bleak and it will always be like this";
                chosen = shortDescription;
                break;

            case 1:
                shortDescription = "Severe Depression";
                longDescription = "Feelings of hopelessness and guilt. Thoughts of suicide, little movement  and it feels impossible to do anything.";
                chosen = shortDescription;
                break;

            case 2:
                shortDescription = "Mild to Moderate Depression";
                longDescription = "Slow thinking, no appetite, need to be alone, excessive sleep or disturbed sleep. Everything feels like a struggle.";
                chosen = shortDescription;
                break;

            case 3:
                shortDescription = "Mild to Moderate Depression";
                longDescription = "Feelings of panic and anxiety, concentration difficult and memory poor, some comfort in routine.";
                chosen = shortDescription;
                break;

            case 4:
                shortDescription = "Balanced";
                longDescription = "Slight withdrawal from social situations, less concentration than usual, slight agitation.";
                chosen = shortDescription;
                break;

            case 5:
                shortDescription = "Balanced";
                longDescription = "Mood in balance, making good decisions. Life is going well and the outlook is good.";
                chosen = shortDescription;
                break;

            case 6:
                shortDescription = "Balanced";
                longDescription = "Self-esteem is good, optimistic, sociable and articulate. Making good decisions and getting work done.";
                chosen = shortDescription;
                break;

            case 7:
                shortDescription = "Hypomania";
                longDescription = "Very productive, charming and talkative. Doing everything to excess (e.g.: phone calls, writing, smoking, tea).";
                chosen = shortDescription;
                break;

            case 8:
                shortDescription = "Hypomania";
                longDescription = "Inflated self-esteem, rapid thoughts and speech. Doing too many things at once and not finishing any tasks";
                chosen = shortDescription;
                break;

            case 9:
                shortDescription = "Mania";
                longDescription = "Lost touch with reality, incoherent, no sleep. Feeling paranoid and vindictive. Behaviour is reckless.";
                chosen = shortDescription;
                break;

            case 10:
                shortDescription = "Mania";
                longDescription = "Total loss of judgement, out-of-control spending, religious delusions and hallucinations.";
                chosen = shortDescription;
                break;
        }
        // Set the specific progress.
        String finalresult = moodInt + ": " + chosen;
        textProgress.setText(finalresult);
        descriptionProgress.setText(longDescription);
    }
}