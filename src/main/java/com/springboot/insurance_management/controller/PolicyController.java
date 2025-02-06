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
    //http://localhost:8080/api/v1/policy
     @PostMapping("/policy")
    public ResponseEntity<Policy> createPolicy(@ RequestBody Policy policy){
        Policy savedPolicy= policyService.createPolicy(policy);
         return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
     }
   //http://localhost:8080/api/v1/policies/getall
     @GetMapping("/policies/getall")
    public List<Policy> getAllPolicies(){
     return    policyService.getAllPolicies();
     }
    //http://localhost:8080/api/v1/policies/1
     @GetMapping("/policies/{policyId}")
    public Policy getPolicyByPolicyId(@PathVariable  int policyId){
         return policyService.getPolicyByPolicyId(policyId);
     }
     //http://localhost:8080/api/v1/policies/1
    @DeleteMapping("policies/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable int policyId){
        return policyService.deletePolicy(policyId);
    }
    ////http://localhost:8080/api/v1/customers/1/policies
    @PostMapping("/customers/{customerId}/policies")
    public ResponseEntity<String> createPolicyByCustomerId(@PathVariable("customerId") int customerId, @RequestBody Policy policy) {
        policyService.createPolicyByCustomerId( customerId,policy);

        return new ResponseEntity<>("Policy created with customer id:", HttpStatus.CREATED);
    }
      //http://localhost:8080/api/v1/policy/1/customers
    @GetMapping("/policy/{policyId}/customers")
    public ResponseEntity<Set<Customer>>getCustomersByPolicyId(@PathVariable("policyId")int policyId){
        return policyService.getCustomersByPolicyId(policyId);

    }
//    @GetMapping("customer/{customerId}/Policies")
//    public ResponseEntity<List<Policy>>getAllPoliciesByCustomerId(@PathVariable("customerId")int customerId){
//        return policyService.getAllPolicyByCustomerId(customerId);

}
