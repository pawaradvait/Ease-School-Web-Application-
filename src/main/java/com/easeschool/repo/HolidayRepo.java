package com.easeschool.repo;

import com.easeschool.model.Holiday;
import org.springframework.data.repository.ListCrudRepository;

public interface HolidayRepo extends ListCrudRepository<Holiday, String> {

}
