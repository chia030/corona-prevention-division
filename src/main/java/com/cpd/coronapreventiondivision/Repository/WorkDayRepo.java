package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.WorkDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class WorkDayRepo implements RowMapper<WorkDay> {

    @Autowired
    private JdbcTemplate template;

    private final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public WorkDay mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkDay(
                LocalTime.parse(rs.getString(2), tf),
                LocalTime.parse(rs.getString(3), tf),
                rs.getInt(4),
                rs.getInt(5)
        );
    }

    public  WorkDay fetchById(int id){
        String query = "SELECT * FROM cpd1.work_days WHERE work_day_id = ?";

        List<WorkDay> wd = template.query(query, new Object[]{id}, this);
        return (wd.size() > 0) ? wd.get(0) : null;
    }
}
