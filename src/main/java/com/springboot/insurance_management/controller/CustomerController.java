package com.springboot.insurance_management.controller;

import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.model.Policy;
import com.springboot.insurance_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //get all customers
    //http://localhost:8080/api/customers/getall
    @GetMapping("/getall")
    public List<Customer> getAllCustomers(){
       return customerService.getAllCustomers();
    }

    //get customer by id
    //http://localhost:8080/api/customers/getcustomer/3
    @GetMapping("getcustomer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    //add a new customer
    //http://localhost:8080/api/customers
    @PostMapping()
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer savedCustomer=customerService.addCustomer(customer);
       return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED) ;
    }

    //get policies by customer id
    //http://localhost:8080//api/customers/{customerId}/policies
    @GetMapping("/{customerId}/policies")
    public ResponseEntity<Set<Policy>>getPoliciesByCustomerId(@PathVariable int customerId){
        return ResponseEntity.ok(customerService.getPoliciesByCustomerId(customerId));
    }

    //Delete customer by id
    //http://localhost:8080//api/customers/{customerId}
    @GetMapping("/{customerId}")
    public ResponseEntity<String>deleteCustomerById(@PathVariable int customerId){
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>("Customer deleted successfully",HttpStatus.OK);
    }
}
