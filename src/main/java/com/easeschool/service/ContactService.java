package com.easeschool.service;

import com.easeschool.constant.AllConstantsOfApplitn;
import com.easeschool.model.Contact;
import com.easeschool.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    public boolean saveMessage(Contact contact){
        boolean isSave = false;
contact.setCreatedAt(LocalDateTime.now());
contact.setCreatedBy(AllConstantsOfApplitn.ANONYMOUS);
contact.setStatus(AllConstantsOfApplitn.OPEN);
          int saved = contactRepo.saveContactMsg(contact);
          if(saved > 1){

              isSave = true;
          }
        return isSave;
    }
}
