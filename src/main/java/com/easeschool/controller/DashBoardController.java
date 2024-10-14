package com.easeschool.controller;

import com.easeschool.model.Person;
import com.easeschool.repo.PersonRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {
    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/dashboard")
    public String dashBoard(Model model, Authentication auth , HttpSession session) {

          String username =auth.getName();

           Person person= personRepo.findByEmail(username);



        session.setAttribute("person", person);
      model.addAttribute("username", person.getName());
      model.addAttribute("roles", auth.getAuthorities());
        return "dashboard.html";
    }


}
