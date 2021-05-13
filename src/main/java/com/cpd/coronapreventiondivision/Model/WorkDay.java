package com.cpd.coronapreventiondivision.Model;

import java.time.LocalTime;

public class WorkDay {

    private int workDayID;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private int interval; //In minutes
    private int capacity;

    public WorkDay() {}

    public WorkDay(int workDayID, LocalTime openingTime, LocalTime closingTime, int interval, int capacity) {
        this.workDayID = workDayID;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.interval = interval;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "workDayID=" + workDayID +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", interval=" + interval +
                ", capacity=" + capacity +
                '}';
    }

    public int getWorkDayID() {
        return workDayID;
    }

    public void setWorkDayID(int workDayID) {
        this.workDayID = workDayID;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
