package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CenterRepo implements RowMapper<Center> {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private WorkWeekRepo workWeekRepo;

    @Override
    public Center mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Center(
                rs.getInt(1),
                Center.CenterType.valueOf(rs.getString(2)),
                addressRepo.fetchById(rs.getInt(3)),
                workWeekRepo.fetchById(rs.getInt(4))
        );
    }

    public Center fetchById(int id){
        String query = "SELECT * FROM cpd1.centers WHERE center_id = ?";

        return template.queryForObject(query, new Object[]{id}, this);
    }

    public List<Center> fetchByType(String type){
        String query = "SELECT * FROM cpd1.centers WHERE type = ?";

        return template.query(query, new Object[]{type}, this);
    }

    public List<Center> fetchAll(){
        String query = "SELECT * FROM cpd1.centers";

        return template.query(query, this);
    }
}
