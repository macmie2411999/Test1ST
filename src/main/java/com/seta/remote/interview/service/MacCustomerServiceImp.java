package com.seta.remote.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seta.remote.interview.models.entity.Customer;
import com.seta.remote.interview.repos.CustomerRepo;
import com.seta.remote.interview.repos.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
public class MacCustomerServiceImp implements MacCustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> findCustomersByTier(Integer tierCustomer) {
        // log.info("Fetching all Customer by tier: " + tierCustomer);
        return customerRepo.findCustomersByTier(tierCustomer);
    }
    
}
