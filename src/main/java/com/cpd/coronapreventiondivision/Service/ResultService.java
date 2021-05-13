package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Repository.AppointmentRepo;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import com.cpd.coronapreventiondivision.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    PatientRepo patientRepo;

    public String fetchResultByIdAndCpr(int appointmentID, long cpr){
        return appointmentRepo.fetchByIdAndCpr(appointmentID, cpr).getResult().toString();
    }

    public Patient fetchPatientByCpr(long cpr){
        return patientRepo.fetchByCpr(cpr);
    }
}