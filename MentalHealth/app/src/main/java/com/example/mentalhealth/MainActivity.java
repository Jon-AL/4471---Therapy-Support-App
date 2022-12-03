package com.example.mentalhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;



/**
 * MainActivity is the class that starts the Android app.
 * It sets up the navigation menu and continues the app progress.
 * References:
 * https://stackoverflow.com/questions/43476261/android-seekbar-coding
 */

public class MainActivity extends AppCompatActivity {


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView nv;

    /**
     * onCreate starts and continues the process of the app state.
     * The navigation menu is created.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the mood fragment as the starting page.
        add_mood_fragment addMoodFragment = new add_mood_fragment();
        setMyFragment(addMoodFragment);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Start the navigation menu
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            /**
             * Naviagation screen
             * @param item
             * @return the user will choose the desired topic that they want.
             */
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.add_mood) {
                    add_mood_fragment addMoodFragment = new add_mood_fragment();
                    setMyFragment(addMoodFragment);
                } else if (id == R.id.symptoms) {
                    SymptomFragment symptomFragment = new SymptomFragment();
                    setMyFragment(symptomFragment);
                }
                else if (id == R.id.medication) {
                    MedicationFragment medicationFragment = new MedicationFragment();
                    setMyFragment(medicationFragment);

                } else if (id == R.id.moods) {
                    MoodFragment moodFragment = new MoodFragment();
                    setMyFragment(moodFragment);
                } else if (id == R.id.medicationList){
                    MedicationListFragment medlistfrag = new MedicationListFragment();
                    setMyFragment(medlistfrag);
                } else if (id == R.id.Mood_Report){
                    MoodReport moodreport = new MoodReport();
                    setMyFragment(moodreport);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }


        });

    }

    /**
     * Checks if option is there.
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Create option and return true once it is.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    /**
     * Set the fragment container so fragments can be added.
     * @param fragment
     */
    private void setMyFragment(Fragment fragment)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}