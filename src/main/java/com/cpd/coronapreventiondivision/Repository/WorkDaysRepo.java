package com.cpd.coronapreventiondivision.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDaysRepo {

    @Autowired
    JdbcTemplate template;

}