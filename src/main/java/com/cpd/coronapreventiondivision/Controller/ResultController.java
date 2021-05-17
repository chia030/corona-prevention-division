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
    public String viewResultForm(@RequestParam(value = "id") Integer id, Model model){
        model.addAttribute("id", id);

        return "resultform";
    }

    @PostMapping("/results")
    public String viewResults(@RequestParam(value = "id") int id,
                              @RequestParam(value = "cpr") long cpr,
                              Model model) {
        System.out.println(id + " :id/cpr: " + cpr);

        String result = resultService.fetchResultByIdAndCpr(id, cpr);

        if(result != null){
            Patient patient = resultService.fetchPatientByCpr(cpr);
            String name = patient.getFirstName() + " " + patient.getLastName();

            model.addAttribute("id", id);
            model.addAttribute("name", name);
            model.addAttribute("result", result);

            return "results";
        }

        return viewResultForm(id, model);
    }
}
