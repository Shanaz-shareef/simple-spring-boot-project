package com.springboot.insurance_management.repository;

import com.springboot.insurance_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepositoy extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
}
