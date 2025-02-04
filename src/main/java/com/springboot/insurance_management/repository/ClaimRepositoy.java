package com.springboot.insurance_management.repository;

import com.springboot.insurance_management.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepositoy extends JpaRepository<Claim, Integer> {
}
