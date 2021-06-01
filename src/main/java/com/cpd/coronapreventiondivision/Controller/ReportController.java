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


//     @PostMapping("/secretary")
//     public String homeSecretary(@RequestParam User user) {
//        homeSecretary(LoginController.staticUser);


//    @PostMapping("/home-secretary")
//    public String homeSecretary(@RequestParam User user) {
////        homeSecretary(LoginController.staticUser);
//        return "logging/secretary-landing";
//    }



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

//    @PostMapping("/get-cpr-appointments")
//    @ResponseBody
//    public List<Appointment> getCprAppointments(@RequestParam(required = false) Integer centerid, Long cpr) {
//        try {
//            List <Appointment> appointmentList = reportService.fetchByCenter(centerid);
//            return appointmentList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }


//    @GetMapping("/secretary/appointments")
//    public String displayAppointments (@RequestParam(required=false) int centerid , Model model) {
//
//        List<Appointment> appointmentList = getCenterAppointments(centerid,model);
//        model.addAttribute("appointmentList", appointmentList);
//
//        return "logging/appointments";
//    }

    //checks whether the old booked appointments have been set to 'missing'
    public void setMissing(boolean update) {
        if (!update) {
            reportService.updateOldBooked();
            missingSet =  true;
        }
    }

    @PostMapping("update-appointment-report")
    @ResponseBody
    public void updateResult(@RequestParam(required = false) Integer appointment_id, @RequestParam(required = false) String result) {
        if (result == null || appointment_id == null) {
            return;
        }

        reportService.updateAppointment(Appointment.Result.valueOf(result), appointment_id);

    }




}
