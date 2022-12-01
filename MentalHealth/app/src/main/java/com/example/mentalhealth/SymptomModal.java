package com.example.mentalhealth;

/**
 * SymptomModal is a class that will contain and get the information for symptoms.
 */

public class SymptomModal {

    int id;
    String name, description, date;

    /**
     * Constructor for symptom
     * @param name
     * @param description
     * @param date
     */
    public SymptomModal (String name, String description, String date){
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /**
     * Getter for date.
     * @return date string
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Setter for date.
     * @param date
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * getter for id
     * @return
     */
    public int getId(){
        return this.id;
    }

    /**
     * Setter for id
     *
     **/
    public void setId(int id){
        this.id = id;
    }

    /**
     * getter for name
     * @return name string
     */
    public String getName(){
        return this.name;
    }

    /**
     * setter for name.
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Getter for description
     * @return description string
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Setter for description
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
}
