package com.example.therapysupportapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SymptomDBHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "Symptoms";
    private static final int DB_version = 2;

    private static final String Table_Name = "symptoms";
    private static final String id_col = "id";
    private static final String symptom_name_col = "symptom_name";
    private static final String description_col = "description";

    public SymptomDBHelper(Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_Name + " ("
                + id_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + symptom_name_col + " TEXT,"
                + description_col +  " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    public void addNewMedication(String id, String symptomName, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Create the values
        values.put(id_col, id);
        values.put(symptom_name_col, symptomName);
        values.put(description_col, description);

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
