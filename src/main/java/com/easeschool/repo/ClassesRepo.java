package com.easeschool.repo;

import com.easeschool.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepo extends JpaRepository<Classes, Integer> {
}
