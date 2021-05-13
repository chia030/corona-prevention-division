package com.cpd.coronapreventiondivision.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments" )
public class Appointment {

    @Id
    private int appointment_id;
    private Result result;
    private LocalDate date;
    private LocalTime time;
    private long CPR;
    private Center center; //add center ID here
    private String patientEmail;


    public Appointment() {}

    public Appointment(Result result, LocalDate date, LocalTime time, long CPR, Center center, String patientEmail) {
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

    public long getCPR() {
        return CPR;
    }

    public void setCPR(long CPR) {
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
