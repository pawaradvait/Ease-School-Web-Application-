package com.easeschool.controller;

import com.easeschool.model.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {

    @GetMapping("displayCourses")
    public String student(Model model, HttpSession session) {
        Person person= (Person)session.getAttribute("person");
 model.addAttribute("person", person);
 return "course_enrolled.html";

    }

}
