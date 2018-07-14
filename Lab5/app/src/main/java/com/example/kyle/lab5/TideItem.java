package com.example.kyle.lab5;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TideItem {

    private String date = null;
    private String day = null;
    private String time = null;
    private String predValue = null;
    private String highLow = null;

    private SimpleDateFormat dateInFormat = new SimpleDateFormat("yyyy/MM/dd");


    public String getTideDateFormatted() {
        try {
            Date formatDate = dateInFormat.parse(date);
            String tideDate = dateInFormat.format(formatDate);
            return tideDate;
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getPredValue() {
        return predValue;
    }

    public void setPredValue(String predValue) {
        this.predValue = predValue;
    }

    public String getHighLow() {
        return highLow;
    }
    public void setHighLow(String highLow) {
        this.highLow = highLow;
    }


}
