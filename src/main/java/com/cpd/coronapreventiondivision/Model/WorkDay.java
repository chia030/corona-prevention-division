package com.cpd.coronapreventiondivision.Model;

import java.time.LocalTime;

public class WorkDay {

    private int id;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private int interval; //In minutes
    private int capacity;

    public WorkDay(LocalTime openingTime, LocalTime closingTime, int interval, int capacity) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.interval = interval;
        this.capacity = capacity;
    }

    public WorkDay(int id, LocalTime openingTime, LocalTime closingTime, int interval, int capacity) {
        this.id = id;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.interval = interval;
        this.capacity = capacity;
    }

    public WorkDay() {
        openingTime = null;
        closingTime = null;
        interval = 0;
        capacity = 0;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", interval=" + interval +
                ", capacity=" + capacity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
