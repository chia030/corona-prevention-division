package com.cpd.coronapreventiondivision.Repository.Mappers;

import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import com.cpd.coronapreventiondivision.Repository.PatientRepo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentRowMapper implements RowMapper<Appointment> {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Appointment(
                Appointment.Result.valueOf(rs.getString(2)),
                LocalDate.parse(rs.getString(3), df),
                LocalTime.parse(rs.getString(4), tf),
                PatientRepo.fetchByCpr(rs.getLong(5)),
                CenterRepo.fetchById(rs.getInt(6)),
                rs.getString(7)
        );
    }
}
