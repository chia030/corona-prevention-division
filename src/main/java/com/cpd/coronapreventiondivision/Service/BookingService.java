package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.CoronaPreventionDivisionApplication;
import com.cpd.coronapreventiondivision.Model.*;
import com.cpd.coronapreventiondivision.Repository.AppointmentRepo;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import com.cpd.coronapreventiondivision.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    CenterRepo centerRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    public List<Center> fetchCenterByType(String type){
        return centerRepo.fetchByType(type);
    }

    public String fetchCenterGoogleMapsLinkById(int id){
        return centerRepo.fetchById(id).getAddress().getGoogleMapsLink();
    }

    public boolean verifyEmail(String approvalID){
        try {
            patientRepo.verifyEmail(approvalID);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public int sendConfirmation(Long cpr, String email, String firstName, String lastName){
        List<Patient> p = patientRepo.fetch(cpr, email, firstName, lastName);

        if(p.size() > 0){
            Patient patient = p.get(0);

            if (patient.isApproved() && patient.getEmailAddress().equals(email)){
                return 0;
            }
            else {
                String toHash = email + new Date();
                String approvalID = User.hash(toHash);
                patient.setApprovalID(approvalID);
                patientRepo.setApproval(cpr, approvalID, email);
                boolean res = CoronaPreventionDivisionApplication.emailhandler.sendConfirmation(email, approvalID, firstName);
                if (res)
                    return 1;
                else return -1;
            }
        }

        return -2;
    }

    public String fetchNumberOfAvailableSpots(int centerid, String date){
        WorkWeek workWeek = centerRepo.fetchById(centerid).getWeekday();
        int capacity = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));

        switch(c.get(Calendar.DAY_OF_WEEK)){
            case 1:
                capacity = workWeek.getSunday().getCapacity();
                break;
            case 2:
                capacity = workWeek.getMonday().getCapacity();
                break;
            case 3:
                capacity = workWeek.getTuesday().getCapacity();
                break;
            case 4:
                capacity = workWeek.getWednesday().getCapacity();
                break;
            case 5:
                capacity = workWeek.getThursday().getCapacity();
                break;
            case 6:
                capacity = workWeek.getFriday().getCapacity();
                break;
            case 7:
                capacity = workWeek.getSaturday().getCapacity();
                break;
            default:
                break;
        }
        return String.valueOf(capacity - appointmentRepo.fetchNumberOfAvailableSpots(centerid, date));
    }
}
