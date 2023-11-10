package com.seta.remote.interview.repos;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import com.seta.remote.interview.models.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

	List<Customer> findAll();

	@Query("SELECT c FROM Customer c WHERE c.tier = :tierCustomer")
    List<Customer> findCustomersByTier(@Param("tierCustomer") Integer tierCustomer);
}
