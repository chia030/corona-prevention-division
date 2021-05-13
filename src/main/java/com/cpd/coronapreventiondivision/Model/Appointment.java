package com.cpd.coronapreventiondivision.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int appointment_id;
    private Result result;
    private LocalDate date;
    private LocalTime time;
    private Patient CPR;
    private Center center; //add center ID here
    private String patientEmail;

    public enum Result {
        BOOKED,
        MISSED,
        POSITIVE,
        NEGATIVE,
        INCONCLUSIVE,
        PARTIAL_VACCINE,
        VACCINATED
    }

    public Appointment() {}

    public Appointment(Result result, LocalDate date, LocalTime time, Patient CPR, Center center, String patientEmail) {
        this.result = result;
        this.date = date;
        this.time = time;
        this.CPR = CPR;
        this.center = center;
        this.patientEmail = patientEmail;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "result=" + result +
                ", date=" + date +
                ", time=" + time +
                ", CPR=" + CPR +
                ", center=" + center +
                ", patientEmail='" + patientEmail + '\'' +
                '}';
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Patient getCPR() {
        return CPR;
    }

    public void setCPR(Patient CPR) {
        this.CPR = CPR;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }


}
