package com.cpd.coronapreventiondivision.Model;

public class WorkWeek {

    private int id;
    private WorkDay monday;
    private WorkDay tuesday;
    private WorkDay wednesday;
    private WorkDay thursday;
    private WorkDay friday;
    private WorkDay saturday;
    private WorkDay sunday;

    public WorkWeek(WorkDay monday, WorkDay tuesday, WorkDay wednesday, WorkDay thursday, WorkDay friday, WorkDay saturday, WorkDay sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public WorkWeek(int id, WorkDay monday, WorkDay tuesday, WorkDay wednesday, WorkDay thursday, WorkDay friday, WorkDay saturday, WorkDay sunday) {
        this.id = id;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public WorkWeek(){
        monday = new WorkDay();
        tuesday = new WorkDay();
        wednesday = new WorkDay();
        thursday = new WorkDay();
        friday = new WorkDay();
        saturday = new WorkDay();
        sunday = new WorkDay();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WorkDay getDay(int i){
        switch(i){
            case 0: return monday;
            case 1: return tuesday;
            case 2: return wednesday;
            case 3: return thursday;
            case 4: return friday;
            case 5: return saturday;
            case 6: return sunday;
        }

        return null;
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