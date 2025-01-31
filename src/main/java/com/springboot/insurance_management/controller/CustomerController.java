package com.springboot.insurance_management.controller;

import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    CustomerService service;
    @GetMapping("all")
    public List<Customer> getAllCustomers(){
       return service.getAllCustomers();
    }
    @GetMapping({"id"})
    public Customer getCustomerById(@PathVariable("id") int id){

        return service.getCustomerById(id);
    }
    @PostMapping()
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer savedCustomer=service.addCustomer(customer);
       return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED) ;
    }
}
