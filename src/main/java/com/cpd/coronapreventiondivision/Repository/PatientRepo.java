package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Repository.Mappers.PatientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PatientRepo {

    @Autowired
    private static JdbcTemplate template;

    public static Patient fetchByCpr(long cpr){
        String query = "SELECT * FROM cpd1.patients WHERE cpr = ?";

        return template.queryForObject(query, new Object[]{cpr}, new PatientRowMapper());
    }

}
