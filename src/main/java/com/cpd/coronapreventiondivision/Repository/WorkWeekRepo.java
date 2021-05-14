package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.WorkWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WorkWeekRepo implements RowMapper<WorkWeek> {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private WorkDayRepo workDayRepo;

    @Override
    public WorkWeek mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkWeek(
                workDayRepo.fetchById(rs.getInt(2)),
                workDayRepo.fetchById(rs.getInt(3)),
                workDayRepo.fetchById(rs.getInt(4)),
                workDayRepo.fetchById(rs.getInt(5)),
                workDayRepo.fetchById(rs.getInt(6)),
                workDayRepo.fetchById(rs.getInt(7)),
                workDayRepo.fetchById(rs.getInt(8))
        );
    }

    public WorkWeek fetchById(int id){
        String query = "SELECT * FROM cpd1.work_weeks WHERE work_week_id = ?";

        return template.queryForObject(query, new Object[]{id}, this);
    }
}
