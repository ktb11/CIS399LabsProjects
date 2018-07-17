package com.example.kyle.lab6v2;

public class TideItem {

    private String date = null;
    private String day = null;
    private String time = null;
    private String predValueFt = null;
    private String predValueCm = null;
    private String highLow = null;


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
    public String getPredValueFt() {
        return predValueFt;
    }

    public void setPredValueFt(String predValueFt) {
        this.predValueFt = predValueFt;
    }
    public String getPredValueCm() {
        double dataFt = Integer.parseInt(getPredValueFt());
        double dataCm = dataFt*30.48;
        predValueCm = Double.toString(dataCm);
        return predValueCm;
    }

    public void setPredValueCm(String predValueCm) {
        this.predValueCm = predValueCm;
    }

    public String getHighLow() {
        return highLow;
    }
    public void setHighLow(String highLow) {
        this.highLow = highLow;
    }


}
