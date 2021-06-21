package com.cpd.coronapreventiondivision.Service;


import com.cpd.coronapreventiondivision.CoronaPreventionDivisionApplication;
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
public class ReportService {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    CenterRepo centerRepo;

    @Autowired
    PatientRepo patientRepo;

    public void updateOldBooked() { appointmentRepo.updateOldBooked(); }

    public List<Patient> fetchVaccinated() { return patientRepo.fetchPartialVaccinated(); }

    public List<Appointment> fetchAllAppointments() {
        return appointmentRepo.fetchAll();
    }

    public List<Center> fetchAllCenters() { return centerRepo.fetchAll(); }

    public List<Appointment> fetchBooked() { return appointmentRepo.fetchTodayBooked(); }

    public List<Appointment> fetchBookedByCpr(long cpr) { return appointmentRepo.fetchBookedByCpr(cpr); }

    public List<Appointment> fetchBookedByCenter(int centerID) { return appointmentRepo.fetchBookedByCenter(centerID); }

    public List<Appointment> fetchBookedByCprAndCenter(long cpr, int centerID) { return appointmentRepo.fetchBookedByCprAndCenter(cpr, centerID); }

    public List<Appointment> fetchByCenter(int centerID) {
        return appointmentRepo.fetchByCenter(centerID);
    }

    public Appointment fetchByID(int appointmentID) { return appointmentRepo.fetchById(appointmentID); }

    public List<Appointment> fetchByCprAndCenter(long cpr, int centerID) { return appointmentRepo.fetchByCprAndCenter(cpr,centerID); }

    public List<Appointment> fetchByCpr(long cpr) { return appointmentRepo.fetchByCpr(cpr); }

    public boolean updateAppointment(Appointment.Result status, int appointmentID) {

        boolean updated = appointmentRepo.updateAppointment(status, appointmentID);

        if (updated) {
            CoronaPreventionDivisionApplication.emailhandler.sendResults(String.valueOf(appointmentID), "patient");
            System.out.println("email sent to patient");
        }

        return updated;

    }

}
