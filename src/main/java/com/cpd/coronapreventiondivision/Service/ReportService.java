package com.cpd.coronapreventiondivision.Service;


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

    public List<Appointment> fetchAll() {
        return appointmentRepo.fetchAll();
    }

    public List<Appointment> fetchBooked() {
        return appointmentRepo.fetchBooked();
    }

    public List<Appointment> fetchResolved() {
        return appointmentRepo.fetchResolved();
    }

    public List<Appointment> fetchByCenter(int centerID) {
        return appointmentRepo.fetchByCenter(centerID);
    }


}
