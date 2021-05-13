package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Repository.Mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepo {

    @Autowired
    private static JdbcTemplate template;

    public static User fetchByUsernameAndPassword(String username, String password){
        String query = "SELECT * FROM cpd1.users WHERE username = ? AND password = ?";

        return template.queryForObject(query, new Object[]{username, password}, new UserRowMapper());
    }

}
