package com.easeschool.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    @GetMapping("/dashboard")
    public String dashBoard(Model model, Authentication auth) {
      model.addAttribute("username", auth.getName());
      model.addAttribute("roles", auth.getAuthorities());
        return "dashboard.html";
    }


}
