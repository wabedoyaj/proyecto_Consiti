package com.eccomerce_wilson.eccomerce.Service.implem;

import com.eccomerce_wilson.eccomerce.DTO.OrderDTO;
import com.eccomerce_wilson.eccomerce.Entity.Customer;
import com.eccomerce_wilson.eccomerce.Entity.Order;
import com.eccomerce_wilson.eccomerce.Exceptions.*;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.CustomerNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.OrderNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.ProductNotFoundException;
import com.eccomerce_wilson.eccomerce.Repository.ICustomerRepository;
import com.eccomerce_wilson.eccomerce.Repository.IOrderRepository;
import com.eccomerce_wilson.eccomerce.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements IOrderService {
    @Autowired
    private IOrderRepository repository;

    @Autowired
    private ICustomerRepository CustomerRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Order> getAllOrder() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return repository.findById(orderId).orElseThrow(
                ()-> new OrderNotFoundException(orderId)
        );
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        if (repository.existsById(orderDTO.getOrderId())) {
            throw new OrderExistsException(orderDTO.getOrderId());
        }
        // Buscar el customer en la base de datos usando el ID
        Customer customer = customerRepository.findById(orderDTO.getCustomerId()).orElseThrow(()
                -> new CustomerNotFoundException(orderDTO.getCustomerId()));

        // Crear una nueva instancia y asignar los valores
        Order order1 = new Order();
        order1.setOrderId(orderDTO.getOrderId());
        order1.setCustomer(customer);
        order1.setTotalAmount(orderDTO.getTotalAmount());
        // Guardar la orden en la base de datos
        return repository.save(order1);
    }

    @Override
    public Order updateOrder(Long orderId, OrderDTO orderDTO) {
        return repository.findById(orderId).map( existingOrder -> {
            Customer customer = customerRepository.findById(orderDTO.getCustomerId()).orElseThrow(()
                    -> new CustomerNotFoundException(orderDTO.getCustomerId()));
            existingOrder.setOrderId(orderDTO.getOrderId());
            existingOrder.setCustomer(customer);
            existingOrder.setTotalAmount(orderDTO.getTotalAmount());
            // Guardar el producto en la base de datos
            return repository.save(existingOrder);
        }).orElseThrow(() -> new ProductNotFoundException(orderId));

    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        repository.delete(order);
    }


}
