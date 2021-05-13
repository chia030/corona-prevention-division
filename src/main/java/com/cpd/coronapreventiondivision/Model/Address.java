package com.cpd.coronapreventiondivision.Model;

public class Address {

    private int addressID;
    private String city;
    private int postCode;
    private String streetName;
    private String streetNumber;
    private String floor;

    public Address() {}

    public Address(String city, int postCode, String streetName, String streetNumber, String floor) {
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
