package com.cpd.coronapreventiondivision.Model;

public class Address {

    private int id;
    private String city;
    private int postCode;
    private String streetName;
    private String streetNumber;
    private String floor;
    private String googleMapsLink;

    public Address() {
        city = null;
        postCode = 0;
        streetName = null;
        streetNumber = null;
        floor = null;
        googleMapsLink = null;
    }

    public Address(String city, int postCode, String streetName, String streetNumber, String floor, String googleMapsLink) {
        this.city = city;
        this.postCode = postCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.floor = floor;
        this.googleMapsLink = googleMapsLink;
    }

    public Address(int id, String city, int postCode, String streetName, String streetNumber, String floor, String googleMapsLink) {
        this.id = id;
        this.city = city;
        this.postCode = postCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.floor = floor;
        this.googleMapsLink = googleMapsLink;
    }

    @Override
    public String toString() {
        return  city + " " + postCode + ", " + streetName + " " +
                " " + streetNumber + " " + floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoogleMapsLink() {
        return googleMapsLink;
    }

    public void setGoogleMapsLink(String googleMapsLink) {
        this.googleMapsLink = googleMapsLink;
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
