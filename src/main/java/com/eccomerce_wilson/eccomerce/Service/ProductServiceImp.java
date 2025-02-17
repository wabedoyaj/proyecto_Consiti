package com.eccomerce_wilson.eccomerce.Service;

import com.eccomerce_wilson.eccomerce.DTO.ProductDTO;
import com.eccomerce_wilson.eccomerce.Entity.Category;
import com.eccomerce_wilson.eccomerce.Entity.Product;
import com.eccomerce_wilson.eccomerce.Exceptions.CategoryNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.ProductExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.ProductNotFoundException;
import com.eccomerce_wilson.eccomerce.Repository.ICategoryRepository;
import com.eccomerce_wilson.eccomerce.Repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements IProductService{
    @Autowired
    private IProductRepository repository;

    @Autowired
    private ICategoryRepository iCategoryRepository;


    @Override
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long product_id) {

        return repository.findById(product_id).orElseThrow(
                () -> new ProductNotFoundException(product_id));
    }



    @Override
    public Product createProduct(ProductDTO productDto) {

        if (repository.existsById(productDto.getProductId())) {
            throw new ProductExistsException(productDto.getProductId());
        }
        // Buscar la categoría en la base de datos usando el ID
        Category category = iCategoryRepository.findById(productDto.getCategoryId()).orElseThrow(()
                -> new CategoryNotFoundException(productDto.getCategoryId()));

        // Crear una nueva instancia de Product y asignar los valores
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(category); // Asignar la categoría
        // Guardar el producto en la base de datos
        return repository.save(product);
    }


    @Override
    public Product updateProduct(Long product_id, ProductDTO productDto) {

        return repository.findById(product_id).map( existingProduct -> {
            Category category = iCategoryRepository.findById(productDto.getCategoryId()).orElseThrow(()
                    -> new CategoryNotFoundException(productDto.getCategoryId()));

            existingProduct.setProductId(productDto.getProductId());
            existingProduct.setProductName(productDto.getProductName());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setStockQuantity(productDto.getStockQuantity());
            existingProduct.setCategory(category); // Asignar la categoría
            // Guardar el producto en la base de datos
            return repository.save(existingProduct);
        }).orElseThrow(() -> new ProductNotFoundException(product_id));
    }

    @Override
    public void deleteProduct(Long product_id) {
        Product product = repository.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException(product_id));
        repository.delete(product);

    }


}
