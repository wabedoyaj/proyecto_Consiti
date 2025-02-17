package com.eccomerce_wilson.eccomerce.Service;


import com.eccomerce_wilson.eccomerce.Entity.Customer;
import com.eccomerce_wilson.eccomerce.Exceptions.CategoryNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.CustomerExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.CustomerNotFoundException;
import com.eccomerce_wilson.eccomerce.Repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;


    @Override
    public List<Customer> getAllCustomer() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return iCustomerRepository.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException(customerId));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (iCustomerRepository.existsById(customer.getCustomerId())){
            throw new CustomerExistsException(customer.getCustomerId());
        }
        customer.setCustomerId(customer.getCustomerId());
        customer.setFirstname(customer.getFirstname());
        customer.setLastname(customer.getLastname());
        customer.setEmail(customer.getEmail());
        customer.setPhoneNumber(customer.getPhoneNumber());
        return iCustomerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        return iCustomerRepository.findById(customerId).map( existingCustomer -> {
            existingCustomer.setCustomerId(customer.getCustomerId());
            existingCustomer.setFirstname(customer.getFirstname());
            existingCustomer.setLastname(customer.getLastname());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            // Guardar el producto en la base de datos
            return iCustomerRepository.save(existingCustomer);
        }).orElseThrow(() -> new CategoryNotFoundException(customerId));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = iCustomerRepository.findById(customerId).orElseThrow(
                ()  -> new CustomerNotFoundException(customerId));
        iCustomerRepository.delete(customer);

    }
}
