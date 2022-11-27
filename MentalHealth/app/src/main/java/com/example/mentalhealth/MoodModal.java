package com.example.mentalhealth;

public class MoodModal {
    private int id;
    private String moodDescription;
    private String moodRating;



    public String getMoodDescription(){
        return this.moodDescription;
    }

    public void setMoodDescription(String description){
        this.moodDescription = description;
    }

    public String getMoodRating(){
        return this.moodRating;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int diffid){
        this.id = diffid;
    }

    public MoodModal(String rating, String moodDescription){
        this.moodDescription = moodDescription;
        this.moodRating = rating;
    }
}
