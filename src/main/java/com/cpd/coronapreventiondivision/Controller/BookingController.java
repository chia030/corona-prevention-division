package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/CPD.dk")
    public String home(){ return "booking/home"; }

    @GetMapping("/CPD.dk/pcr-test")
    public String testBooking(Model model){
        List<Center> testCenters = bookingService.fetchCenterByType("PCR_TEST");
        model.addAttribute("testCenters", testCenters);
        System.out.println(model);
        return "booking/test";
    }
    @GetMapping("/CPD.dk/vaccine")
    public String vaccineBooking(Model model){
        List<Center> vaccineCenters1 = bookingService.fetchCenterByType("COMIRNATY_VACCINE");
        List<Center> vaccineCenters2 = bookingService.fetchCenterByType("MODERNA_VACCINE");
        model.addAttribute("vaccineCenters1", vaccineCenters1);
        model.addAttribute("vaccineCenters2", vaccineCenters2);
        System.out.println(model);
        return "booking/vaccine";
    }

    @GetMapping("/CPD.dk/locations")
    public String locations(){ return "booking/locations"; }

    @GetMapping("/CPD.dk/info")
    public String info(){ return "booking/info"; }


}
