package com.springboot.insurance_management.service;

import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.model.Policy;
import com.springboot.insurance_management.repository.CustomerRepositoy;
import com.springboot.insurance_management.repository.PolicyRepository;
import exception.DuplicateResourceException;
import exception.EmptyInputException;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class PolicyService {
    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    CustomerRepositoy customerRepository;
  // create a new policy
    public Policy createPolicy(Policy policy) {

        return policyRepository.save(policy);

    }
    //get all policies
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

   //get policy by policy id
    public Policy getPolicyByPolicyId(int id) {
        return policyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("policy" + "id" + String.valueOf(id)));
    }
    //delete policy by policy id
    public ResponseEntity<String> deletePolicy(int id) {
        policyRepository.deleteById(id);
        return new ResponseEntity<>("Policy deleted successfully", HttpStatus.OK);
    }


      //Assign a policy to customer
    public ResponseEntity<String> AssignPolicyToCustomer(int customerId, int policyId) {
        Policy existingPolicy=policyRepository.findById(policyId).orElseThrow(
                () -> new ResourceNotFoundException("policy not found with the id:" + String.valueOf(policyId)));
        Customer existingCustomer=customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer not found with id"+ String.valueOf(customerId)));
        existingCustomer.getPolicies().add(existingPolicy);
        customerRepository.save(existingCustomer);

        return new ResponseEntity<>("policy created successfully ", HttpStatus.CREATED);
    }


    public Set<Customer> getCustomersByPolicy(int policyId) {
        Policy existingPolicy=policyRepository.findById(policyId).orElseThrow(
                () -> new ResourceNotFoundException("policy" + "id" + String.valueOf(policyId)));
        return new HashSet<>(existingPolicy.getCustomers());
    }
}


