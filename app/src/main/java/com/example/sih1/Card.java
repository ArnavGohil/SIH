package com.example.sih1;

public class Card
{
    private String description , date ;
    int type;

    public Card(String desc , int type , String date )
    {
        description = desc ;
        this.type = type ;
        this.date = date ;
    }

    public int getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
