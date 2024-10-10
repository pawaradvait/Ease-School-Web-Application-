package com.easeschool.controller;

import com.easeschool.model.Holiday;
import com.easeschool.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/holidays")
    public String displayHoliday(Model model, @RequestParam(value = "festival" ,required = false) String festival
     ,@RequestParam(value = "federal" , required = false) String federal
     ) {
//        List<Holiday> holidays = Arrays.asList(
//                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
//                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
//                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
//                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
//                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
//                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
//        );
//        Holiday.Type[] types=Holiday.Type.values();

        List<Holiday> holidays = holidayService.getAllHolidays();
        System.out.println(holidays);
        Set<String> tester = holidays.stream().map((holiday)-> holiday.getType()).distinct().collect(Collectors.toSet());
        List<Holiday> festivals =holidays.stream().filter((holiday) -> holiday.getType().equals("FESTIVAL")).collect(Collectors.toList());
        List<Holiday> federals = holidays.stream().filter((holiday) -> holiday.getType().equals("FEDERAL")).collect(Collectors.toList());

         System.out.println("tester" + tester);
        for (String type : tester) {

            if (festival != null && type.equals("FESTIVAL")) {
                System.out.println("enter");
                model.addAttribute("festival", "true");
                model.addAttribute(type, festivals);

            }
            if (federal != null && type.equals("FEDERAL")) {
                model.addAttribute("federal", "true");
                model.addAttribute(type, federals);

            }
        }
            return "holiday.html";
        }




}
