package com.springboot.insurance_management.service;


import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.repository.CustomerRepositoy;

import exception.DuplicateResourceException;
import exception.EmptyInputException;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {
   @Autowired
    CustomerRepositoy repository;
    public List<Customer> getAllCustomers() {

       List<Customer>customers= repository.findAll();

       return customers;
    }

    public Customer getCustomerById(int id) {
       return repository.findById(id).orElseThrow(()->new ResourceNotFoundException
               ("customer","id",String.valueOf(id)));
    }

    public Customer addCustomer(Customer customer) {
        if(customer.getName().trim().isEmpty() || customer.getName()==null){
            throw new EmptyInputException("customer"+"customer name cannot be empty");
        }
        if(customer.getEmail().trim().isEmpty() || customer.getEmail()==null){
            throw new EmptyInputException("customer"+"customer email cannot be empty");
        }
          Optional<Customer> existingCustomer=repository.findByEmail(customer.getEmail());
        if(existingCustomer.isPresent()){
            throw new DuplicateResourceException("customer"+"customer with the email already exists");
        }
        return repository.save(customer);
    }}


