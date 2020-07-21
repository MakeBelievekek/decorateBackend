package com.example.decorate.service;

import com.example.decorate.domain.Category;
import com.example.decorate.domain.Product;
import com.example.decorate.domain.ProductCategoryListItem;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.repository.CategoryRepository;
import com.example.decorate.repository.ProductCategoryListItemRepository;
import com.example.decorate.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;
    private ProductCategoryListItemRepository productCategoryListItemRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductCategoryListItemRepository productCategoryListItemRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryListItemRepository = productCategoryListItemRepository;
        this.categoryRepository = categoryRepository;
    }

    public void saveProduct(ProductFormData productFormData) {
        Product product = new Product();
        product.setName(productFormData.getProductName());
        product.setProductDesc(productFormData.getProductDesc());
        product.setProductCategory(productFormData.getProductCategory());
        product.setImgUrl(productFormData.getProductImg());
        productRepository.save(product);
        saveProductCategoryListItem(product, productFormData.getCategoryId());

    }

    public void saveProductCategoryListItem(Product product, Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            ProductCategoryListItem productCategoryListItem = new ProductCategoryListItem(product, category.get());
            productCategoryListItemRepository.save(productCategoryListItem);
        }
    }
}
