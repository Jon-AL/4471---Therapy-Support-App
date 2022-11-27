package com.example.mentalhealth;

public class MoodModal {
    private int id;
    private String moodDescription;
    private int moodRating;
    private String date;


    public String getMoodDescription(){
        return this.moodDescription;
    }

    public void setMoodDescription(String description){
        this.moodDescription = description;
    }

    public int getMoodRating(){
        return this.moodRating;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int diffid){
        this.id = diffid;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public MoodModal(int rating, String date, String moodDescription){
        this.moodDescription = moodDescription;
        this.moodRating = rating;
        this.date = date;

    }
}
