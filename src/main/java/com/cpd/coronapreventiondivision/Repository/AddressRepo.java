package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AddressRepo implements RowMapper<Address> {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Address(
                //rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6) == null ? "" : rs.getString(6), //hides the null values at the end
                rs.getString(7)
        );
    }

    public Address fetchById(int id){
        String query = "SELECT * FROM cpd1.addresses WHERE address_id = ?";

        return template.queryForObject(query, new Object[]{id}, this);
    }

    public Address fetch(String city, int postCode, String streetName, String streetNumber, String floor, String googleMapsLink){
        String query = "SELECT * FROM cpd1.addresses WHERE city = ? AND post_code = ? AND street_name = ? AND street_number = ? AND floor = ? AND google_maps_link = ?";

        List<Address> a = template.query(query, new Object[]{city, postCode, streetName, streetNumber, floor, googleMapsLink}, this);
        return (a.size() > 0) ? a.get(0) : null;
    }

    public int insert(Address address){
        String query = "INSERT INTO cpd1.addresses (city, post_code, street_name, street_number, floor, google_maps_link) VALUES (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[] {"id"});
                    ps.setString(1, address.getCity());
                    ps.setInt(2, address.getPostCode());
                    ps.setString(3, address.getStreetName());
                    ps.setString(4, address.getStreetNumber());
                    ps.setString(5, address.getFloor());
                    ps.setString(6, address.getGoogleMapsLink());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
