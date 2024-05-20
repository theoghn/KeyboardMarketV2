package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Product;
import com.tfluke.KBDMarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
}
