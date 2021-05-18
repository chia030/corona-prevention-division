package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("confirm-booking")
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

    @PostMapping("get-available-count")
    public String getAvailableCount(int centerid, String date){
        System.out.println("I'm in getAvailableCount!!");
        return String.valueOf(bookingService.fetchNumberOfAvailableSpots(centerid, date));
    }

    @GetMapping("/")
    public String home(){ return "index"; }

    @GetMapping("/test")
    public String testBooking( Model model){
        List<Center> testCenters = bookingService.fetchCenterByType("PCR_TEST");
        model.addAttribute("title", "Book a PCR Test appointment");
        model.addAttribute("centers", testCenters);
        model.addAttribute("selectedCenter", testCenters.get(3));

        return "booking/booking";
    }
    @GetMapping("/vaccine")
    public String vaccineBooking(Model model){
        List<Center> vaccineCenters1 = bookingService.fetchCenterByType("MODERNA_VACCINE");
        List<Center> vaccineCenters = bookingService.fetchCenterByType("COMIRNATY_VACCINE");
        vaccineCenters.addAll(vaccineCenters1);
        model.addAttribute("title", "Book a vaccine shot appointment");
        model.addAttribute("centers", vaccineCenters);

        return "booking/booking";
    }

    @GetMapping("/locations")
    public String locations(){ return "booking/locations"; }

}
