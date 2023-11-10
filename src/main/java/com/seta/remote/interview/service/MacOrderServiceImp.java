package com.seta.remote.interview.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seta.remote.interview.models.entity.Order;
import com.seta.remote.interview.repos.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
public class MacOrderServiceImp implements MacOrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<Order> findOrdersByOrderDate(LocalDate targetDate) {
        return orderRepo.findOrdersByOrderDate(targetDate);
    }

    // @Override
    // public List<Order> getOrdersWithProductsInCategory(String category) {
    //     log.info("Fetching all Orders by category: " + category);
    //     return orderRepo.findOrdersWithProductsInCategory(category);
    // }
    
    
    
}
