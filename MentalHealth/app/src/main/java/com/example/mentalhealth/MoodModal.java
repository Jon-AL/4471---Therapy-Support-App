package com.example.mentalhealth;

/**
 * MoodModal contains the logic for the mood data.
 *
 */
public class MoodModal {

    private int id;
    private String moodDescription;
    private int moodRating;
    private String date;

    /**
     * getter for mood description
     * @return
     */
    public String getMoodDescription(){
        return this.moodDescription;
    }

    /**
     * Setter for mood description.
     * @param description
     */
    public void setMoodDescription(String description){
        this.moodDescription = description;
    }

    /**
     * getter for mood rating
     * @return
     */
    public int getMoodRating(){
        return this.moodRating;
    }

    /**
     * getter for id
     * @return id int
     */
    public int getId(){
        return this.id;
    }

    /**
     * setter for id.
     * @param diffid
     */
    public void setId(int diffid){
        this.id = diffid;
    }

    /**
     * getter for date
     * @return
     */
    public String getDate(){
        return this.date;
    }

    /**
     * setter for date.
     * @param date
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * Mood constructor.
     * @param rating
     * @param date
     * @param moodDescription
     */
    public MoodModal(int rating, String date, String moodDescription){
        this.moodDescription = moodDescription;
        this.moodRating = rating;
        this.date = date;

    }
}
