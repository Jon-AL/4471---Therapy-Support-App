package com.example.mentalhealth;

public class SymptomModal {
    int id;
    String name, description, date;
    public SymptomModal (String name, String description, String date){
        this.name = name;
        this.description = description;
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;

    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
