package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.WorkDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
                rs.getInt(1),
                LocalTime.parse(rs.getString(2), tf),
                LocalTime.parse(rs.getString(3), tf),
                rs.getInt(4),
                rs.getInt(5)
        );
    }

    public WorkDay fetchById(int id){
        String query = "SELECT * FROM cpd1.work_days WHERE work_day_id = ?";

        List<WorkDay> wd = template.query(query, new Object[]{id}, this);
        return (wd.size() > 0) ? wd.get(0) : null;
    }

    public WorkDay fetch(String open, String close, int interval, int capacity){
        String query = "SELECT * FROM cpd1.work_days WHERE cast(opening_time as time) = cast(? as time) AND cast(closing_time as time) = cast(? as time) AND interv = ? AND capacity = ?";

        List<WorkDay> wd = template.query(query, new Object[]{open, close, interval, capacity}, this);
        return (wd.size() > 0) ? wd.get(0) : null;
    }

    public int insert(WorkDay day){
        String query = "INSERT INTO cpd1.work_days (opening_time, closing_time, interv, capacity) VALUES (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[] {"id"});
                    ps.setTime(1, java.sql.Time.valueOf(day.getOpeningTime()));
                    ps.setTime(2, java.sql.Time.valueOf(day.getClosingTime()));
                    ps.setInt(3, day.getInterval());
                    ps.setInt(4, day.getCapacity());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
