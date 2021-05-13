package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Repository.Mappers.CenterRowMapper;
import com.cpd.coronapreventiondivision.Repository.Mappers.PatientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CenterRepo {

    @Autowired
    private static JdbcTemplate template;

    public static Center fetchById(int id){
        String query = "SELECT * FROM cpd1.centers WHERE center_id = ?";

        return template.queryForObject(query, new Object[]{id}, new CenterRowMapper());
    }

    public static List<Center> fetchAll(){
        String query = "SELECT * FROM cpd1.centers";

        return template.query(query, new CenterRowMapper());
    }
}
