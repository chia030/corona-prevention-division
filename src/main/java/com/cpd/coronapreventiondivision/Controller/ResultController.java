package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping("/results")
    public String viewResults(@RequestParam(required = false) Integer id,
                              @RequestParam(required = false) Long cpr,
                              Model model){
        if(id == null){
            return "resultform";
        }

        model.addAttribute("id", id);
        model.addAttribute("cpr", cpr);

        if(cpr != null){
            String result = resultService.fetchResultByIdAndCpr(id, cpr);

            if(result != null){
                //CPR is correct
                Patient patient = resultService.fetchPatientByCpr(cpr);
                String name = patient.getFirstName() + " " + patient.getLastName();

                model.addAttribute("name", name);
                model.addAttribute("result", result);
                return "results";
            }
        }

        //CPR is incorrect or not specified
        return "resultform";
    }
}
