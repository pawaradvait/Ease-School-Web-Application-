package com.easeschool.repo;

import com.easeschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {

    Roles findByRoleName(String name);
}
