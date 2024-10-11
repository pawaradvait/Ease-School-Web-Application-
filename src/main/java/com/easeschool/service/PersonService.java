package com.easeschool.service;

import com.easeschool.constant.AllConstantsOfApplitn;
import com.easeschool.model.Person;
import com.easeschool.model.Roles;
import com.easeschool.repo.PersonRepo;
import com.easeschool.repo.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private RolesRepo rolesRepo;

    public boolean savePerson(Person person) {
        boolean flag = false;
        Roles role =rolesRepo.findByRoleName(AllConstantsOfApplitn.STUDENT_ROLE);

        person.setRole(role);
     Person savedPerson =personRepo.save(person);
     if( savedPerson.getPersonId()>0) {
         flag = true;
     }
     return flag;
    }

}
