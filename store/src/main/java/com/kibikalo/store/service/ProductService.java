package com.kibikalo.store.service;

import com.kibikalo.store.exception.NotFoundException;

import com.kibikalo.store.model.Product;
import com.kibikalo.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new NotFoundException("Product not found with id " + id);
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
