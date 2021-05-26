package com.cpd.coronapreventiondivision.Service;


import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import org.springframework.stereotype.Service;


import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    CenterRepo centerRepo;

    public void updateOldBooked() { appointmentRepo.updateOldBooked(); }

    public List<Appointment> fetchAllAppointments() {
        return appointmentRepo.fetchAll();
    }

    public List<Center> fetchAllCenters() { return centerRepo.fetchAll(); }

    public List<Appointment> fetchBooked() {
        return appointmentRepo.fetchBooked();
    }

    public List<Appointment> fetchResolved() {
        return appointmentRepo.fetchResolved();
    }

    public List<Appointment> fetchByCenter(int centerID) {
        return appointmentRepo.fetchByCenter(centerID);
    }

    public Appointment fetchByID(int appointmentID) { return appointmentRepo.fetchById(appointmentID); }

    public boolean updateAppointment(Appointment.Result status, int appointmentID) { return appointmentRepo.updateAppointment(status, appointmentID); }

}
