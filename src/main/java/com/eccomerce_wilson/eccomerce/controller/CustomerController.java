package com.eccomerce_wilson.eccomerce.controller;


import com.eccomerce_wilson.eccomerce.Entity.Customer;
import com.eccomerce_wilson.eccomerce.Exceptions.CustomerExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.CustomerNotFoundException;
import com.eccomerce_wilson.eccomerce.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(service.getAllCustomer());
    }

    @GetMapping("{customerId}")
    public Customer getCustomerId(@PathVariable Long customerId){
        return service.getCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity<Customer> createCCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(service.createCustomer(customer));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = service.updateCustomer(customerId, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        service.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(CustomerExistsException.class)
    public ResponseEntity<String> handleCustomerExistsException(CustomerExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
