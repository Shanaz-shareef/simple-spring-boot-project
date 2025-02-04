package com.springboot.insurance_management.controller;

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
     @PostMapping()
    public ResponseEntity<Policy> createPolicy(@ RequestBody Policy policy){
        Policy savedPolicy= policyService.createPolicy(policy);
         return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
     }

     @GetMapping()
    public List<Policy> getAllPolicies(){
     return    policyService.getAllPolicies();
     }

     @GetMapping("/getByPolicy/{policyId}")
    public Policy getPolicyByPolicyId(@PathVariable  int policyId){
         return policyService.getPolicyByPolicyId(policyId);
     }
    @DeleteMapping("/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable int policyId){
        return policyService.deletePolicy(policyId);
    }
}
