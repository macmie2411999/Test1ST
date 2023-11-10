// package com.seta.remote.interview.repos;

// import java.util.List;

// import org.springframework.data.jdbc.repository.query.Query;
// import com.seta.remote.interview.models.entity.OrderProductRelationship;
// import com.seta.remote.interview.models.entity.Order;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface OrderProductRelationShipRepo extends CrudRepository<OrderProductRelationship, Long> {

// 	List<OrderProductRelationship> findAll();

// 	@Query("SELECT opr.order FROM OrderProductRelationship opr WHERE opr.product.id = :productId")
//     List<Order> findOrdersByProductId(@Param("productId") Long productId);

// }
