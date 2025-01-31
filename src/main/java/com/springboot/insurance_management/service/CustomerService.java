package com.springboot.insurance_management.service;


import com.springboot.insurance_management.model.Customer;
import com.springboot.insurance_management.repository.CustomerRepositoy;
import exception.CustomerNotFoundException;
import exception.DuplicateCustomerException;
import exception.InvalidCustomerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerService {
   @Autowired
    CustomerRepositoy repository;
    public List<Customer> getAllCustomers() {

       List<Customer>customers= repository.findAll();
       if(customers.isEmpty()){
           throw new CustomerNotFoundException("no customers found");
       }
       return customers;
    }

    public Customer getCustomerById(int id) {
       return repository.findById(id).orElseThrow(()->new CustomerNotFoundException("customer not found with the id:"+id));
    }

    public Customer addCustomer(Customer customer) {
        if(customer.getName().trim().isEmpty() || customer.getName()==null){
            throw new InvalidCustomerException("customer name cannot be empty");
        }
        if(customer.getEmail().trim().isEmpty() || customer.getEmail()==null){
            throw new InvalidCustomerException("customer email cannot be empty");
        }
          Optional<Customer> existingCustomer=repository.findByEmail(customer.getEmail());
        if(existingCustomer.isPresent()){
            throw new DuplicateCustomerException("customer with the email already exists");
        }
        return repository.save(customer);
    }
}
