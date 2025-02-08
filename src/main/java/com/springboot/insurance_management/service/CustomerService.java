package com.springboot.insurance_management.service;


import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.model.Policy;
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
import java.util.Set;

@Service
public class CustomerService {
   @Autowired
    CustomerRepositoy customerRepository;
    public List<Customer> getAllCustomers() {

       List<Customer>customers= customerRepository.findAll();

       return customers;
    }

    public Customer getCustomerById(int id) {
       return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException
               ("customer","id",String.valueOf(id)));
    }

    public Customer addCustomer(Customer customer) {
        if(customer.getName()==null || customer.getName().trim().isEmpty()  ){
            throw new EmptyInputException("Customer name cannot be empty");
        }
        if(customer.getEmail()==null || customer.getEmail().trim().isEmpty()  ){
            throw new EmptyInputException("Customer email cannot be empty");
        }
          Optional<Customer> existingCustomer=customerRepository.findByEmail(customer.getEmail());
        if(existingCustomer.isPresent()){
            throw new DuplicateResourceException("Customer with this email already exists");
        }
        return customerRepository.save(customer);
    }

    //get policies linked to a customer
    public Set<Policy> getPoliciesByCustomerId(int customerId) {
        Customer customer=customerRepository.findById(customerId).orElseThrow(
                ()->new ResourceNotFoundException("Customer","customerId",String.valueOf(customerId)));
        return customer.getPolicies();
    }

    //delete customer by id
     public void deleteCustomerById(int customerId) {
        Customer customer=customerRepository.findById(customerId).orElseThrow(
                ()->new ResourceNotFoundException("Customer","customerId",String.valueOf(customerId)));
        customerRepository.delete(customer);
    }
}


