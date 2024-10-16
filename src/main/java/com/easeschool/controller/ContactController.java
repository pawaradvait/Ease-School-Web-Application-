package com.easeschool.controller;

import com.easeschool.config.EaseSchoolProps;
import com.easeschool.constant.AllConstantsOfApplitn;
import com.easeschool.model.Contact;
import com.easeschool.repo.ContactRepo;
import com.easeschool.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    @Autowired
//    private Environment environment ;

    @Autowired
    private EaseSchoolProps easeSchoolProps;

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

    @GetMapping("/displayMessages/page/{pagenos}")
    public String displayMessages(Model model , @PathVariable int pagenos,
                                   @RequestParam(value = "sortField") String fieldName,
                                  @RequestParam(value = "sortDir") String sortDirec

                                  ) {
//        List<Contact> lc = contactService.foundContact_msgWithStatus(AllConstantsOfApplitn.OPEN);


 int pageSize = easeSchoolProps.getPagesize();

        Pageable pageable = PageRequest.of(pagenos -1  ,pageSize, sortDirec.equals("asc")?Sort.by(fieldName):Sort.by(fieldName).descending());
        Page<Contact> contact_mesg = contactService.foundContact_msgWithStatus(AllConstantsOfApplitn.OPEN , pageable);

         model.addAttribute("contactMsgs", contact_mesg);
         model.addAttribute("currentPage", pagenos);
         model.addAttribute("reverseSortDir" , sortDirec.equals("asc")?"desc":"asc");
     model.addAttribute("totalPages", contact_mesg.getTotalPages());
      model.addAttribute("sortField" , "name");
      model.addAttribute("sortDir" , "asc");
        return "message.html";
    }

    @GetMapping("/closeMsg")
    public String updateMsgStatus(Model model, @RequestParam int id, Authentication authentication) {

           contactService.updateMsgStatus(id,AllConstantsOfApplitn.CLOSE,authentication.getName());

         return "redirect:/displayMessages/page/1?sortField=name&sortDir=asc";


    }
}
