package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

//    @PostMapping("/login")
//    public String login(){
//        return "redirect:/";
//    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Model model){
        model.addAttribute("message", "There was an unexpected error. please try again later.");
        return "booking/clue";
    }



}