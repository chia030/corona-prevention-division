package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.WorkWeek;
import com.cpd.coronapreventiondivision.Repository.Mappers.WorkWeekRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class WorkWeekRepo {

    @Autowired
    private static JdbcTemplate template;

    public static WorkWeek fetchById(int id){
        String query = "SELECT * FROM cpd1.work_weeks WHERE work_week_id = ?";

        return template.queryForObject(query, new Object[]{id}, new WorkWeekRowMapper());
    }
}
