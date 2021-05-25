package com.cpd.coronapreventiondivision.Model;

public class Center {

    private int centerID;
    private CenterType centerType;
    private Address address; //add the IDs here
    private WorkWeek workWeek;

    public enum CenterType {
        PCR_TEST,
        MODERNA_VACCINE,
        COMIRNATY_VACCINE,
        UNKNOWN
    }

    public Center() {}

    public Center(int centerID, CenterType centerType, Address address, WorkWeek workWeek) {
        this.centerID = centerID;
        this.centerType = centerType;
        this.address = address;
        this.workWeek = workWeek;
    }

    @Override
    public String toString() {
        return "Center{" +
                "centerID=" + centerID +
                ", centerType=" + centerType +
                ", address=" + address +
                ", workDays=" + workWeek +
                '}';
    }

    public int getCenterID() {
        return centerID;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public WorkWeek getWorkWeek() {
        return workWeek;
    }

    public void setWorkWeek(WorkWeek workWeek) {
        this.workWeek = workWeek;
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
        return workWeek;
    }

    public void setWorkDays(WorkWeek workWeek) {
        this.workWeek = workWeek;
    }
}