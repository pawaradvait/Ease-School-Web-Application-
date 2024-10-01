package com.easeschool.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error" ,required = false) String error, Model model,
                        @RequestParam(value = "logout",required = false) String logout
                        ) {
        if(logout!=null){
            model.addAttribute("errorMessge","user has logout successfully");

        }
        if(error != null) {
            model.addAttribute("errorMessge", "username or password is invalid");
        }
        return "login.html";
    }

}
