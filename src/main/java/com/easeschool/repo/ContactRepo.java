package com.easeschool.repo;

import com.easeschool.model.Contact;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends ListCrudRepository<Contact, Long> {

    List<Contact> findByStatus(String name);
}
