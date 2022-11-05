package com.example.therapysupportapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
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
 * */


public class MedicationDBHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "Medication";
    private static final int DB_version = 1;

    private static final String Table_Name = "medications";
    private static final String ID_COL  = "id";
    private static final String medicationName_COL = "MED_NAME";
    private static final String dosage_COL = "dosage";
    private static final String dosageQuantity_COL = "dosage_quantity";
    private static final String inventory_COL = "inventory";
    private static final String inventoryReminder_COL = "inventory_reminder";
    private static final String history_COL = "history";

    public MedicationDBHelper( Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_Name + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + medicationName_COL + " TEXT,"
                + dosage_COL + " TEXT,"
                + dosageQuantity_COL + " TEXT,"
                + inventory_COL + " Text,"
                + inventoryReminder_COL + " TEXT,"
                + history_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    public void addNewMedication(String id, String medName, String dosage, String dosageQuantity, String inventory, String inventoryReminder, String history){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Create the values
        values.put(ID_COL, id);
        values.put(medicationName_COL, medName);
        values.put(dosage_COL, dosage);
        values.put(dosageQuantity_COL, dosageQuantity);
        values.put(inventory_COL, inventory);
        values.put(inventoryReminder_COL, inventoryReminder);
        values.put(history_COL, history);

        // Store the values into the database table.
        db.insert(Table_Name, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }
}
