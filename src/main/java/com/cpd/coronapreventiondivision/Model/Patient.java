package com.cpd.coronapreventiondivision.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Patient {

    private long CPR;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private boolean isApproved;
    private String approvalID;

    public Patient() {}

    public Patient(long CPR, String emailAddress, String firstName, String lastName, boolean isApproved, String approvalID) {
        this.CPR = CPR;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isApproved = isApproved;
        this.approvalID = approvalID;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "CPR=" + CPR +
                ", emailAddress='" + emailAddress + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }

    public String getApprovalID() {
        return approvalID;
    }

    public void setApprovalID(String approvalID) {
        this.approvalID = approvalID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getCPR() {
        return CPR;
    }

    public void setCPR(long CPR) {
        this.CPR = CPR;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

}

