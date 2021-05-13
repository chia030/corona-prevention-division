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

    @GetMapping("/")
    public String landingPage(){
        return "landing page";
    }

    @GetMapping("/book-a-test")
    public String selectTestCenter(Model model){
        List<Center> testCenters = bookingService.fetchCenterByType("PCR_TEST");
        model.addAttribute("testCenters", testCenters);
        System.out.println(model);

        return "book-a-test";
    }

}
