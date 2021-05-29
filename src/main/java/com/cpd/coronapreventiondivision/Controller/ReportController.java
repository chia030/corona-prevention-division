package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Service.BookingService;
import com.cpd.coronapreventiondivision.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {
  
    @Autowired
    ReportService reportService;


    private static boolean missingSet = false;


//     @PostMapping("/secretary")
//     public String homeSecretary(@RequestParam User user) {
//        homeSecretary(LoginController.staticUser);


//    @PostMapping("/home-secretary")
//    public String homeSecretary(@RequestParam User user) {
////        homeSecretary(LoginController.staticUser);
//        return "logging/secretary-landing";
//    }



    @GetMapping("/secretary")
    public String homeSecretary(/*@RequestParam(name = "user", value="user", required = false) User user*/ Model model) {
        setMissing(missingSet);
        List<Appointment> appointmentList = new ArrayList<>();
        List<Center> centerList = reportService.fetchAllCenters();
        model.addAttribute("appointmentList", appointmentList);
        model.addAttribute("centerList", centerList);
        return "logging/secretary-landing";
    }

    @PostMapping("/get-center-appointments")
    @ResponseBody
    public List<Appointment> getCenterAppointments(@RequestParam(required = false) Integer centerid, Model model) {
        try {
            List<Appointment> appointmentList = reportService.fetchByCenter(centerid);
            model.addAttribute("appointmentList", appointmentList);
            return appointmentList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/secretary/appointments")
    public String displayAppointments (@RequestParam(required=false) int centerid , Model model) {

        List<Appointment> appointmentList = getCenterAppointments(centerid,model);
        model.addAttribute("appointmentList", appointmentList);

        return "logging/appointments";
    }

    //checks whether the old booked appointments have been set to 'missing'
    public void setMissing(boolean update) {
        if (!update) {
            reportService.updateOldBooked();
            missingSet =  true;
        }
    }

    @PostMapping("/update-appointment-report")
    public String updateResult(@RequestParam(required = false) Integer appointment_id, Model model) {
        if (appointment_id == null) {
            return "redirect:/secretary";
        }

        Appointment appointment = reportService.fetchByID(appointment_id);


        return "redirect:/secretary";
    }

    // TODO: search function, filter for booked and non-booked, date picker?
    // TODO: make the system delete the appointments when a center is deleted
    // TODO: make the 'booked' appointments turn into 'missed' if the status is not changed on that day



}
