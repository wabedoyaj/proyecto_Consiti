package com.eccomerce_wilson.eccomerce.Service;

import com.eccomerce_wilson.eccomerce.DTO.ProductDTO;
import com.eccomerce_wilson.eccomerce.Entity.Customer;
import com.eccomerce_wilson.eccomerce.Entity.Product;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAllCustomer();
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customer);
    void deleteCustomer(Long customerId);

}
