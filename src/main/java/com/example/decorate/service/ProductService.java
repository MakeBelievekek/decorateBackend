package com.example.decorate.service;

import com.example.decorate.domain.Product;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(ProductFormData productFormData) {
        Product product = new Product();
        product.setName(productFormData.getProductName());
        product.setProductDesc(productFormData.getProductDesc());
        product.setProductCategory(productFormData.getProductCategory());
        product.setImgUrl(productFormData.getProductImg());

        productRepository.save(product);

    }
}
