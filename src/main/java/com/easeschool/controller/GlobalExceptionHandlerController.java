package com.easeschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e, Model model) {
 ModelAndView mav = new ModelAndView();
 mav.setViewName("error");
 mav.addObject("errormsg", e.getMessage());

        return mav;
    }

}
