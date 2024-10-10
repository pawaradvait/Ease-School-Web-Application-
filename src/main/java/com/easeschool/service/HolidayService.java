package com.easeschool.service;

import com.easeschool.model.Holiday;
import com.easeschool.repo.HolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepo holidayRepo;


    public List<Holiday> getAllHolidays() {
        return holidayRepo.findAll();
    }
}
