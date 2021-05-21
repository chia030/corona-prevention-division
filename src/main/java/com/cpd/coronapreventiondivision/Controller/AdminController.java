package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public String homeAdmin(@RequestParam(name = "user", required = false) User user, Model model) {
        //Unauthorized entrance
//        if (user == null) {
//            return "redirect:/";
//        }
//
//        model.addAttribute("user", user);

        //Adding a list of all centers
        List<Center> modernaVaccineCenters = adminService.fetchCenterByType("MODERNA_VACCINE");
        List<Center> comirnatyVaccineCenters = adminService.fetchCenterByType("COMIRNATY_VACCINE");
        List<Center> pcrTestCenters = adminService.fetchCenterByType("PCR_TEST");

        List<Center> centers = modernaVaccineCenters;
        centers.addAll(comirnatyVaccineCenters);
        centers.addAll(pcrTestCenters);

        model.addAttribute("centers", centers);

        return "admin/admin-landing";
    }

    @GetMapping("/create-center")
    public String createCenter(Model model){
        //Administrator selects to create a new center,
        //and is redirected to an empty form page to fill
        //out all the info about the center

        model.addAttribute("center", new Center());

        return "admin/center";
    }

    @PostMapping("/update-center")
    public String updateCenter(@RequestParam(required = false) Center center, Model model) {
        if (center == null) {
            return "admin/admin-landing";
        }

        //Adminsitrator selects to update a center,
        //and is redirected to a form page with current center
        //info filled in. They can then manipulate the form
        //before submitting for an update

        model.addAttribute("center", center);

        return "admin/center";
    }
}
