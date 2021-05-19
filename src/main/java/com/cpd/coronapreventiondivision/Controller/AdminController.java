package com.cpd.coronapreventiondivision.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @GetMapping("/home-admin")
    public String homeAdmin() {
        return "admin/admin-landing";
    }

}
