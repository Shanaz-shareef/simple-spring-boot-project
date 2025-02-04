package com.springboot.insurance_management.service;

import com.springboot.insurance_management.model.Policy;
import com.springboot.insurance_management.repository.PolicyRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    @Autowired
    PolicyRepository policyRepository;
    public Policy createPolicy(Policy policy) {
       return policyRepository.save(policy);
    }

    public List<Policy> getAllPolicies() {
      return  policyRepository.findAll();
    }



    public Policy getPolicyByPolicyId(int policyId) {
        return policyRepository.findById(policyId).orElseThrow(()->new ResourceNotFoundException("policy"+"id"+String.valueOf(policyId)));
    }

    public ResponseEntity<String> deletePolicy(int policyId) {
        policyRepository.deleteById(policyId);
        return new ResponseEntity<>("Policy deleted successfully",HttpStatus.OK);
    }

}

