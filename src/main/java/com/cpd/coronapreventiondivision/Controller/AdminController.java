package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Address;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Model.WorkWeek;
import com.cpd.coronapreventiondivision.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public String homeAdmin(@RequestParam(name = "user", value="user", required = false) User user, Model model) {
        //Unauthorized entrance
//        if (user == null) {
//            return "redirect:/";
//        }
//
//        model.addAttribute("user", user);

        //Adding a list of all centers
        System.out.println("Hello im in admin page");
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

        model.addAttribute("title", "Declare a new center");
        model.addAttribute("center", new Center(-1, Center.CenterType.UNKNOWN, new Address(), new WorkWeek()));

        return "admin/center";
    }

    @RequestMapping(value = "/update-center", method = RequestMethod.POST, params = "remove")
    public String removeCenter(@RequestParam Integer remove){
        adminService.removeCenter(remove);

        return "redirect:/admin";
    }

    @PostMapping("/update-center")
    public String updateCenter(@RequestParam(required = false) Integer centerid, Model model) {
        if (centerid == null) {
            return "redirect:/admin";
        }

        Center center = adminService.fetchCenterById(centerid);

        //Administrator selects to update a center,
        //and is redirected to a form page with current center
        //info filled in. They can then manipulate the form
        //before submitting for an update

        model.addAttribute("title", "Edit selected center");
        model.addAttribute("center", center);

        return "admin/center";
    }

    @PostMapping("/submit-center")
    public String submitCenter(@RequestParam Integer centerid, @RequestParam String centerType,
                               /*Address*/ @RequestParam String city, @RequestParam Integer postCode, @RequestParam String streetName, @RequestParam String streetNumber, @RequestParam String floor, @RequestParam String googleMapsLink,
                               /*Monday*/ @RequestParam String mOpeningTime, @RequestParam String mClosingTime, @RequestParam Integer mInterval, @RequestParam Integer mCapacity,
                               /*Tuesday*/ @RequestParam String tOpeningTime, @RequestParam String tClosingTime, @RequestParam Integer tInterval, @RequestParam Integer tCapacity,
                               /*Wednesday*/ @RequestParam String wOpeningTime, @RequestParam String wClosingTime, @RequestParam Integer wInterval, @RequestParam Integer wCapacity,
                               /*Thursday*/ @RequestParam String thOpeningTime, @RequestParam String thClosingTime, @RequestParam Integer thInterval, @RequestParam Integer thCapacity,
                               /*Friday*/ @RequestParam String fOpeningTime, @RequestParam String fClosingTime, @RequestParam Integer fInterval, @RequestParam Integer fCapacity,
                               /*Saturday*/ @RequestParam String sOpeningTime, @RequestParam String sClosingTime, @RequestParam Integer sInterval, @RequestParam Integer sCapacity,
                               /*Sunday*/ @RequestParam String suOpeningTime, @RequestParam String suClosingTime, @RequestParam Integer suInterval, @RequestParam Integer suCapacity){
        Center center = adminService.fetchCenterById(centerid);
        Center.CenterType type = Center.CenterType.valueOf(centerType);
        if (streetNumber.equals("")) streetNumber = null;
        if (floor.equals("")) floor = null;
        if (googleMapsLink.equals("")) googleMapsLink = null;
        Address address = adminService.fetchAddress(city, postCode, streetName, streetNumber, floor, googleMapsLink);
        if (address == null){
            address = new Address(city, postCode, streetName, streetNumber, floor, googleMapsLink);
            address.setId(adminService.insertAddress(address));
        }
        if (center == null){
            //Create a new center

            WorkWeek week = adminService.getWorkWeek(
                    mOpeningTime, mClosingTime, mInterval, mCapacity,
                    tOpeningTime, tClosingTime, tInterval, tCapacity,
                    wOpeningTime, wClosingTime, wInterval, wCapacity,
                    thOpeningTime, thClosingTime, thInterval, thCapacity,
                    fOpeningTime, fClosingTime, fInterval, fCapacity,
                    sOpeningTime, sClosingTime, sInterval, sCapacity,
                    suOpeningTime, suClosingTime, suInterval, suCapacity);

            center = new Center(-1, type, address, week);
            center.setCenterID(adminService.insertCenter(center));
        }
        else {
            //Center exists, update the differences
            adminService.updateCenter(new Center(center.getCenterID(), type,
                    address,
                    adminService.getWorkWeek(
                            mOpeningTime, mClosingTime, mInterval, mCapacity,
                            tOpeningTime, tClosingTime, tInterval, tCapacity,
                            wOpeningTime, wClosingTime, wInterval, wCapacity,
                            thOpeningTime, thClosingTime, thInterval, thCapacity,
                            fOpeningTime, fClosingTime, fInterval, fCapacity,
                            sOpeningTime, sClosingTime, sInterval, sCapacity,
                            suOpeningTime, suClosingTime, suInterval, suCapacity)));
        }

        return "redirect:/admin";
    }
}
