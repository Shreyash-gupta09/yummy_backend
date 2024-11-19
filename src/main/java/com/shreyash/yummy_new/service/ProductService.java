package com.shreyash.yummy_new.service;

import com.shreyash.yummy_new.entity.Product;
import com.shreyash.yummy_new.repo.ProductRepo;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    private final ProductRepo productRepository;
    public ProductService(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findTop2ByPriceBetweenOrderByPriceAsc(minPrice, maxPrice);
    }
}