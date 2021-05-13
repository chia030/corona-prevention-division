package com.cpd.coronapreventiondivision.Repository.Mappers;

import com.cpd.coronapreventiondivision.Model.WorkWeek;
import com.cpd.coronapreventiondivision.Repository.WorkDayRepo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkWeekRowMapper implements RowMapper<WorkWeek> {
    @Override
    public WorkWeek mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkWeek(
                WorkDayRepo.fetchById(rs.getInt(2)),
                WorkDayRepo.fetchById(rs.getInt(3)),
                WorkDayRepo.fetchById(rs.getInt(4)),
                WorkDayRepo.fetchById(rs.getInt(5)),
                WorkDayRepo.fetchById(rs.getInt(6)),
                WorkDayRepo.fetchById(rs.getInt(7)),
                WorkDayRepo.fetchById(rs.getInt(8))
        );
    }
}
