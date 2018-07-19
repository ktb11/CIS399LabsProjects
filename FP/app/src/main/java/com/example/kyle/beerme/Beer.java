package com.example.kyle.beerme;

public class Beer {
    private String beerName;
    private String breweryName;
    private String date;

    public Beer(String beerName, String breweryName, String date){
        this.beerName = beerName;
        this.breweryName = breweryName;
        this.date = date;
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
}
