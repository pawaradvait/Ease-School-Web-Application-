package com.easeschool.service;

import com.easeschool.constant.AllConstantsOfApplitn;
import com.easeschool.model.Contact;
import com.easeschool.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    public boolean saveMessage(Contact contact){
        boolean isSave = false;
contact.setCreatedAt(LocalDateTime.now());
contact.setCreatedBy(AllConstantsOfApplitn.ANONYMOUS);
contact.setStatus(AllConstantsOfApplitn.OPEN);
          Contact saved = contactRepo.save(contact);
          if(saved != null){

              isSave = true;
          }
        return isSave;
    }

    public List<Contact> foundContact_msgWithStatus(String status){
        return contactRepo.findByStatus(status);

    }

    public int updateMsgStatus(long id,String status,String updatedBy){
        Optional<Contact> contact = contactRepo.findById(id);
       if(contact.isPresent()){
           contact.get().setStatus(status);
           contact.get().setUpdatedAt(LocalDateTime.now());
           contact.get().setUpdatedBy(updatedBy);
       }
        Contact c1 = contactRepo.save(contact.get());
        if(c1 != null){
            return 1;
        }
        return 0;
    }
}
