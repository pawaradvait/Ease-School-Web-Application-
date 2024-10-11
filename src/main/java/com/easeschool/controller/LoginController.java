package com.easeschool.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error" ,required = false) String error, Model model,
                        @RequestParam(value = "logout",required = false) String logout,
                        @RequestParam(value = "register" , required = false) String register
                        ) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect!";
        } else if (logout != null) {
            errorMessage = "You have been successfully logged out!";
        } else if (register != null) {
            errorMessage = "You registeration successful. Login with registered credentials!";
        }
        model.addAttribute("errorMessage", errorMessage);

        return "login.html";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            // logout includes "session.invalidate()"
        }
        return "redirect:/login?logout=true";
    }



}
