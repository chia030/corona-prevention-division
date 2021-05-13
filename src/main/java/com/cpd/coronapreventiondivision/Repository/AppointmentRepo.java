package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Model.WorkDay;
import com.cpd.coronapreventiondivision.Repository.Mappers.AppointmentRowMapper;
import com.cpd.coronapreventiondivision.Repository.Mappers.WorkDayRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentRepo {

    @Autowired
    private static JdbcTemplate template;

    public static Appointment fetchByCenterAndDateTime(int centerID, LocalDate date, LocalTime time){
        String query = "SELECT * FROM cpd1.appointments WHERE center_id = ? AND date = ? and time = ?";

        return template.queryForObject(query, new Object[]{centerID, date.toString(), time.toString()}, new AppointmentRowMapper());
    }

    public static List<Appointment> fetchByCpr(long cpr){
        String query = "SELECT * FROM cpd1.appointments WHERE cpr = ?";

        return template.query(query, new Object[]{cpr}, new AppointmentRowMapper());
    }
}
