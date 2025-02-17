package com.eccomerce_wilson.eccomerce.Repository;

import com.eccomerce_wilson.eccomerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
}
