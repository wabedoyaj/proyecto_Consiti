package com.eccomerce_wilson.eccomerce.controller;

import com.eccomerce_wilson.eccomerce.DTO.OrderDTO;
import com.eccomerce_wilson.eccomerce.Entity.Order;
import com.eccomerce_wilson.eccomerce.Exceptions.*;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.CustomerNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.OrderNotFoundException;
import com.eccomerce_wilson.eccomerce.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.ok(service.getAllOrder());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
        Order order =  service.getOrderById(orderId);
        if (order==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO order) {
        return ResponseEntity.ok(service.createOrder(order));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = service.updateOrder(orderId,orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        service.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(OrderExistsException.class)
    public ResponseEntity<String> handleOrderExistsException(OrderExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
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
