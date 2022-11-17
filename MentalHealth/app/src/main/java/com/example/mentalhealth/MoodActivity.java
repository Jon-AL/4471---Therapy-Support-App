package com.example.mentalhealth;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MoodFragment extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText moodRatingEdt, moodDescriptionEdt;
    private Button addMoodBtn, readMoodBtn;
    private MoodDBHelper MooddbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mood);





        // initializing all our variables.
        moodRatingEdt = findViewById(R.id.idEdtMoodRating);
        moodDescriptionEdt = findViewById(R.id.idEdtMoodDescription);

        addMoodBtn = findViewById(R.id.idBtnAddCourse);
        readMoodBtn = findViewById(R.id.idBtnReadCourse);

        // creating a new dbhandler class
        // and passing our context to it.
        MooddbHelper = new MoodDBHelper(MoodActivity.this);

        // below line is to add on click listener for our add course button.
        addMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String moodRating = moodRatingEdt.getText().toString();
                String moodDescription = moodRatingEdt.getText().toString();
                // validating if the text fields are empty or not.
                if (moodRating.isEmpty() && moodDescription.isEmpty()) {
                    Toast.makeText(MoodActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                MooddbHelper.addNewMood(moodRating, moodDescription);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MoodActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                moodRatingEdt.setText("");
                moodDescriptionEdt.setText("");

            }
        });

        readMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MoodActivity.this, ViewMoods.class);
                startActivity(i);
            }
        });
    }
}


