package com.seta.remote.interview.repos;

import java.util.List;

import com.seta.remote.interview.models.entity.Product;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

	List<Product> findAll();

	// @Query("SELECT p FROM Product p WHERE p.category = 'Books' AND p.price > 100")
    // List<Product> findExpensiveBooks();
}
