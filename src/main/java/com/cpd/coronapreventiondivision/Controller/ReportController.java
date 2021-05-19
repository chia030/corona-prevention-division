package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {

    @GetMapping("/home-secretary")
    public String homeSecretary() {
        return "logging/secretary-landing";
    }

}
