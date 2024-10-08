package com.easeschool.repo;

import com.easeschool.model.Holiday;
import com.easeschool.rowMapper.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HolidayRepo {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidayRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAll() {

        String sql = "SELECT * FROM holidays";


        return jdbcTemplate.query(sql, new HolidayMapper());
    }

}
