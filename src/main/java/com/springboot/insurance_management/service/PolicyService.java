package com.springboot.insurance_management.service;

import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.model.Policy;
import com.springboot.insurance_management.repository.PolicyRepository;
import exception.DuplicateResourceException;
import exception.EmptyInputException;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {
    @Autowired
    PolicyRepository policyRepository;

    public Policy createPolicy(Policy policy) {

        return policyRepository.save(policy);

    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }


    public Policy getPolicyByPolicyId(int id) {
        return policyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("policy" + "id" + String.valueOf(id)));
    }

    public ResponseEntity<String> deletePolicy(int id) {
        policyRepository.deleteById(id);
        return new ResponseEntity<>("Policy deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Customer>> getCustomersByPolicyId(int policyId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy", "policyId", String.valueOf(policyId)));

        List<Customer> customers = new ArrayList<>(policy.getCustomers());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    public ResponseEntity<String> createPolicyByCustomerId(int customerId, Policy policy) {

        policyRepository.save(policy);
        return new ResponseEntity<>("policy created successfully ", HttpStatus.CREATED);
    }


}


