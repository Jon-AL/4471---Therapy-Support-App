package com.example.mentalhealth;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateMoodActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText moodRatingEdt, moodDescriptionEdt;
    private Button updateMoodBtn, deleteMoodBtn;
    private MoodDBHelper mooddbHandler;
    String moodRating, moodDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_update_mood);

        // initializing all our variables.
        moodRatingEdt = findViewById(R.id.idEdtMoodRating);
        moodDescriptionEdt = findViewById(R.id.idEdtMoodRating);

        updateMoodBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteMoodBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        mooddbHandler = new MoodDBHelper(UpdateMoodActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        moodRating = getIntent().getStringExtra("Mood Rating");
        moodDescription = getIntent().getStringExtra("description");

        // setting data to edit text
        // of our update activity.
        moodRatingEdt.setText(moodRating);

        moodDescriptionEdt.setText(moodDescription);


        // adding on click listener to our update course button.
        updateMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                mooddbHandler.updateMoods(moodRating, moodRatingEdt.getText().toString(), moodDescriptionEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateMoodActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateMoodActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our course.
        deleteMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                mooddbHandler.deleteMood(moodRating);
                Toast.makeText(UpdateMoodActivity.this, "Deleted the mood entry", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateMoodActivity.this, MoodFragment.class);
                startActivity(i);
            }
        });
    }
}
