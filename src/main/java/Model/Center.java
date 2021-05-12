package Model;

import java.util.ArrayList;

public class Center {

    private int centerID;
    private CenterType centerType;
    private Address address;
    private ArrayList<WorkDay> workDays;

    public Center() {}

    public Center(int centerID, CenterType centerType, Address address, ArrayList<WorkDay> workDays) {
        this.centerID = centerID;
        this.centerType = centerType;
        this.address = address;
        this.workDays = workDays;
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

    public ArrayList<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(ArrayList<WorkDay> workDays) {
        this.workDays = workDays;
    }
}
