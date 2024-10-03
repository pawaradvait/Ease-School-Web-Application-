package com.easeschool.repo;


import com.easeschool.model.Contact;
import com.easeschool.rowMapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepo {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact){
        String sql = "INSERT INTO contact_msg (name,mobile_num,email,subject,message,status,created_at,created_by) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(),
                contact.getEmail(), contact.getSubject(), contact.getMessage(),
                contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy());

    }

  public List<Contact> findContactmsgWithStus(String status){
         String sql = "select * from contact_msg where status=?";
      List<Contact> lc =  jdbcTemplate.query(sql, new PreparedStatementSetter() {
             @Override
             public void setValues(PreparedStatement ps) throws SQLException {
                 ps.setString(1, status);
             }
         },new ContactMapper());
 return lc;
   }

   public int  updateMsgStatus(long id,String status,String updatedBy){
    LocalDateTime updatedDate =LocalDateTime.now();

        String sql = "update  contact_msg set status=? ,updated_by=? ,updated_At=? where contact_id=?";
 return jdbcTemplate.update(sql, status,updatedBy,updatedDate,id);


   }
}
