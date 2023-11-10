// package com.seta.remote.interview.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.seta.remote.interview.models.entity.Product;
// import com.seta.remote.interview.models.entity.Order;
// import com.seta.remote.interview.repos.OrderProductRelationShipRepo;
// import com.seta.remote.interview.repos.OrderRepo;
// import com.seta.remote.interview.repos.ProductRepo;

// import lombok.extern.slf4j.Slf4j;

// @Transactional
// @Slf4j
// @Service
// public class MacOPRServiceImp implements MacOPRService {

//     @Autowired
//     private OrderProductRelationShipRepo orderProductRelationShipRepo;

//     @Override
//     public List<Order> findOrdersByProductId(Long idProduct) {
//         log.info("Fetching all Books by category: " + idProduct);
//         return orderProductRelationShipRepo.findOrdersByProductId(idProduct);
//     }

    
// }
