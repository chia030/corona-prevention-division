package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        User user2 = new User(user.getUsername(), user.getPassword());
        System.out.println(user2);
        User user3 = loginService.fetchByUsernameAndPassword(user2.getUsername(),user2.getPassword());
        System.out.println(user3);
        System.out.println(user3 == null ? "wrong credentials" : "successful");

        if (user3!=null && user3.getLevel().equals(User.UserType.SECRETARY)) {
            return "redirect:/home-secretary";
        }

        else if(user3!=null && user3.getLevel() == User.UserType.ADMIN) {
            return "redirect:/home-admin";
        }

        else return "login";
    }

}
