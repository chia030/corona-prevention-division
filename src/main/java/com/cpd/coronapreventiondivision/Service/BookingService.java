package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.CoronaPreventionDivisionApplication;
import com.cpd.coronapreventiondivision.Model.*;
import com.cpd.coronapreventiondivision.Repository.AppointmentRepo;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import com.cpd.coronapreventiondivision.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Center fetchCenterById(int centerid){
        return centerRepo.fetchById(centerid);
    }

    public ArrayList<Times> fetchTimes(int id, int dayOfWeek){
        Center c = centerRepo.fetchById(id);
        WorkDay d = c.getWeekday().getDay(dayOfWeek);
        ArrayList<Times> times = new ArrayList<>();
        int capacity = d.getCapacity();
        int start = d.getOpeningTime().getMinute() + 60*d.getOpeningTime().getHour(); //in minutes
        int end = d.getClosingTime().getMinute() + 60*d.getClosingTime().getHour(); //in minutes
        int interval = d.getInterval(); //in minutes

        for(int i = start; i < end; i += interval){
            Times time = new Times(String.valueOf(i), String.valueOf(i+interval), String.valueOf(capacity));
            times.add(time);
        }

        return times;
    }

    public String fetchNumberOfAvailableSpots(int centerid, String date, int dayOfWeek){
        WorkWeek workWeek = centerRepo.fetchById(centerid).getWeekday();
        int capacity = 0;

        switch(dayOfWeek){
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
