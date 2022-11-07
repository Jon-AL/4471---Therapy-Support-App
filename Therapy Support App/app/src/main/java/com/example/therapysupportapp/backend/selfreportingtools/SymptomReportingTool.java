package com.example.therapysupportapp.backend.selfreportingtools;

import java.util.TreeMap;
import java.time.LocalDateTime;
//import some kind of date and time object


class SymptomReportingTool {
    private static SymptomReportingTool instance;
    private Data data;

    private String name;
    private TreeMap<LocalDateTime, Integer> reports; // self reports;  //TODO: change Object to whatever type of date and time object we pick.

    private SymptomReportingTool(String value) {
        this.value = value;
    }

    public static SymptomReportingTool getInstance(String value) {
        SymptomReportingTool result = instance;
        if (result != null) {
            return result;
        }
        synchronized (SymptomReportingTool.class) {
            if (instance == null) {
                instance = new SymptomReportingTool(value);
            }
            return instance;
        }

    }


}
