package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AddressRepo implements RowMapper<Address> {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Address(
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6) == null ? "" : rs.getString(6) //hides the null values at the end
                rs.getString(7)
        );
    }

    public Address fetchById(int id){
        String query = "SELECT * FROM cpd1.addresses WHERE address_id = ?";

        return template.queryForObject(query, new Object[]{id}, this);
    }
}
