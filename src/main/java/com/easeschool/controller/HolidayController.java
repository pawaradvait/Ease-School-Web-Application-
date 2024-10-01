package com.easeschool.controller;

import com.easeschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

    @GetMapping("/holidays")
    public String displayHoliday(Model model, @RequestParam(value = "festival" ,required = false) String festival
     ,@RequestParam(value = "federal" , required = false) String federal
     ) {
        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
        );
        Holiday.Type[] types=Holiday.Type.values();
        for (Holiday.Type type : types) {
            if(festival != null && type == Holiday.Type.FESTIVAL) {
                model.addAttribute("festival", "true");
                model.addAttribute(type.toString(), holidays.stream().filter((holiday)->holiday.getType().equals(type)).collect(Collectors.toList()));

            }
            if(federal != null && type == Holiday.Type.FEDERAL) {
                model.addAttribute("federal", "true");
                model.addAttribute(type.toString(), holidays.stream().filter((holiday)->holiday.getType().equals(type)).collect(Collectors.toList()));

            }


        }

        return "holiday.html";
    }

}
