package com.seta.remote.interview;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seta.remote.interview.models.entity.Product;
import com.seta.remote.interview.models.entity.Order;
import com.seta.remote.interview.models.entity.Customer;
import com.seta.remote.interview.repos.OrderRepo;
import com.seta.remote.interview.repos.ProductRepo;
import com.seta.remote.interview.repos.CustomerRepo;

@Slf4j
@Component
@Transactional
public class AppCommandRunner implements CommandLineRunner {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public void run(String... args) throws Exception {
        log.info("running runner");
        basicStreamApi();
        advanceStreamApi();

    }

    private void basicStreamApi() {
        List<Product> allProducts = productRepo.findAll();
        List<Order> allOrders = orderRepo.findAll();
        List<Customer> allCustomers = customerRepo.findAll();

        // 1 — Obtain a list of products belongs to category “Books” with price > 100
        // log.info("Fetching all books > 100: " + productRepo.findExpensiveBooks());
        List<Product> expensiveBooks = allProducts.stream()
                .filter(product -> "Books".equals(product.getCategory()) && product.getPrice() > 100)
                .collect(Collectors.toList());

        log.info("Fetching a list of products belongs to category “Books” with price > 100: " + expensiveBooks);
        // log.info("Fetching tempt: " + productRepo.findExpensiveBooks().toString());

        // 2 — Obtain a list of order with products belong to category “Baby”
        List<Order> ordersWithBabyProducts = allOrders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> "Baby".equals(product.getCategory())))
                .collect(Collectors.toList());

        log.info("Fetching all Orders with products belong to category “Baby”: " + ordersWithBabyProducts);

        // 3 — Obtain a list of product with category = “Toys” and then apply 10%
        // discount
        List<Product> toysWithDiscount = allProducts.stream()
                .filter(product -> "Toys".equals(product.getCategory()))
                .map(product -> {
                    product.setPrice(product.getPrice() * 0.9);
                    return product;
                })
                .collect(Collectors.toList());

        log.info("Fetching all Toys with 10% discount: " + toysWithDiscount);

        // 4 — Obtain a list of products ordered by customer of tier 2 between
        // 01-Feb-2021 and 01-Apr-2021
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);

        List<Product> productsOrderedByTier2Customers = allOrders.stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().isAfter(startDate) && order.getOrderDate().isBefore(endDate))
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toList());

        log.info("Fetching products ordered by Tier 2 customers between 01-Feb-2021 and 01-Apr-2021: "
                + productsOrderedByTier2Customers);

        // 5 — Get the cheapest products of “Books” category
        Optional<Product> cheapestBook = allProducts.stream()
                .filter(product -> "Books".equals(product.getCategory()))
                .min(Comparator.comparing(Product::getPrice));

        if (cheapestBook.isPresent()) {
            log.info("The cheapest book: " + cheapestBook.get());
        } else {
            log.info("No books in the specified category.");
        }

        // 6 — Get the 3 most recent placed order
        List<Order> mostRecentOrders = allOrders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());

        if (!mostRecentOrders.isEmpty()) {
            log.info("The 3 most recent placed orders: " + mostRecentOrders);
        } else {
            log.info("No recent orders found.");
        }

        // 7 — Get a list of orders which were ordered on 15-Mar-2021, log the order

        // records to the console and then return its product list

        // 8 — Calculate total lump sum of all orders placed in Feb 2021

        // 9 — Calculate order average payment placed on 14-Mar-2021

        // 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min,
        // count) for all products of category “Books”

        // 11 — Obtain a data map with order id and order’s product count

        // 12 — Produce a data map with order records grouped by customer

        // 13 — Produce a data map with order record and product total sum

        // 14 — Obtain a data map with list of product name by category

        // 15 — Get the most expensive product by category

    }

    private void advanceStreamApi() {
        // Find the highest populated city of each country:
        // Find the most populated city of each continent:
        // Find the number of movies of each director: Try to solve this problem by
        // assuming that Director class has not the member movies.
        // Find the number of genres of each director's movies:
        // Find the highest populated capital city:
        // Find the highest populated capital city of each continent:
        // Sort the countries by number of their cities in desending order:
        // Find the list of movies having the genres "Drama" and "Comedy" only:
        // Group the movies by the year and list them:
        // Sort the countries by their population densities in descending order ignoring
        // zero population countries:
    }

}
