package com.seta.remote.interview.repos;

import java.util.List;

import com.seta.remote.interview.models.entity.Product;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

	List<Product> findAll();

	@Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findBooksByCategory(@Param("category") String category);

	// @Modifying
    // @Query("UPDATE Product p SET p.price = :newPrice WHERE p.id = :productId")
    // void updateProductPrice(@Param("productId") Long productId, @Param("newPrice") Double newPrice);

	
}
