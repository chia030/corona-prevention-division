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
    public void login(@ModelAttribute(name = "user") User user, Model model) {

        user = loginService.verifyCredentials(user);

        if (user!=null) {

            switch ((user.getLevel())) {
                case ADMIN:
                    model.addAttribute("user", user);
                    model.addAttribute("ADMIN");
                    redirect(model, user.getUsername());
                    System.out.println("admin in");
                    break;
//                    return "/redirect/" + user.getUsername();
                case SECRETARY:
                    model.addAttribute("user", user);
                    model.addAttribute("SECRETARY");
                    redirect(model, user.getUsername());
                    System.out.println("secretary in");
                    break;
//                    return "/redirect/"+ user.getUsername();
                default:
                    model = null;
                    errorPage();
//                    return "redirect:/something-went-wrong";
            }

        }
    }

    @GetMapping("/redirect/{username}")
    public RedirectView redirect(@RequestParam Model model, @PathVariable("username") String username) {

        RedirectView rv = new RedirectView();

        if (model.containsAttribute("ADMIN")) {
            homeAdmin(model, username);
            rv.setUrl("/home-admin/");
        }
        else if(model.containsAttribute("SECRETARY")) {
            homeSecretary(model, username);
            rv.setUrl("/home-secretary/");
        }
        else { errorPage(); }
        return rv;
    }

    @GetMapping("/home-admin/{username}")
    public String homeAdmin(@RequestParam Model model, @PathVariable("username") String username) {
        return "admin/admin-landing";
    }

    @GetMapping("/home-secretary/{username}")
    public String homeSecretary(@RequestParam Model model, @PathVariable("username") String username) {
        return "logging/secretary-landing";
    }

    @GetMapping("/something-went-wrong")
    public String errorPage() {
        return "error-page";
    }

}