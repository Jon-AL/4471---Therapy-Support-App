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
 * Base features of creating and adding a database has been created.
 * Additional functionality will be added.
 *
 * @author Michael Tang-Tran
 * @date: November 5, 2022
 *
 * The code references two resources:
 * 1. https://www.geeksforgeeks.org/how-to-create-and-add-data-to-sqlite-database-in-android/
 * 2. https://www.youtube.com/watch?v=aQAIMY-HzL8
 * */
public class MoodDBHelper extends SQLiteOpenHelper{
    private static final String DB_Name = "Mood";
    private static final int DB_version = 3;

    private static final String Table_Name = "moods";
    private static final String id_col = "id";
    private static final String moodRating_col = "mood_rating";
    private static final String description_col = "description";

    public MoodDBHelper(Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_Name + " ("
                + id_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + moodRating_col + " TEXT,"
                + description_col +  " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    public void addNewMood(String moodRating, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Create the values
        values.put(moodRating_col, moodRating);
        values.put(description_col, description);

        // Store the values into the database table.
        db.insert(Table_Name, null, values);

        db.close();
    }

    // we have created a new method for reading all the courses.
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
                moodModalArrayList.add(new MoodModal(cursorMoods.getString(0),
                        cursorMoods.getString(1)));

            } while (cursorMoods.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorMoods.close();
        return moodModalArrayList;
    }


    // below is the method for updating our courses
    public void updateMoods(String originalCourseName, String courseName, String courseDescription) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(moodRating_col, courseName);

        values.put(description_col, courseDescription);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(Table_Name, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    // below is the method for deleting our course.
    public void deleteMood(String description) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(Table_Name, "name=?", new String[]{description});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }
}
