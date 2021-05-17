package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService {

    @Autowired
    UserRepo userRepo;

    public User fetchByUsernameAndPassword(String username, String password) {
        try {
            return userRepo.fetchByUsernameAndPassword(username, password);
        } catch(Exception e) { return null; }
    }


}
