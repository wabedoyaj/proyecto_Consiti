package com.eccomerce_wilson.eccomerce.Repository;


import com.eccomerce_wilson.eccomerce.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

}
