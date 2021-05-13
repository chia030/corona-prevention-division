package com.cpd.coronapreventiondivision.Model;

public class WeekDay {

    private WorkDay monday;
    private WorkDay tuesday;
    private WorkDay wednesday;
    private WorkDay thursday;
    private WorkDay friday;
    private WorkDay saturday;
    private WorkDay sunday;

    public WeekDay(WorkDay monday, WorkDay tuesday, WorkDay wednesday, WorkDay thursday, WorkDay friday, WorkDay saturday, WorkDay sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public WorkDay getMonday() {
        return monday;
    }

    public void setMonday(WorkDay monday) {
        this.monday = monday;
    }

    public WorkDay getTuesday() {
        return tuesday;
    }

    public void setTuesday(WorkDay tuesday) {
        this.tuesday = tuesday;
    }

    public WorkDay getWednesday() {
        return wednesday;
    }

    public void setWednesday(WorkDay wednesday) {
        this.wednesday = wednesday;
    }

    public WorkDay getThursday() {
        return thursday;
    }

    public void setThursday(WorkDay thursday) {
        this.thursday = thursday;
    }

    public WorkDay getFriday() {
        return friday;
    }

    public void setFriday(WorkDay friday) {
        this.friday = friday;
    }

    public WorkDay getSaturday() {
        return saturday;
    }

    public void setSaturday(WorkDay saturday) {
        this.saturday = saturday;
    }

    public WorkDay getSunday() {
        return sunday;
    }

    public void setSunday(WorkDay sunday) {
        this.sunday = sunday;
    }
}
