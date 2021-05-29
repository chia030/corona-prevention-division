package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Times;
import com.cpd.coronapreventiondivision.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/email-verification")
    public String verifyEmail(@RequestParam(name = "id") String verificationCode, Model model){
        boolean isVerified = bookingService.verifyEmail(verificationCode);

        if (isVerified)
            model.addAttribute("message", "Your email is now verified. Please return to the previous page to finish the booking.");
        else
            model.addAttribute("message", "Oh no! There was an issue while verifying your email. Please try again later.");

        return "booking/clue";
    }

    @PostMapping("confirm-booking")
    public String sendConfirmation(@RequestParam(required = false) Integer center,
                                   @RequestParam(required = false) Integer year,
                                   @RequestParam(required = false) Integer month,
                                   @RequestParam(required = false) Integer day,
                                   @RequestParam(required = false) String time,
                                   @RequestParam(required = false) Long cpr,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName,
                                   Model model){
        System.out.println("Booking appointment - centerid: " + center +
                           " date: " + year + "-" + month + "-" + day +
                           " time: " + time +
                           " cpr: " + cpr +
                           " email: " + email +
                           " name: " + firstName + " " + lastName);



        if (cpr != null && email != null && firstName != null && lastName != null){
            int emailSent = bookingService.sendConfirmation(cpr, email, firstName, lastName);

            String m = String.valueOf(month);
            String d = String.valueOf(day);
            if(month < 10) m = "0" + m;
            if(day < 10) d = "0" + d;
            String date = year + "-" + m + "-" + d;

            if (emailSent == 1) {
                model.addAttribute("idV", "");
                model.addAttribute("booked", 0);
                model.addAttribute("outcome", "A confirmation email has been sent to your inbox. Please verify it to finish your booking.");
            }
            else if (emailSent == 0){
                //Creating and inserting appointment
                String id = bookingService.bookAppointment(cpr, date, time, center, email);
                model.addAttribute("idV", id);
                model.addAttribute("booked", 1);
                model.addAttribute("outcome", "Your appointment has been booked");
            }
            else {
                return "booking/booking";
            }

            //Unseen data
            model.addAttribute("center", center);
            model.addAttribute("year", year);
            model.addAttribute("month", month);
            model.addAttribute("day", day);
            model.addAttribute("time", time);
            model.addAttribute("cpr", cpr);
            model.addAttribute("email", email);
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);

            //Visible data
            model.addAttribute("dateV", date);
            model.addAttribute("timeV", time);
            model.addAttribute("cprV", cpr);
            model.addAttribute("nameV", firstName + " " + lastName);
            model.addAttribute("emailV", email);
            model.addAttribute("locationV", bookingService.fetchCenterById(center).getAddress().toString());
        }
        else {
            return "booking/booking";
        }

        return "booking/booking-overview";
    }

    @PostMapping("get-google-maps-link")
    @ResponseBody
    public String getGoogleMapsLink(int centerid){
        try {
            return bookingService.fetchCenterById(centerid).getAddress().getGoogleMapsLink();
        }
        catch(Exception e){
            return "https://google.com/";
        }
    }

    @PostMapping("get-available-times")
    @ResponseBody
    public ArrayList<Times> getAvailableTimes(Integer centerid, String date, Integer dayOfWeek) {
        return bookingService.fetchTimes(centerid, date, dayOfWeek);
    }

    @PostMapping("get-available-days")
    @ResponseBody
    public ArrayList<String> getAvailableDays(Integer centerid, Integer year, Integer month, Integer dayOfWeek, Integer dayNum){
        return bookingService.fetchDays(centerid, year, month, dayOfWeek, dayNum);
    }

    @GetMapping("/")
    public String home(){ return "index"; }

    @GetMapping("/test")
    public String testBooking(Model model){
        List<Center> testCenters = bookingService.fetchCenterByType("PCR_TEST");
        model.addAttribute("title", "Book a PCR Test appointment");
        model.addAttribute("centers", testCenters);

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
}
