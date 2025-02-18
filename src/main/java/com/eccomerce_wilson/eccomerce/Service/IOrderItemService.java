package com.eccomerce_wilson.eccomerce.Service;

import com.eccomerce_wilson.eccomerce.DTO.OrderItemDTO;
import com.eccomerce_wilson.eccomerce.Entity.Category;
import com.eccomerce_wilson.eccomerce.Entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    OrderItem getOrderItemById(Long orderItemId);
    List<OrderItem> getAllOrderItem();
    OrderItem createOrderItem(OrderItemDTO orderItemDTO);
    OrderItem updateOrderItem(Long orderItemId, OrderItemDTO orderItemDTO);
    void deleteOrderItem(Long orderItemId);
}
