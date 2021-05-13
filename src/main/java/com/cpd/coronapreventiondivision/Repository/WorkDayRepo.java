package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.WorkDay;
import com.cpd.coronapreventiondivision.Repository.Mappers.WorkDayRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class WorkDayRepo {

    @Autowired
    private static JdbcTemplate template;

    public static WorkDay fetchById(int id){
        String query = "SELECT * FROM cpd1.work_days WHERE work_day_id = ?";

        return template.queryForObject(query, new Object[]{id}, new WorkDayRowMapper());
    }
}
