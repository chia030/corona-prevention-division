package com.cpd.coronapreventiondivision.Repository.Mappers;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Repository.AddressRepo;
import com.cpd.coronapreventiondivision.Repository.WorkWeekRepo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CenterRowMapper implements RowMapper<Center> {
    @Override
    public Center mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Center(
                rs.getInt(1),
                Center.CenterType.valueOf(rs.getString(2)),
                AddressRepo.fetchById(rs.getInt(3)),
                WorkWeekRepo.fetchById(rs.getInt(4))
        );
    }
}
