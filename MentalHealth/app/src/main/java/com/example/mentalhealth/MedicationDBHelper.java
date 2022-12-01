package com.example.mentalhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 *
 * Medication class.
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
 * 3. https://www.folkstalk.com/2022/09/delete-data-from-database-sqlite-android-with-code-examples.html
 * 4. https://www.digitalocean.com/community/tutorials/android-sqlite-database-example-tutorial
 * */


public class MedicationDBHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "Med";
    private static final int DB_version = 3;

    private static final String Table_Name = "medications";
    private static final String ID_COL  = "id";
    private static final String medicationName_COL = "MED_NAME";
    private static final String brandName_COL = "brandName";
    private static final String dosageQuantity_COL = "dosage_quantity";
    private static final String dosageUnit_COL = "dosageUnit";
    private static final String frequency_COL = "frequency";

    /**
     * Initalize the sontructor to get a specific database and context.
     * @param context
     */
    public MedicationDBHelper( Context context) {
        super(context, DB_Name, null, DB_version);
    }

    /**
     * Initial the table inside the db with its respective attributes.
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_Name + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + medicationName_COL + " TEXT,"
                + brandName_COL + " TEXT,"
                + dosageQuantity_COL + " TEXT,"
                + dosageUnit_COL + " Text,"
                + frequency_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    /**
     * Add a new medication
     * @param commonName
     * @param medName
     * @param dosageUnit
     * @param dosageQuantity
     * @param frequency
     */
    public void addNewMedication( String commonName, String medName, String dosageUnit, String dosageQuantity, String frequency){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Create the values
        values.put(medicationName_COL, medName);
        values.put(brandName_COL, commonName);
        values.put(dosageQuantity_COL, dosageQuantity);
        values.put(dosageUnit_COL, dosageUnit);
        values.put(frequency_COL, frequency);


        // Store the values into the database table.
        db.insert(Table_Name, null, values);

        // close the database.
        db.close();
    }

    /**
     * Delete a record from the db.
     * @param medName
     */
    public void deleteRecord(String medName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Name,  "medicationName=?" , new String[]{medName});
        db.close();
    }

    /**
     * Update the record with the specific information with respect to its old key oldname.
     * @param oldName
     * @param medName
     * @param dosage
     * @param dosageUnit
     * @param commonName
     * @param frequency
     */
    public void updateMedication(String oldName, String medName, String dosage, String dosageUnit, String commonName, String frequency ) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(medicationName_COL, medName);
        values.put(brandName_COL, commonName);
        values.put(dosageQuantity_COL, dosage);
        values.put(dosageUnit_COL, dosageUnit);
        values.put(frequency_COL, frequency);



        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(Table_Name, values, "medicationName=?", new String[]{oldName});
        db.close();
    }

    /**
     * Read all medication records from the db and store it inside an arraylist of MedicationModal objects.
     * @return
     */
    public ArrayList<MedicationModal> readMedications() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorMedications = db.rawQuery("SELECT * FROM " + Table_Name, null);

        // on below line we are creating a new array list.
        ArrayList<MedicationModal> MedicationModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorMedications.moveToFirst()) {
            do {

                // on below line we are adding the data from cursor to our array list.
                MedicationModalArrayList.add(new MedicationModal(cursorMedications.getString(1), cursorMedications.getString(2), cursorMedications.getString(3), cursorMedications.getString(4), cursorMedications.getString(5)));

            } while (cursorMedications.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorMedications.close();
        return MedicationModalArrayList;
    }

    /**
     * Check if the database has been updated.
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
