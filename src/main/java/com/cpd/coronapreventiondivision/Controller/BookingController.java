package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/email-verification")
    public RedirectView verifyEmail(@RequestParam(name = "id") String verificationCode){
        bookingService.verifyEmail(verificationCode);

        return new RedirectView("/");
    }

    @GetMapping("send-confirmation")
    public String sendConfirmation(@RequestParam(required = false) Long cpr,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName,
                                   Model model){
        if (cpr != null && email != null && firstName != null && lastName != null){
            int emailSent = bookingService.sendConfirmation(cpr, email, firstName, lastName);
            model.addAttribute("outcome", emailSent);
        }
        else {
            model.addAttribute("outcome", -3);
        }

        return "index";
    }

    @GetMapping("/CPD.dk")
    public String home(){ return "booking/home"; }

    @GetMapping("/CPD.dk/pcr-test")
    public String testBooking(Model model){
        List<Center> testCenters = bookingService.fetchCenterByType("PCR_TEST");
        model.addAttribute("centers", testCenters);
        if (centerid != null){
            String googleMapsLink = bookingService.fetchCenterGoogleMapsLinkById(centerid);
            model.addAttribute("googleMapsLink", googleMapsLink);
        }
        return "booking/test";
    }
    @GetMapping("/CPD.dk/vaccine")
    public String vaccineBooking(Model model){
        List<Center> vaccineCenters2 = bookingService.fetchCenterByType("MODERNA_VACCINE");
        List<Center> vaccineCenters1 = bookingService.fetchCenterByType("COMIRNATY_VACCINE");
        List<Center> vaccineCenters = vaccineCenters1.addAll(vaccineCenters2);
        model.addAttribute("centers", vaccineCenters);
        if (centerid != null){
            String googleMapsLink = bookingService.fetchCenterGoogleMapsLinkById(centerid);
            model.addAttribute("googleMapsLink", googleMapsLink);
        }
        return "booking/test";
    }

    @GetMapping("/CPD.dk/locations")
    public String locations(){ return "booking/locations"; }

    @GetMapping("/CPD.dk/info")
    public String info(){ return "booking/info"; }

}
