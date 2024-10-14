package com.easeschool.controller;

import com.easeschool.model.Address;
import com.easeschool.model.Person;
import com.easeschool.model.Profile;
import com.easeschool.repo.AddressRepo;
import com.easeschool.repo.PersonRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private AddressRepo addressRepo;


    @GetMapping("/displayProfile")
    public String displayProfile(Model model, HttpSession session) {
        Person person = (Person)session.getAttribute("person");
        Profile profile = new Profile();

        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());

        if(person.getAddress() !=null){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());

        }

        model.addAttribute("profile", profile);
        return "profile.html";

    }

    @PostMapping("/updateProfile")
    public String updateProfile( @Valid @ModelAttribute(name = "profile") Profile profile, Errors errors,HttpSession session) {
        if (errors.hasErrors()) {
            return "profile.html";
        }
         System.out.println(profile);
        Person person = (Person)session.getAttribute("person");




         person.setName(profile.getName());
         person.setEmail(profile.getEmail());
         person.setMobileNumber(profile.getMobileNumber());
         Address address  ;
         if(person.getAddress() ==null){
             System.out.println("entereeed..");
          address= new Address();
         }else{
              address = person.getAddress();
             System.out.println("noww..");
             System.out.println(person.getAddress());
              address.setAddressId(person.getAddress().getAddressId());
              System.out.println(address);
              System.out.println(person.getAddress());
         }

         address.setCity(profile.getCity());
         address.setState(profile.getState());
         address.setAddress1(profile.getAddress1());
         address.setAddress2(profile.getAddress2());
         address.setZipCode(profile.getZipCode());
          System.out.println(address);
      Address    savedaddress = addressRepo.save(address);


         person.setAddress(savedaddress);
System.out.println(person);
         personRepo.save(person);
         session.setAttribute("person", person);
         return "redirect:/displayProfile";
    }

}
