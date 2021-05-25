package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReportController {
  
    @Autowired
    ReportService reportService;


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
        List<Appointment> appointmentList = reportService.fetchAll();
        model.addAttribute("appointmentList", appointmentList);
        return "logging/secretary-landing";
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
