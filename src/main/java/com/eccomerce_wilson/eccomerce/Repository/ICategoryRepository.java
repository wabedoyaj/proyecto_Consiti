package com.eccomerce_wilson.eccomerce.Repository;

import com.eccomerce_wilson.eccomerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
