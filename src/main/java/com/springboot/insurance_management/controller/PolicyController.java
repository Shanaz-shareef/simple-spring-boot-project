package com.springboot.insurance_management.controller;

import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.model.Policy;
import com.springboot.insurance_management.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    @Autowired
    PolicyService policyService;
    //
     @PostMapping()
    public ResponseEntity<Policy> createPolicy(@ RequestBody Policy policy){
        Policy savedPolicy= policyService.createPolicy(policy);
         return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
     }
   //http://localhost:8080/api/policies/getall
     @GetMapping("/getall")
    public List<Policy> getAllPolicies(){
     return    policyService.getAllPolicies();
     }
    //
     @GetMapping("/{policyId}")
    public Policy getPolicyByPolicyId(@PathVariable  int policyId){
         return policyService.getPolicyByPolicyId(policyId);
     }
     //
    @DeleteMapping("/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable int policyId){
        return policyService.deletePolicy(policyId);
    }
    //
    @PostMapping("/customers/{customerId}/policies")
    public ResponseEntity<String> createPolicyByCustomerId(@PathVariable("customerId") int customerId, @RequestBody Policy policy) {
        policyService.createPolicyByCustomerId( customerId,policy);
        return new ResponseEntity<>("Policy created with customer id:", HttpStatus.CREATED);
    }

    @GetMapping("/{policyId}/customers")
    public ResponseEntity<List<Customer>>getCustomersByPolicyId(@PathVariable("policyId")int policyId){
        return policyService.getCustomersByPolicyId(policyId);

    }
//    @GetMapping("customer/{customerId}/Policies")
//    public ResponseEntity<List<Policy>>getAllPoliciesByCustomerId(@PathVariable("customerId")int customerId){
//        return policyService.getAllPolicyByCustomerId(customerId);

}
