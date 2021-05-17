package com.cpd.coronapreventiondivision.Model;

public class Center {

    private int centerID;
    private CenterType centerType;
    private Address address; //add the IDs here
    private WorkWeek weekday;

    public enum CenterType {
        PCR_TEST,
        MODERNA_VACCINE,
        COMIRNATY_VACCINE
    }

    public Center() {}

    public Center(int centerID, CenterType centerType, Address address, WorkWeek weekday) {
        this.centerID = centerID;
        this.centerType = centerType;
        this.address = address;
        this.weekday = weekday;
    }

    @Override
    public String toString() {
        return "Center{" +
                "centerID=" + centerID +
                ", centerType=" + centerType +
                ", address=" + address +
                ", workDays=" + weekday +
                '}';
    }

    public int getCenterID() {
        return centerID;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public WorkWeek getWeekday() {
        return weekday;
    }

    public void setWeekday(WorkWeek weekday) {
        this.weekday = weekday;
    }

    public CenterType getCenterType() {
        return centerType;
    }

    public void setCenterType(CenterType centerType) {
        this.centerType = centerType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public WorkWeek getWorkDays() {
        return weekday;
    }

    public void setWorkDays(WorkWeek weekday) {
        this.weekday = weekday;
    }
}