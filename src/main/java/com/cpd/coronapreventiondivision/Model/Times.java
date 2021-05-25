package com.cpd.coronapreventiondivision.Model;

public class Times{
    private String timeStart;
    private String timeEnd;
    private String available;

    public Times(String timeStart, String timeEnd, String available){
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.available = available;
    }

    public String getTimeStart(){
        return timeStart;
    }

    public String getTimeEnd(){
        return timeEnd;
    }

    public String getAvailable(){
        return available;
    }
}