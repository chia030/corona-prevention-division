package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PatientRepo implements RowMapper<Patient> {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Patient(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getBoolean(5),
                rs.getString(6)
        );
    }

    public Patient fetchByCpr(long cpr){
        String query = "SELECT * FROM cpd1.patients WHERE cpr = ?";

        return template.queryForObject(query, new Object[]{cpr}, this);
    }

    public void verifyEmail(String approvalID){
        String query = "UPDATE cpd1.patients SET approved = true, approval_id = null WHERE approval_id = ?";

        template.query(query, new Object[]{approvalID}, this);
    }

    public List<Patient> fetch(long cpr, String email, String firstName, String lastName){
        String query = "SELECT * FROM cpd1.patients WHERE cpr = ? and first_name = ? and last_name = ?";

        return template.query(query, new Object[]{cpr, firstName, lastName}, this);
    }
}
