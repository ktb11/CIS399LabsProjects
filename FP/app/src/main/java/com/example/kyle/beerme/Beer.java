package com.example.kyle.beerme;

public class Beer {
    private String beerName;
    private String breweryName;
    private String date;
    private String type;
    private String rating;

    public Beer(String beerName, String breweryName, String date, String type, String rating){
        this.beerName = beerName;
        this.breweryName = breweryName;
        this.date = date;
        this.type = type;
        this.rating = rating;
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

    public String getRating() {
        return rating;
    }
}
