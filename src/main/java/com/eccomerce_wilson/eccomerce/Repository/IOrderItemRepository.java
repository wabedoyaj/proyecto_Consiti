package com.eccomerce_wilson.eccomerce.Repository;

import com.eccomerce_wilson.eccomerce.Entity.Category;
import com.eccomerce_wilson.eccomerce.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
}
