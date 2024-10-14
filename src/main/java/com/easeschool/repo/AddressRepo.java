package com.easeschool.repo;

import com.easeschool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}
