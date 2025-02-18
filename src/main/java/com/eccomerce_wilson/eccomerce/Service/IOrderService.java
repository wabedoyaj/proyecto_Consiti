package com.eccomerce_wilson.eccomerce.Service;

import com.eccomerce_wilson.eccomerce.DTO.OrderDTO;
import com.eccomerce_wilson.eccomerce.DTO.ProductDTO;
import com.eccomerce_wilson.eccomerce.Entity.Order;
import com.eccomerce_wilson.eccomerce.Entity.Product;

import java.util.List;

public interface IOrderService {

    List<Order> getAllOrder();
    Order getOrderById(Long orderId);
    Order createOrder(OrderDTO orderDTO);
    Order updateOrder(Long orderId, OrderDTO orderDTO);
    void deleteOrder(Long orderId);

}
