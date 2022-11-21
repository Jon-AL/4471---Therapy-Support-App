package com.example.mentalhealth;

public class SymptomModal {
    int id;
    String name, description;
    public SymptomModal (String name, String description){
        this.name = name;
        this.description = description;
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
