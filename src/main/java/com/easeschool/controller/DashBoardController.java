package com.easeschool.controller;

import com.easeschool.model.Person;
import com.easeschool.repo.PersonRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashBoardController {
    @Autowired
    private PersonRepo personRepo;

    @RequestMapping(value = "/dashboard" , method = {RequestMethod.GET, RequestMethod.POST})
    public String dashBoard(Model model, Authentication auth , HttpSession session) {

          String username =auth.getName();

           Person person= personRepo.findByEmail(username);



        session.setAttribute("person", person);

        if(person.getEaseClass() !=null){
            model.addAttribute("enrolledClass", person.getEaseClass().getName());
        }
      model.addAttribute("username", person.getName());
      model.addAttribute("roles", auth.getAuthorities());
        return "dashboard.html";
    }


}
