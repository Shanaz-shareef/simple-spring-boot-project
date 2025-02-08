package com.springboot.insurance_management.controller;

import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.model.Policy;
import com.springboot.insurance_management.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class PolicyController {
    @Autowired
    PolicyService policyService;

    //create a policy
    //http://localhost:8080/api/v1/policy
    @PostMapping("/policy")
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        Policy savedPolicy = policyService.createPolicy(policy);
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }

    //get all policies
    //http://localhost:8080/api/v1/policies/getall
    @GetMapping("/policies/getall")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    //get policy by policy id
    //http://localhost:8080/api/v1/policies/1
    @GetMapping("/policies/{policyId}")
    public Policy getPolicyByPolicyId(@PathVariable int policyId) {
        return policyService.getPolicyByPolicyId(policyId);
    }

    //http://localhost:8080/api/v1/policy/1
    @DeleteMapping("/policy/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable int policyId) {
        return policyService.deletePolicy(policyId);
    }

    //Assign a policy to customer id
    //http://localhost:8080/api/v1/policy/{policyId}/customers/{customerId}
    @GetMapping("/policy/{policyId}/customers/{customerId}")
    public ResponseEntity<String> AssignPolicyToCustomer(@PathVariable("policyId") int policyId,@PathVariable int customerId) {
         policyService.AssignPolicyToCustomer(policyId,customerId);
       return new ResponseEntity<>("Policy assigned to customer successfully",HttpStatus.OK);
    }

    //get all customer linked to a policy
    //http://localhost:8080/api/v1/{policyId}/customers
    @GetMapping("/{policyId}/customers")
    public Set<Customer>  getCustomersByPolicy(@PathVariable int policyId){
        return policyService.getCustomersByPolicy(policyId);

}}
