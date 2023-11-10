package com.seta.remote.interview.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import com.seta.remote.interview.models.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

	List<Order> findAll();

	// @Query("SELECT DISTINCT o FROM Order o JOIN o.products p WHERE p.category = :category")
    // List<Order> findOrdersWithProductsInCategory(@Param("category") String category);

	@Query("SELECT o FROM Order o WHERE o.orderDate = :targetDate")
    List<Order> findOrdersByOrderDate(@Param("targetDate") LocalDate targetDate);


}
