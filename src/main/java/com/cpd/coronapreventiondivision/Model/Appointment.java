package com.cpd.coronapreventiondivision.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int appointment_id;
    private Result result;
    private LocalDate date;
    private LocalTime time;
    private Patient patient;
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

    public Appointment(Result result, LocalDate date, LocalTime time, Patient patient, Center center, String patientEmail) {
        this.result = result;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.center = center;
        this.patientEmail = patientEmail;
    }
    public Appointment(int appointment_id, Result result, LocalDate date, LocalTime time, Patient patient, Center center, String patientEmail) {
        this.appointment_id = appointment_id;
        this.result = result;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.center = center;
        this.patientEmail = patientEmail;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "result=" + result +
                ", date=" + date +
                ", time=" + time +
                ", patient=" + patient +
                ", center=" + center +
                ", patientEmail='" + patientEmail + '\'' +
                '}';
    }

    public int getAppointment_id() { return appointment_id; }

    public void setAppointment_id(int appointment_id) { this.appointment_id = appointment_id; }

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient CPR) {
        this.patient = patient;
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
