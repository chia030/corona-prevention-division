package com.cpd.coronapreventiondivision.Model;


public class Center {

    private int centerID;
    private CenterType centerType;
    private String addressID;
    private Address address; //add the IDs here
    private WeekDay weekday;

    private enum CenterType {
        PCR_TEST,
        MODERNA_VACCINE,
        COMIRNATY_VACCINE
    }

    public Center() {}

    public Center(int centerID, CenterType centerType, Address address, WeekDay weekday) {
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

    public WeekDay getWorkDays() {
        return weekday;
    }

    public void setWorkDays(WeekDay weekday) {
        this.weekday = weekday;
    }
}
