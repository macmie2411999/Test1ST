package com.seta.remote.interview.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.seta.remote.interview.models.entity.Order;
import com.seta.remote.interview.models.entity.Product;
import com.seta.remote.interview.repos.ProductRepo;

public interface MacOrderService {
   
    // public List<Order> getOrdersWithProductsInCategory(String category); 
    
    public List<Order> findOrdersByOrderDate(LocalDate targetDate);
     
}
