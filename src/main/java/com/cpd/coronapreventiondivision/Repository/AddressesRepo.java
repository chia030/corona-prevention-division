package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Address;
import com.cpd.coronapreventiondivision.Repository.Mappers.AddressRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressesRepo {

    @Autowired
    JdbcTemplate template;

    Address fetchById(int id){
        String query = "SELECT * FROM cpd1.addresses WHERE address_id = ?";

        return template.queryForObject(query, new Object[]{id}, new AddressRowMapper());
    }
}
