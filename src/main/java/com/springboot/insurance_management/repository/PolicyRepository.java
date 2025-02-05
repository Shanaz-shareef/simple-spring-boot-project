package com.springboot.insurance_management.repository;

import com.springboot.insurance_management.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

}
