package Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "centers" )
public class Center {

    @Id
    private int centerID;
    private CenterType centerType;
    private Address address; //add the IDs here
    private ArrayList<WorkDay> workDays; //I think we need to change this to List

    public Center() {}

    public Center(int centerID, CenterType centerType, Address address, ArrayList<WorkDay> workDays) {
        this.centerID = centerID;
        this.centerType = centerType;
        this.address = address;
        this.workDays = workDays;
    }

    @Override
    public String toString() {
        return "Center{" +
                "centerID=" + centerID +
                ", centerType=" + centerType +
                ", address=" + address +
                ", workDays=" + workDays +
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

    public ArrayList<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(ArrayList<WorkDay> workDays) {
        this.workDays = workDays;
    }
}
