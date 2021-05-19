package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class AppointmentRepo implements RowMapper<Appointment> {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private CenterRepo centerRepo;

    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Appointment(
                Appointment.Result.valueOf(rs.getString(2)),
                LocalDate.parse(rs.getString(3), df),
                LocalTime.parse(rs.getString(4), tf),
                patientRepo.fetchByCpr(rs.getLong(5)),
                centerRepo.fetchById(rs.getInt(6)),
                rs.getString(7)
        );
    }

    public Appointment fetchByCenterAndDateTime(int centerID, LocalDate date, LocalTime time){
        String query = "SELECT * FROM cpd1.appointments WHERE center_id = ? AND date = ? and time = ?";

        return template.queryForObject(query, new Object[]{centerID, date.toString(), time.toString()}, this);
    }

    public Appointment fetchById(int appointmentID){
        String query = "SELECT * FROM cpd1.appointments WHERE appointment_id = ?";

        List<Appointment> a = template.query(query, new Object[]{appointmentID}, this);

        if (a.size() > 0){
            return a.get(0);
        }
        else {
            return null;
        }
    }

    public Appointment fetchByIdAndCpr(int appointmentID, long cpr){
        String query = "SELECT * FROM cpd1.appointments WHERE appointment_id = ? AND cpr = ?";

        List<Appointment> a = template.query(query, new Object[]{appointmentID, cpr}, this);

        if (a.size() > 0){
            return a.get(0);
        }
        else {
            return null;
        }
    }

    public List<Appointment> fetchByCpr(long cpr){
        String query = "SELECT * FROM cpd1.appointments WHERE cpr = ?";

        return template.query(query, new Object[]{cpr}, this);
    }

    public int fetchNumberOfAvailableSpots(int centerid, String date){
        String query = "SELECT COUNT(*) FROM cpd1.appointments WHERE center_id = ? AND date = ?";

        return template.queryForObject(query, new Object[]{centerid, date}, Integer.class);
    }

}
