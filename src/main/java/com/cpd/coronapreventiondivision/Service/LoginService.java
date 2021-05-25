package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepo userRepo;

    public User fetchByUsernameAndPassword(String username, String password) {
        try {
            return userRepo.fetchByUsernameAndPassword(username, password);
        } catch(Exception e) { return null; }
    }

    public User verifyCredentials(User user) {
        try {

            User user2 = new User(user.getUsername(), user.getPassword()); //this hashes the password
            User user3 = fetchByUsernameAndPassword(user2.getUsername(), user2.getPassword());
            return user3;

        } catch(Exception e) { return null; }
    }

}
