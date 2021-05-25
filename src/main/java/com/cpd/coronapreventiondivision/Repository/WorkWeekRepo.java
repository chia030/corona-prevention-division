package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.WorkDay;
import com.cpd.coronapreventiondivision.Model.WorkWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WorkWeekRepo implements RowMapper<WorkWeek> {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private WorkDayRepo workDayRepo;

    @Override
    public WorkWeek mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkWeek(
                rs.getInt(1),
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

    public WorkWeek fetch(WorkDay monday, WorkDay tuesday, WorkDay wednesday, WorkDay thursday, WorkDay friday, WorkDay saturday, WorkDay sunday){
        String query = "SELECT * FROM cpd1.work_weeks WHERE monday = ? AND tuesday = ? AND wednesday = ? AND thursday = ? AND friday = ? AND saturday = ? AND sunday = ?";

        List<WorkWeek> ww = template.query(query, new Object[]{monday.getId(), tuesday.getId(), wednesday.getId(), thursday.getId(), friday.getId(), saturday.getId(), sunday.getId()}, this);
        return (ww.size() > 0) ? ww.get(0) : null;
    }

    public int insert(WorkWeek week){
        String query = "INSERT INTO cpd1.work_weeks (monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[] {"id"});
                    ps.setInt(1, week.getMonday().getId());
                    ps.setInt(2, week.getTuesday().getId());
                    ps.setInt(3, week.getWednesday().getId());
                    ps.setInt(4, week.getThursday().getId());
                    ps.setInt(5, week.getFriday().getId());
                    ps.setInt(6, week.getSaturday().getId());
                    ps.setInt(7, week.getSunday().getId());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
