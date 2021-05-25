package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.User;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepo implements RowMapper<User> {

    @Autowired
    private JdbcTemplate template;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getString(1),
                rs.getString(2),
                User.UserType.valueOf(rs.getString(3))
        );
    }

    public User fetchByUsernameAndPassword(String username, String password){

        String query = "SELECT * FROM cpd1.users WHERE username = ? AND password = ?";

        return template.queryForObject(query, this, username, password);
    }

}
