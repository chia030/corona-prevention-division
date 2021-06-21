package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Patient;
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



//    @RequestMapping(value = "/secretary", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/secretary")
    public String homeSecretary(Model model) {
        setMissing(missingSet);

//        if (appointmentList == null) {
//            appointmentList = new ArrayList<>();
//        }

        List<Center> centerList = reportService.fetchAllCenters();
        List<Patient> vaccinatedList = reportService.fetchVaccinated();
//        model.addAttribute("appointmentList", appointmentList);
        model.addAttribute("centerList", centerList);
        model.addAttribute("vaccinatedList", vaccinatedList);
        return "logging/secretary-landing";
    }

    @PostMapping("get-center-appointments")
    @ResponseBody
    public List<Appointment> getCenterAppointments(@RequestParam(required = false) long cpr, @RequestParam(required = false)Integer centerid, @RequestParam(required = false) boolean booked) {

        List<Appointment> appointmentList;

        System.out.println(cpr+" "+centerid+" "+booked);

        try {

            //returns only today's booked appointments
            if (booked) {

                if(cpr == 1111111111 && centerid != 0) {
                    appointmentList = reportService.fetchBookedByCenter(centerid);
                }

                else if (cpr != 1111111111 && centerid != 0) {
                    appointmentList = reportService.fetchBookedByCprAndCenter(cpr,centerid);
                }

                else if (cpr != 1111111111) {
                    appointmentList = reportService.fetchBookedByCpr(cpr);
                }

                else {
                    appointmentList = reportService.fetchBooked();
                }

            }

            //returns all
            else {
                if(cpr == 1111111111 && centerid != 0) {
                    appointmentList = reportService.fetchByCenter(centerid);
                }

                else if (cpr != 1111111111 && centerid != 0) {
                    appointmentList = reportService.fetchByCprAndCenter(cpr,centerid);
                }

                else if (cpr != 1111111111) {
                    appointmentList = reportService.fetchByCpr(cpr);
                }

                else {
                    appointmentList = reportService.fetchAllAppointments();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            appointmentList = new ArrayList<>();
        }

        return appointmentList;
    }

    //checks whether the old booked appointments have been set to 'missing'
    public void setMissing(boolean update) {
        if (!update) {
            reportService.updateOldBooked();
            missingSet =  true;
        }
    }

    @PostMapping("update-appointment-report")
    @ResponseBody
    public void updateResult(@RequestParam(required = false) Integer appointment_id, @RequestParam(required = false) String status) {


        reportService.updateAppointment(Appointment.Result.valueOf(status), appointment_id);

    }




}
