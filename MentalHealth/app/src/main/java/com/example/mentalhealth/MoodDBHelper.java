package com.example.mentalhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 *
 * Mood db class.
 *
 *
 * The MoodDBHelper class controls the SQLite Database. It will provide functions
 * where mood classes will access its data to do their methods.
 *
 * @author Michael Tang-Tran
 * @date: November 5, 2022
 *
 * The code references two resources:
 * 1. https://www.geeksforgeeks.org/how-to-create-and-add-data-to-sqlite-database-in-android/
 * 2. https://www.youtube.com/watch?v=aQAIMY-HzL8
 * */
public class MoodDBHelper extends SQLiteOpenHelper{
    private static final String DB_Name = "Moodv2";
    private static final int DB_version = 3;

    private static final String Table_Name = "moods";
    private static final String id_col = "id";
    private static final String moodRating_col = "mood_rating";
    private static final String description_col = "description";
    private static final String date_col = "date";
    public MoodDBHelper(Context context) {
        super(context, DB_Name, null, DB_version);
    }

    /**
     * Initialize the table for mood
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_Name + " ("
                + id_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + moodRating_col + " INT,"
                + date_col + " TEXT,"
                + description_col +  " TEXT)";


        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    /**
     * Add a new mood entry into the database
     * @param moodRating
     * @param date
     * @param description
     */
    public void addNewMood(int moodRating, String date, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Create the values
        values.put(moodRating_col, moodRating);

        values.put(date_col, date);
        values.put(description_col, description);

        // Store the values into the database table.
        db.insert(Table_Name, null, values);

        db.close();
    }

    /**
     *  Read all mood data and store it inside an array of mood modals.
     * @return arraylist of moodmodals
     */

    public ArrayList<MoodModal> readMoods() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorMoods = db.rawQuery("SELECT * FROM " + Table_Name, null);

        // on below line we are creating a new array list.
        ArrayList<MoodModal> moodModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorMoods.moveToFirst()) {
            do {

                // on below line we are adding the data from cursor to our array list.
                moodModalArrayList.add(new MoodModal(cursorMoods.getInt(1),
                        cursorMoods.getString(2), cursorMoods.getString(3)));

            } while (cursorMoods.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorMoods.close();

        return moodModalArrayList;
    }


    /**
     * Update the moods based on the new entry
     * @param moodRating
     * @param mooddate
     * @param moodDescription
     * @param oldDescription
     */
    // below is the method for updating our courses
    public void updateMoods(int moodRating, String mooddate, String moodDescription, String oldDescription) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(moodRating_col, moodRating);
        values.put(date_col, mooddate);
        values.put(description_col, moodDescription);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(Table_Name, values, "date=?", new String[]{String.valueOf(oldDescription)});
        db.close();
    }

    /**
     * Delete the desired mood record
     * @param date
     */
    // below is the method for deleting our course.
    public void deleteMood(String date) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(Table_Name, "date=?", new String[]{String.valueOf(date)});

        //System.out.println("did we get here");
        db.close();
    }

    /**
     * Return a sorted list of moodmodals by date
     * @return arraylist of moodmodals
     */
    public ArrayList<MoodModal> ReadSortByDate(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorMoods = db.rawQuery("SELECT * FROM " + Table_Name + " ORDER BY date", null);

        // on below line we are creating a new array list.
        ArrayList<MoodModal> moodModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorMoods.moveToFirst()) {
            do {

                // on below line we are adding the data from cursor to our array list.
                moodModalArrayList.add(new MoodModal(cursorMoods.getInt(1),
                        cursorMoods.getString(2), cursorMoods.getString(3)));

            } while (cursorMoods.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorMoods.close();

        return moodModalArrayList;

    }

    /**
     * Check if the db already exists
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }
}
