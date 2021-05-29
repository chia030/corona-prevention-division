package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
                rs.getInt(1),
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

        List<Appointment> a = template.query(query, this, appointmentID, cpr);

        if (a.size() > 0){
            return a.get(0);
        }
        else {
            return null;
        }
    }

    public List<Appointment> fetchByCpr(long cpr){
        String query = "SELECT * FROM cpd1.appointments WHERE cpr = ?";

        return template.query(query, this, cpr);

    }

    public int insert(Appointment appointment){
        String query = "INSERT INTO cpd1.appointments (result, date, time, cpr, center_id, patient_email) VALUES (?, ?, ?, ?, ?, ?);";
              KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[] {"id"});
                    ps.setString(1, appointment.getResult().toString());
                    ps.setDate(2, java.sql.Date.valueOf(appointment.getDate()));
                    ps.setTime(3, java.sql.Time.valueOf(appointment.getTime()));
                    ps.setString(4, String.valueOf(appointment.getPatient().getCPR()));
                    ps.setInt(5, appointment.getCenter().getCenterID());
                    ps.setString(6, appointment.getPatientEmail());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }


    public int fetchNumberOfBookedAfter(int centerid, String date, String start){
        String query = "SELECT COUNT(*) FROM cpd1.appointments WHERE center_id = ? AND date = ? AND time > ?";

        return template.queryForObject(query, new Object[]{centerid, date, start}, Integer.class);
    }

    public int fetchNumberOfBookedAt(int centerid, String date, String start){
        String query = "SELECT COUNT(*) FROM cpd1.appointments WHERE center_id = ? AND date = ? AND time = ?";
        return template.queryForObject(query, Integer.class, centerid, date, start);

    }

    public void updateOldBooked(){
        String query = "UPDATE cpd1.appointments SET result = ? WHERE date < CURRENT_TIMESTAMP AND result = ?";

        try {
            template.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(query);
                        ps.setString(1, Appointment.Result.MISSED.name());
//                        ps.setDate(2, Date.valueOf(LocalDate.now()));
                        ps.setString(2, Appointment.Result.BOOKED.name());
                        return ps;
                    });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> fetchAll() {
        String query = "SELECT * FROM cpd1.appointments";
        return template.query(query, this::mapRow);
    }

    public List<Appointment> fetchBooked() {

        String query = "SELECT * FROM cpd1.appointments WHERE result = 'BOOKED'";
        return template.query(query, this);

    }

    public List<Appointment> fetchResolved() {

        String query = "SELECT * FROM cp1.appointments WHERE result =! 'BOOKED'";
        return template.query(query, this);

    }

    public List<Appointment> fetchByCenter(int centerID) {

        String query = "SELECT * FROM cp1.appointments WHERE center_id = ?";
        return template.query(query, this::mapRow, centerID);
    }

    public boolean updateAppointment(Appointment.Result status, int appointmentID ) {
        String query = "UPDATE cp1.appointments SET result = ? WHERE appointment_id = ? AND result = 'BOOKED'";
        try {
            template.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(query);
                        ps.setString(1, status.name());
                        ps.setInt(2, appointmentID);
                        return ps;
                    });
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
