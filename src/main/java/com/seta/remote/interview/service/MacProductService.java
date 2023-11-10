package com.seta.remote.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.seta.remote.interview.models.entity.Product;
import com.seta.remote.interview.repos.ProductRepo;

public interface MacProductService {
    
    public List<Product> getListProductsByCategory(String category);

    public void updateProduct(Product newProduct);
    
}
