package com.cpd.coronapreventiondivision.Repository.Mappers;

import com.cpd.coronapreventiondivision.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getString(2),
                rs.getString(3),
                User.UserType.valueOf(rs.getString(4))
        );
    }
}
