package com.easeschool.rowMapper;

import com.easeschool.model.Holiday;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HolidayMapper implements RowMapper<Holiday> {



    @Override
    public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {

        Holiday holiday =new Holiday();
      holiday.setData(rs.getString("DAY"));
      holiday.setType(rs.getString("TYPE"));
      holiday.setReason(rs.getString("REASON"));
      System.out.println(holiday);
      return holiday;


    }
}
