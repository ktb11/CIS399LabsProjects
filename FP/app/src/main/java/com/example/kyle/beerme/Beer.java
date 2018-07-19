package com.example.kyle.beerme;

public class Beer {
    private String beerName;
    private String breweryName;
    private String date;
    private String type;

    public Beer(String beerName, String breweryName, String date, String type){
        this.beerName = beerName;
        this.breweryName = breweryName;
        this.date = date;
        this.type = type;
    }

    public String getBeerName() {
        return beerName;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
