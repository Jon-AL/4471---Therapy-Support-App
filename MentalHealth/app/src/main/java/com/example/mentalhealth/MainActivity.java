package com.example.mentalhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * https://stackoverflow.com/questions/43476261/android-seekbar-coding
 * */

public class MainActivity<textProgress> extends AppCompatActivity {


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nv;

    final Calendar myCalendar= Calendar.getInstance();
    private TextView textProgress;
    private TextView textParagraph;
    private MoodDBHelper Mooddbhelper;
    private int moodvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoodFragment moodFragment = new MoodFragment();
        setMyFragment(moodFragment);



        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);



        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();


                if (id == R.id.moods) {

                    MoodFragment moodFragment = new MoodFragment();
                    setMyFragment(moodFragment);


                } else if (id == R.id.symptoms) {

                    SymptomFragment symptomFragment = new SymptomFragment();
                    setMyFragment(symptomFragment);


                }

                else if (id == R.id.reports) {

                    ReportsFragment reportsFragment = new ReportsFragment();
                    setMyFragment(reportsFragment);

                }


                else if (id == R.id.medication) {

                    MedicationFragment medicationFragment = new MedicationFragment();
                    setMyFragment(medicationFragment);


                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }



        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int progressChangedValue = 0;

        int id=item.getItemId();
        if(id==R.id.action_add) {

            LayoutInflater li = LayoutInflater.from(this);
            View promptsView = li.inflate(R.layout.custom, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            alertDialogBuilder.setView(promptsView);

            SeekBar simpleSeekBar;
            simpleSeekBar=promptsView.findViewById(R.id.moodSeekBar_discrete);
            textProgress = promptsView.findViewById(R.id.progress);
            simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                    updateView( i);
                    setMoodValue(i);
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.input);
            final EditText userdate = (EditText) promptsView
                    .findViewById(R.id.date);

            Spinner s = (Spinner)  promptsView.findViewById(R.id.Spinner01);




            String[] arraySpinner = new String[] {
                    "High", "Moderate", "Low",
            };

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s.setAdapter(adapter);

            DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
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
            userdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            MoodDBHelper MooddbHelper = new MoodDBHelper(MainActivity.this);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    System.out.println(progressChangedValue);
                                    MooddbHelper.addNewMood(getMoodvalue(), userdate.getText().toString(), userInput.getText().toString());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }


        return super.onOptionsItemSelected(item);


    }

    public void setMoodValue(int value){
        this.moodvalue = value;
    }

    public int getMoodvalue(){
        return this.moodvalue;
    }


    public void updateView(int moodInt) {
        String shortDescription, longDescription, colour, chosen;
        //textProgress.setText("Hello world");
        chosen = "Slidebar";
        switch (moodInt) {
            case 0:
                shortDescription = "Severe Depression";
                longDescription = "Endless suicidal thoughts, no way out, no movement. Everything is bleak and it will always be like this";
                colour = "red"; /*"Alarm bells red"*/
                chosen = shortDescription;
                break;
            case 1:
                shortDescription = "Severe Depression";
                longDescription = "Feelings of hopelessness and guilt. Thoughts of suicide, little movement  and it feels impossible to do anything.";
                colour = "red"; /*"Alarm bells red"*/
                chosen = shortDescription;
                break;
            case 2:
                shortDescription = "Mild to Moderate Depression";
                longDescription = "Slow thinking, no appetite, need to be alone, excessive sleep or disturbed sleep. Everything feels like a struggle.";
                colour = "orange"; /*"warning orange"*/
                chosen = shortDescription;
                break;
            case 3:
                shortDescription = "Mild to Moderate Depression";
                longDescription = "Feelings of panic and anxiety, concentration difficult and memory poor, some comfort in routine.";
                colour = "orange"; /*"warning orange"*/
                chosen = shortDescription;
                break;
            case 4:
                shortDescription = "Balanced";
                longDescription = "Slight withdrawal from social situations, less concentration than usual, slight agitation.";
                colour = "green"; /*"positive green orange"*/
                chosen = shortDescription;
                break;
            case 5:
                shortDescription = "Balanced";
                longDescription = "Mood in balance, making good decisions. Life is going well and the outlook is good.";
                colour = "green"; /*"positive green orange"*/
                chosen = shortDescription;
                break;
            case 6:
                shortDescription = "Balanced";
                longDescription = "Self-esteem is good, optimistic, sociable and articulate. Making good decisions adn getting work done.";
                colour = "green"; /*"positive green orange"*/
                chosen = shortDescription;
                break;
            case 7:
                shortDescription = "Hypomania";
                longDescription = "Very productive, charming and talkative. Doing everything to excess (e.g.: phone calls, writing, smoking, tea).";
                colour = "orange"; /*"warning orange"*/
                chosen = shortDescription;
                break;
            case 8:
                shortDescription = "Hypomania";
                longDescription = "Inflated self-esteem, rapid thoughts and speech. Doing too many things at once and not finishing any tasks";
                colour = "orange"; /*"warning orange"*/
                chosen = shortDescription;
                break;
            case 9:
                shortDescription = "Mania";
                longDescription = "Lost touch with reality, incoherent, no sleep. Feeling paranoid and vindictive. Behaviour is reckless.";
                colour = "red"; /*"Alarm bells red"*/
                chosen = shortDescription;
                break;
            case 10:
                shortDescription = "Mania";
                longDescription = "Total loss of judgement, out-of-control spending, religious delusions and hallucinations.";
                colour = "red"; /*"Alarm bells red"*/
                chosen = shortDescription;
                break;
        }
        String finalresult = Integer.toString(moodInt) + ": " + chosen;
        textProgress.setText(finalresult);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    private void setMyFragment(Fragment fragment)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}