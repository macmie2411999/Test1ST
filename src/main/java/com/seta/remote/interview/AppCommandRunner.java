package com.seta.remote.interview;

import java.util.List;
import java.util.stream.Collectors;

import com.seta.remote.interview.models.Order;
import com.seta.remote.interview.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import com.seta.remote.interview.repos.CustomerRepo;
import com.seta.remote.interview.repos.OrderRepo;
import com.seta.remote.interview.repos.ProductRepo;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

	@Autowired
	private CustomerRepo customerRepos;

	@Autowired
	private OrderRepo orderRepos;

	@Autowired
	private ProductRepo productRepos;


	@Override
	public void run(String... args) throws Exception {
		log.info("running runner");
		productRepos.findAll()
				.stream()
				.filter(p -> p.getCategory().equalsIgnoreCase("Books"))
				.filter(p -> p.getPrice() > 100)
				.collect(Collectors.toList())
				.forEach(System.out::println);
		
		List<Order> orders = orderRepos.findAll();
				
		

		List<Product> products = productRepos.findAll();
		products.stream()
		.flatMap(p -> p.getOrders().stream())
		.forEach(System.out::println);
	}

}
