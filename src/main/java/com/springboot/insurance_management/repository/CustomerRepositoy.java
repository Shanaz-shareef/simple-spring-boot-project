package com.springboot.insurance_management.repository;

import com.springboot.insurance_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepositoy extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
}
