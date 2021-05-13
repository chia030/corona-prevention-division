package com.cpd.coronapreventiondivision.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses" )
public class Address {

    @Id
    private int addressID;
    private String city;
    private int postCode;
    private String streetName;
    private String streetNumber;
    private String floor;

    public Address() {}

    public Address(int addressID, String city, int postCode, String streetName, String streetNumber, String floor) {
        this.addressID = addressID;
        this.city = city;
        this.postCode = postCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", city='" + city + '\'' +
                ", postCode=" + postCode +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
