package com.example.therapysupportapp.backend.symptomdata;

import android.provider.BaseColumns;
import android.database.sqlite.*;
public final class SymptomDatabaseInterface {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SymptomDatabaseInterface() {}

    /* Inner class that defines the table contents */
    public static class ColumnNames implements BaseColumns {
        public static final String TABLE_NAME = "symptoms";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATe = "date";
        public static final String COLUMN_NAME_INTENSITY = "intensity";
    }
}


