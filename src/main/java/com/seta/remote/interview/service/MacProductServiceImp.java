package com.seta.remote.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seta.remote.interview.models.entity.Product;
import com.seta.remote.interview.repos.OrderRepo;
import com.seta.remote.interview.repos.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
public class MacProductServiceImp implements MacProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getListProductsByCategory(String category) {
        // log.info("Fetching all Books by category: " + category);
        return productRepo.findBooksByCategory(category);
    }

    @Override
    public void updateProduct(Product newProduct) {
        // log.info("Update Product: " + newProduct.getId());
        productRepo.save(newProduct);

    }

    
}
