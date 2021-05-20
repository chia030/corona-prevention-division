package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.CoronaPreventionDivisionApplication;
import com.cpd.coronapreventiondivision.Model.*;
import com.cpd.coronapreventiondivision.Repository.AppointmentRepo;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import com.cpd.coronapreventiondivision.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
        Patient patient;

        if(p.size() > 0){
            patient = p.get(0);
        }
        else {
            patient = new Patient(cpr, email, firstName, lastName, false, null);
            boolean inserted = patientRepo.insert(patient);
            if (!inserted)
                return -2;
        }

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

    public Center fetchCenterById(int centerid){
        return centerRepo.fetchById(centerid);
    }

    public String bookAppointment(long cpr, String date, String time, int centerid, String email){
        Center center = fetchCenterById(centerid);
        Patient patient = patientRepo.fetchByCpr(cpr);
        Appointment appointment = new Appointment(
                Appointment.Result.BOOKED,
                LocalDate.parse(date),
                LocalTime.parse(time),
                patient,
                center,
                email);

        return String.valueOf(appointmentRepo.insert(appointment));
    }

    public ArrayList<Times> fetchTimes(int id, String date, int dayOfWeek){
        Center c = centerRepo.fetchById(id);
        WorkDay d = c.getWeekday().getDay(dayOfWeek);
        ArrayList<Times> times = new ArrayList<>();

        int fullCapacity = d.getCapacity();
        int start = d.getOpeningTime().getMinute() + 60*d.getOpeningTime().getHour(); //in minutes
        int end = d.getClosingTime().getMinute() + 60*d.getClosingTime().getHour(); //in minutes
        int interval = d.getInterval(); //in minutes

        for(int i = start; i < end; i += interval){
            int sh = i/60;
            int sm = i%60;
            int eh = (i+interval)/60;
            int em = (i+interval)%60;
            String s = sh + ":" + (sm < 10 ? "0" + sm : sm);
            String e = eh + ":" + (em < 10 ? "0" + em : em);

            int offset = 120; //Offset in minutes
            int oh = (i-offset)/60;
            int om = (i-offset)%60;
            String o = (oh < 10 ? "0" + oh : oh) + ":" + (om < 10 ? "0" + om : om);

            int bookedAppointments = appointmentRepo.fetchNumberOfBookedAt(id, date, s + ":00");
            int capacity = fullCapacity - bookedAppointments;

            String currentDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
            String currentTime = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            if(capacity < 1 ||
               (currentDate.equals(date) && o.compareTo(currentTime) < 0)){
                continue;
            }

            Times time = new Times(s, e, capacity + " available");
            times.add(time);
        }

        return times;
    }

    public ArrayList<String> fetchDays(Integer centerid, int year, int month, int firstDayOfWeek, int dayCount){
        int dow = firstDayOfWeek - 1;
        if(dow < 0) dow = 6;
        ArrayList<String> days = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        if (centerid == null){
            for(int i = 0; i < dayCount; i++){
                days.add("0");
            }
            return days;
        }

        WorkWeek workWeek = centerRepo.fetchById(centerid).getWeekday();
        for(int i = 0; i < dayCount; i++){
            WorkDay d = workWeek.getDay(dow);

            String count = "0";
            //If the day is null, there's no spots available that day
            if(d != null) {
                int capacity = d.getCapacity();
                int start = d.getOpeningTime().getMinute() + 60 * d.getOpeningTime().getHour(); //in minutes
                int end = d.getClosingTime().getMinute() + 60 * d.getClosingTime().getHour(); //in minutes
                int interval = d.getInterval(); //in minutes

                //If it's today, show only the times that are bookable
                if((cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month && cal.get(Calendar.DAY_OF_MONTH) == i+1)){
                    int  offset = 120; //Offset in minutes
                    int currentTime = cal.get(Calendar.MINUTE) + 60*cal.get(Calendar.HOUR_OF_DAY);
                    int currentMappedTime = currentTime - (currentTime%interval) + interval + offset;
                    start = Math.max(start, currentMappedTime);
                }

                int fullCapacity = capacity * (end - start) / interval;

                String mm = String.valueOf(month+1);
                if(mm.length() == 1) mm = "0" + mm;
                String dd = String.valueOf(i+1);
                if(dd.length() == 1) dd = "0" + dd;
                String date = year + "-" + mm + "-" + dd;

                int oh = start/60;
                int om = start%60;
                String o = (oh < 10 ? "0" + oh : oh) + ":" + (om < 10 ? "0" + om : om) + ":00";

                int bookedAppointments = appointmentRepo.fetchNumberOfBookedAfter(centerid, date, o);
                count = String.valueOf(fullCapacity - bookedAppointments);

                //If the day is before today, or the time is after closing no spots should be available
                if (start > end ||
                    cal.get(Calendar.YEAR) > year ||
                    (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) > month) ||
                    (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month && cal.get(Calendar.DAY_OF_MONTH) > i+1)) {
                    count = "0";
                }
            }

            days.add(count);

            dow++;
            if(dow > 6) dow = 0;
        }

        return days;
    }
}
