package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/")
    public String landingPage(){
        return "index";
    }

    @GetMapping("/book-a-test")
    public String selectTestCenter(@RequestParam(required = false) Integer centerid, Model model){
        List<Center> testCenters = bookingService.fetchCenterByType("PCR_TEST");
        model.addAttribute("centers", testCenters);
        if (centerid != null){
            String googleMapsLink = bookingService.fetchCenterGoogleMapsLinkById(centerid);
            model.addAttribute("googleMapsLink", googleMapsLink);
        }

        return "appointment";
    }

    @PostMapping("send-confirmation")
    public String sendConfirmation(@RequestParam(required = false) Long cpr,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName,
                                   Model model){
        int emailSent = bookingService.sendConfirmation(cpr, email, firstName, lastName);


        return "appointment";
    }
}
