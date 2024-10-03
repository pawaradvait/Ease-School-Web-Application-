package com.easeschool.controller;

import com.easeschool.constant.AllConstantsOfApplitn;
import com.easeschool.model.Contact;
import com.easeschool.repo.ContactRepo;
import com.easeschool.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepo contactRepo;

    @RequestMapping("/contact")
    public String displayContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping("/saveMsg")
    public String saveMsg(@Valid @ModelAttribute(name = "contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
            return "contact.html";
        }
 System.out.println(contact);
         contactService.saveMessage(contact);
        return "redirect:contact";
    }

    @GetMapping("/displayMessages")
    public String displayMessages(Model model) {
        List<Contact> lc = contactService.foundContact_msgWithStatus(AllConstantsOfApplitn.OPEN);
        model.addAttribute("contactMsgs", lc);
        return "message.html";
    }

    @GetMapping("/closeMsg")
    public String updateMsgStatus(Model model, @RequestParam int id, Authentication authentication) {

   contactRepo.updateMsgStatus(id,AllConstantsOfApplitn.CLOSE,authentication.getName());
         return "redirect:displayMessages";

    }
}
