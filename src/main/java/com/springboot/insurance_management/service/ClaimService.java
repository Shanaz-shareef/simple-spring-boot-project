package com.springboot.insurance_management.service;

import com.springboot.insurance_management.model.Claim;
import com.springboot.insurance_management.repository.ClaimRepositoy;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class ClaimService {
    @Autowired
    ClaimRepositoy claimRepository;

    public Claim saveClaim(int policyId,Claim claim) {
        return claimRepository.save(claim);
    }
    public Claim getByClaimId(int claimId) {
        return claimRepository.findById(claimId).orElseThrow(()->new ResourceNotFoundException("claim"+"id"+String.valueOf(claimId)));
    }

    public ResponseEntity<String> deleteClaim(int claimId) {
        claimRepository.deleteById(claimId);
       return new ResponseEntity<>("claim deleted successfully", HttpStatus.OK);
    }

    public Claim updateClaim( Claim claim){
        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
       return claimRepository.findAll();
    }
}
