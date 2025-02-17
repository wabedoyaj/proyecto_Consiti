package com.eccomerce_wilson.eccomerce.Service;

import com.eccomerce_wilson.eccomerce.DTO.ProductDTO;
import com.eccomerce_wilson.eccomerce.Entity.Product;
import java.util.List;

public interface IProductService {

    List<Product> getAllProduct();
    Product getProductById(Long product_id);
    Product createProduct(ProductDTO productDto);
    Product updateProduct(Long product_id, ProductDTO productDto);
    void deleteProduct(Long product_id);

}
