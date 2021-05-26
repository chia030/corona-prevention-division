package com.cpd.coronapreventiondivision.Repository;

import com.cpd.coronapreventiondivision.Model.Center;
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

    public void remove(int id){
        String query = "DELETE FROM cpd1.centers WHERE center_id = ?";
        template.update(query, new Object[]{id});
        removeCenterAppointments(id);
    }

    //removes the appointments booked in the center that was deleted
    public void removeCenterAppointments(int id) {
        String query = "DELETE FROM cp1.appointments WHERE center_id = ?";
        template.update(query, new Object[]{id});
    }

    // TODO: add something that alerts the customers about the appointments being deleted?
    // or TODO: make it impossible to delete a center if it has future appointments (it could be disabled, until there are no more)

    public Center fetchById(int id){
        String query = "SELECT * FROM cpd1.centers WHERE center_id = ?";


        List<Center> c = template.query(query,this,id);
        return (c.size() > 0) ? c.get(0) : null;
    }

    public List<Center> fetchByType(String type){
        String query = "SELECT * FROM cpd1.centers WHERE type = ?";

        return template.query(query, new Object[]{type}, this);
    }

    public List<Center> fetchAll(){
        String query = "SELECT * FROM cpd1.centers";

        return template.query(query, this);
    }

    public void update(int centerID, String centerType, int addressID, int workWeekID){
        String query = "UPDATE cpd1.centers SET center_type = ?, address_id = ?, work_week_id = ? WHERE center_id = ?";

        template.update(query, new Object[]{centerType, addressID, workWeekID, centerID});
    }

    public int insert(Center center){
        String query = "INSERT INTO cpd1.centers (center_type, address_id, work_week_id) VALUES (?, (SELECT address_id FROM cpd1.addresses WHERE address_id = ?), (SELECT work_week_id FROM cpd1.work_weeks WHERE work_week_id = ?));";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[] {"id"});
                    ps.setString(1, center.getCenterType().toString());
                    ps.setInt(2, center.getAddress().getId());
                    ps.setInt(3, center.getWorkWeek().getId());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
