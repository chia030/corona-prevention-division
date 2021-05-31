package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(){
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Model model){
        model.addAttribute("message", "There was an unexpected error. please try again later.");
        return "clue";
    }

//    @GetMapping("/redirect/{username}")
//    public RedirectView redirect(@RequestParam Model model, @PathVariable("username") String username) {
//
//        RedirectView rv = new RedirectView();
//
//        if (model.containsAttribute("ADMIN")) {
//            homeAdmin(model, username);
//            rv.setUrl("/home-admin/");
//        }
//        else if(model.containsAttribute("SECRETARY")) {
//            homeSecretary(model, username);
//            rv.setUrl("/home-secretary/");
//        }
//        else { errorPage(); }
//        return rv;
//    }
//
//    @GetMapping("/home-admin/{username}")
//    public String homeAdmin(@RequestParam Model model, @PathVariable("username") String username) {
//        return "admin/admin-landing";
//    }
//
//    @GetMapping("/home-secretary/{username}")
//    public String homeSecretary(@RequestParam Model model, @PathVariable("username") String username) {
//        return "logging/secretary-landing";
//    }
//
//    @GetMapping("/something-went-wrong")
//    public String errorPage() {
//        return "error-page";
//    }



}