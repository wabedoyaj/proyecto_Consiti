package com.eccomerce_wilson.eccomerce.Service.implem;

import com.eccomerce_wilson.eccomerce.DTO.OrderDTO;
import com.eccomerce_wilson.eccomerce.DTO.OrderItemDTO;
import com.eccomerce_wilson.eccomerce.Entity.Category;
import com.eccomerce_wilson.eccomerce.Entity.Order;
import com.eccomerce_wilson.eccomerce.Entity.OrderItem;
import com.eccomerce_wilson.eccomerce.Entity.Product;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.CategoryNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.OrderItemNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.OrderNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.ProductNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.OrderItemExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.ProductExistsException;
import com.eccomerce_wilson.eccomerce.Repository.IOrderItemRepository;
import com.eccomerce_wilson.eccomerce.Repository.IOrderRepository;
import com.eccomerce_wilson.eccomerce.Repository.IProductRepository;
import com.eccomerce_wilson.eccomerce.Service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private IOrderItemRepository orderItemRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(
                ()-> new OrderItemNotFoundException(orderItemId));
    }

    @Override
    public OrderItem createOrderItem(OrderItemDTO orderItemDTO) {
        if (orderItemRepository.existsById(orderItemDTO.getOrderItemId())) {
            throw new OrderItemExistsException(orderItemDTO.getOrderItemId());
        }
        Order order = orderRepository.findById(orderItemDTO.getOrderId()).orElseThrow(
                ()-> new OrderNotFoundException(orderItemDTO.getOrderId())
        );
        Product product= productRepository.findById(orderItemDTO.getProductId()).orElseThrow(
                ()-> new ProductNotFoundException(orderItemDTO.getProductId())
        );

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(orderItemDTO.getOrderItemId());
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setSubTotal(orderItemDTO.getQuantity()*orderItemDTO.getPrice());
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long orderItemId, OrderItemDTO orderItemDTO) {
        return orderItemRepository.findById(orderItemId).map( existingOrderItem -> {
            Order order = orderRepository.findById(orderItemDTO.getOrderId()).orElseThrow(
                    ()-> new OrderNotFoundException(orderItemDTO.getOrderId())
            );
            Product product= productRepository.findById(orderItemDTO.getProductId()).orElseThrow(
                    ()-> new ProductNotFoundException(orderItemDTO.getProductId())
            );
            existingOrderItem.setOrderItemId(orderItemDTO.getOrderItemId());
            existingOrderItem.setOrder(order);
            existingOrderItem.setProduct(product);
            existingOrderItem.setPrice(orderItemDTO.getPrice());
            existingOrderItem.setQuantity(orderItemDTO.getQuantity());
            existingOrderItem.setSubTotal(orderItemDTO.getPrice()* orderItemDTO.getQuantity());
            // Guardar el producto en la base de datos
            return orderItemRepository.save(existingOrderItem);
        }).orElseThrow(() -> new OrderItemNotFoundException(orderItemId));
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                ()-> new OrderItemNotFoundException(orderItemId)
        );
        orderItemRepository.delete(orderItem);

    }
}
