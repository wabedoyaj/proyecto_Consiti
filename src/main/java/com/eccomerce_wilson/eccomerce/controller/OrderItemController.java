package com.eccomerce_wilson.eccomerce.controller;

import com.eccomerce_wilson.eccomerce.DTO.OrderDTO;
import com.eccomerce_wilson.eccomerce.DTO.OrderItemDTO;
import com.eccomerce_wilson.eccomerce.Entity.Order;
import com.eccomerce_wilson.eccomerce.Entity.OrderItem;
import com.eccomerce_wilson.eccomerce.Exceptions.CustomerExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.CustomerNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.OrderItemNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.OrderNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.ProductNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.OrderExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.OrderItemExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.ProductExistsException;
import com.eccomerce_wilson.eccomerce.Service.IOrderItemService;
import com.eccomerce_wilson.eccomerce.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    @Autowired
    private IOrderItemService service;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItem(){
        return ResponseEntity.ok(service.getAllOrderItem());
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItem(@PathVariable Long orderItemId){
        OrderItem orderItem =  service.getOrderItemById(orderItemId);
        if (orderItem==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.ok(service.createOrderItem(orderItemDTO));
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long orderItemId, @RequestBody OrderItemDTO orderItemDTO) {
        OrderItem updatedOrderItem = service.updateOrderItem(orderItemId, orderItemDTO);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId) {
        service.deleteOrderItem(orderItemId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(OrderItemNotFoundException.class)
    public ResponseEntity<String> handleOrderItemNotFoundException(OrderItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(OrderItemExistsException.class)
    public ResponseEntity<String> handleOrderItemExistsException(OrderItemExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(OrderExistsException.class)
    public ResponseEntity<String> handleOrderExistsException(OrderExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(ProductExistsException.class)
    public ResponseEntity<String> handleProductExistsException(ProductExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
