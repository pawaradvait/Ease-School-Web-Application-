package com.easeschool.security;

import com.easeschool.model.Person;
import com.easeschool.model.Roles;
import com.easeschool.repo.PersonRepo;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EaseSchoolAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
     Person person =    personRepo.findByEmail(username);
     if(person != null) {
         if(passwordEncoder.matches(password , person.getPwd())){
             return new UsernamePasswordAuthenticationToken(username , null,getGrantedAuth(person.getRole()));
         }else{
             return null;
         }
     }else {
         return null;
     }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public List<GrantedAuthority> getGrantedAuth(Roles role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
    return grantedAuthorities;
    }
}
