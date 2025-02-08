package com.springboot.insurance_management.controller;

import com.springboot.insurance_management.model.Claim;
import com.springboot.insurance_management.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    @Autowired
    ClaimService claimService;

    //create claim linked to policy and customer
    //http://localhost:8080/api/claims/{policyId}/customers/{customerId}
    @PostMapping("/{policyId}/customers/{customerId}")
    public ResponseEntity<Claim> saveClaim( @PathVariable int policyId ,@PathVariable int customerId,@RequestBody Claim claim){
       Claim savedClaim=claimService.saveClaim(policyId,customerId,claim);
       return new ResponseEntity<>(savedClaim, HttpStatus.CREATED);
    }
    @GetMapping("/{claimId}")
    public Claim getByClaimId(@PathVariable int claimId){
        return claimService.getByClaimId(claimId);
    }
    @DeleteMapping("/{claimId}")
    public ResponseEntity<String> deleteClaim(@PathVariable int claimId){
       return claimService.deleteClaim(claimId);
    }

   @GetMapping("/getAll")
    public List<Claim> getAllClaims(){
        return claimService.getAllClaims();
    }


}
