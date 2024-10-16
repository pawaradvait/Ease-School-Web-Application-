package com.easeschool.rest;

import com.easeschool.model.Contact;
import com.easeschool.model.Response;
import com.easeschool.repo.ContactRepo;
import com.easeschool.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@CrossOrigin("*")
public class ContactRestController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepo contactRepo;

    @GetMapping("/getMessageByStatus")
    public ResponseEntity<List<Contact>> getContact(@RequestParam(value = "status") String status) {
        List<Contact> contacts = contactService.foundContact_msgWithStatus(status);

  return ResponseEntity.status(200).body(contacts);

    }

    @PostMapping("/saveMsg")
 public ResponseEntity<Response> saveContact(@RequestHeader("invocationFrom") String invocationFrom ,
                                             @Valid @RequestBody Contact contact

                                             ) {
        boolean contact1 = contactService.saveMessage(contact);
        if(contact1 ) {
            Response response = new Response();
            response.setStatusCode("200");
            response.setMessage("saved successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            Response response = new Response();
            response.setStatusCode("500");
            response.setMessage("save failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteCourse(RequestEntity<Contact> request) throws Exception {
        Contact contact = request.getBody();
       Optional<Contact> contact1 = contactRepo.findById(contact.getContactId());
       if(contact1.isPresent()){
           contactRepo.deleteById(contact.getContactId());
           Response response = new Response();
           response.setStatusCode("200");
           response.setMessage("Message successfully deleted");
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(response);
       }else{
           throw new Exception(" course not deleted ");
       }

    }

}
