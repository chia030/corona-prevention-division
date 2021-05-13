package com.cpd.coronapreventiondivision.Repository.Mappers;

import com.cpd.coronapreventiondivision.Model.Address;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Address(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6));
    }
}
