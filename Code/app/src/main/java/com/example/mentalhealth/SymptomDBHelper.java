package com.example.mentalhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 *
 * Symptom db class.
 *
 * Base features of creating and adding a database has been created.
 * SymptomDBHelper is the helper function that allows the system to use SQLite to execute tasks.
 *
 * @author Michael Tang-Tran
 * @date: November 5, 2022
 *
 * The code references two resources:
 * 1. https://www.geeksforgeeks.org/how-to-create-and-add-data-to-sqlite-database-in-android/
 * 2. https://www.youtube.com/watch?v=aQAIMY-HzL8
 * */
public class SymptomDBHelper extends SQLiteOpenHelper {

    private static final String DB_Name = "moods";
    private static final int DB_version = 3;

    private static final String Table_Name = "symptoms";
    private static final String id_col = "id";
    private static final String datetime_col = "datetime";
    private static final String symptom_name_col = "symptom_name";
    private static final String description_col = "description";

    /**
     * Constructor for the db
     * @param context
     */
    public SymptomDBHelper(Context context) {
        super(context, DB_Name, null, DB_version);
    }

    /**
     * Initalizes the table inside the db with the specific attributes.
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_Name + " ("
                + id_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + symptom_name_col + " TEXT,"
                + datetime_col + " TEXT,"
                + description_col +  " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    /**
     * Add a new symptom entry into the app.
     * @param symptomName
     * @param datetimeval
     * @param description
     */
    public void addNewSymptom(String symptomName, String datetimeval, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Create the values
        values.put(datetime_col, datetimeval);
        values.put(symptom_name_col, symptomName);
        values.put(description_col, description);

        // Store the values into the database table.
        db.insert(Table_Name, null, values);

        db.close();
    }

    /**
     * Delete a specific symptom record
     * @param symptomName
     */
    public void deleteSymptom(String symptomName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(Table_Name, "symptom_name=?", new String[]{symptomName});
        //System.out.println("here");
        db.close();
    }

    /**
     * Update the specific symptom with new entry by finding the entry through its old name.
     * @param symptomName
     * @param symptomDate
     * @param symptomDescription
     * @param oldName
     */
    public void updateSymptom(String symptomName, String symptomDate, String symptomDescription, String oldName) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(symptom_name_col, symptomName);
        values.put(datetime_col, symptomDate);
        values.put(description_col, symptomDescription);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(Table_Name, values, "symptom_name=?", new String[]{oldName});
        db.close();
    }

    /**
     * Read the symptoms from the db.
     * @return
     */
    public ArrayList<SymptomModal> readSymptoms() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorSymptoms = db.rawQuery("SELECT * FROM " + Table_Name, null);

        // on below line we are creating a new array list.
        ArrayList<SymptomModal> SymptomModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorSymptoms.moveToFirst()) {
            do {

                // on below line we are adding the data from cursor to our array list.
                SymptomModalArrayList.add(new SymptomModal(cursorSymptoms.getString(1),
                        cursorSymptoms.getString(2), cursorSymptoms.getString(3)));

            } while (cursorSymptoms.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorSymptoms.close();
        return SymptomModalArrayList;
    }

    /**
     * check if the db already has a table.
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
