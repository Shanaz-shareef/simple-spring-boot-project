package com.springboot.insurance_management.service;

import com.springboot.insurance_management.model.Claim;
import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.model.Policy;
import com.springboot.insurance_management.repository.ClaimRepositoy;
import com.springboot.insurance_management.repository.CustomerRepositoy;
import com.springboot.insurance_management.repository.PolicyRepository;
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

    @Autowired
    CustomerRepositoy customerRepository;

    @Autowired
    PolicyRepository policyRepository;

    public Claim saveClaim(int policyId, int customerId, Claim claim) {
        //fetch the policy by policy id
        Policy policy = policyRepository.findById(policyId).orElseThrow(
                () -> new ResourceNotFoundException("policy" + "id" + String.valueOf(policyId)));
        //fetch customer by customer id
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer" + "id" + String.valueOf(customerId)));

        //Ensure the customer has policy before allowing a claim
        if (!customer.getPolicies().contains(policy)) {
            throw new IllegalStateException("Customer does not have this policy, cannot create claim.");
        }
        //set relationship
        claim.setPolicy(policy);
        claim.setCustomer(customer);
        return claimRepository.save(claim);
    }

    public Claim getByClaimId(int claimId) {
        return claimRepository.findById(claimId).orElseThrow(() -> new ResourceNotFoundException("claim" + "id" + String.valueOf(claimId)));
    }

    public ResponseEntity<String> deleteClaim(int claimId) {
        claimRepository.deleteById(claimId);
        return new ResponseEntity<>("claim deleted successfully", HttpStatus.OK);
    }


    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
