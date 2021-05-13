package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping("/results")
    public String viewResults(@RequestParam(name = "id") int appointmentID,
                              @RequestParam(name = "cpr", required = false) Long cpr,
                              Model model){

        model.addAttribute("appointmentID", appointmentID);
        model.addAttribute("cpr", cpr);

        String result = resultService.fetchResultByIdAndCpr(appointmentID, cpr);

        if(result != null){
            //CPR is correct
            Patient patient = resultService.fetchPatientByCpr(cpr);
            model.addAttribute("patient", patient);
            model.addAttribute("result", result);
            return "results";
        }

        //CPR is incorrect or not specified
        return "resultform";
    }
}
