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
        String query = "UPDATE cpd1.patients SET approved = 1, approval_id = null WHERE approval_id = ?";

        template.update(query, new Object[]{approvalID});
    }

    public List<Patient> fetch(long cpr, String email, String firstName, String lastName){
        String query = "SELECT * FROM cpd1.patients WHERE cpr = ? and first_name = ? and last_name = ?";

        return template.query(query, new Object[]{cpr, firstName, lastName}, this);
    }

    public boolean insert(Patient patient){
        try {
            String query = "INSERT INTO cpd1.patients (cpr, email, last_name, first_name, approved, approval_id) VALUES (?, ?, ?, ?, ?, ?);";

            template.update(query,
                    patient.getCPR(),
                    patient.getEmailAddress(),
                    patient.getLastName(),
                    patient.getFirstName(),
                    patient.isApproved(),
                    patient.getApprovalID());

            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public List<Patient> fetchPartialVaccinated() {
        String query = "SELECT * FROM cpd1.patients " +
                "INNER JOIN cpd1.appointments USING (cpr) " +
                "WHERE cpd1.appointments.result = 'PARTIAL_VACCINE'";

        return template.query(query, this);
    }

    public void setApproval(long cpr, String approvalID, String email){
        String query = "UPDATE cpd1.patients SET approval_id = ?, email = ? WHERE cpr = ?";

        template.update(query, new Object[]{approvalID, email, cpr});
    }
}
