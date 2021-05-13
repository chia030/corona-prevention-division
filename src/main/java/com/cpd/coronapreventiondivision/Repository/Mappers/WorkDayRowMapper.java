package com.cpd.coronapreventiondivision.Repository.Mappers;

import com.cpd.coronapreventiondivision.Model.WorkDay;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WorkDayRowMapper implements RowMapper<WorkDay> {
    private static final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public WorkDay mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkDay(
                LocalTime.parse(rs.getString(2), tf),
                LocalTime.parse(rs.getString(4), tf),
                rs.getInt(4),
                rs.getInt(5)
        );
    }
}
