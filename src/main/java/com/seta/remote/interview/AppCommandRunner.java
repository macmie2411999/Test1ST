package com.seta.remote.interview;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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
import com.seta.remote.interview.service.MacProductService;
import com.seta.remote.interview.service.MacCustomerService;
// import com.seta.remote.interview.service.MacOPRService;
import com.seta.remote.interview.service.MacOrderService;
// import com.seta.remote.interview.service.MacCustomerService;
import com.seta.remote.interview.utilities.PrintHelper;
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

        @Autowired
        private MacProductService macProductService;

        @Autowired
        private MacOrderService macOrderService;

        @Autowired
        private MacCustomerService macCustomerService;

        // @Autowired
        // private MacOPRService macOPRService;

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
                log.info("\n\n >>>>> 1. Fetching a list of Products belongs to category “Books” with price > 100: \n"
                                + PrintHelper.printLogContent(macProductService.getListProductsByCategory("Books")));

                // 2 — Obtain a list of order with products belong to category “Baby”
                List<Order> ordersWithBabyProducts = allOrders.stream()
                                .filter(order -> order.getProducts().stream()
                                                .anyMatch(product -> "Baby".equals(product.getCategory())))
                                .collect(Collectors.toList());

                log.info("\n\n >>>>> 2. Fetching a list of Orders with Products belongs to category “Baby”: \n"
                                + PrintHelper.printLogContent(ordersWithBabyProducts));

                // 3 — Obtain a list of product with category = “Toys” and then apply 10%
                // discount

                List<Product> listTempProducts = macProductService.getListProductsByCategory("Toys").stream()
                                .map(product -> {
                                        product.setPrice(product.getPrice() * 0.9);
                                        return product;
                                })
                                .collect(Collectors.toList());

                log.info("\n\n >>>>> 3. Fetching a list of Orders with Products category = “Toys” and with their price 10% discount: \n"
                                + PrintHelper.printLogContent(listTempProducts));

                // Update in DataBase
                // for(Product product : listTempProducts) {
                // macProductService.updateProduct(product);
                // }

                // 4 — Obtain a list of products ordered by customer of tier 2 between
                // 01-Feb-2021 and 01-Apr-2021
                LocalDate startDate = LocalDate.of(2021, 2, 1);
                LocalDate endDate = LocalDate.of(2021, 4, 1);

                List<Product> productsOrderedByTier2Customers = allOrders.stream()
                                .filter(order -> order.getCustomer().getTier() == 2)
                                .filter(order -> order.getOrderDate().isAfter(startDate)
                                                && order.getOrderDate().isBefore(endDate)) /// ???
                                .flatMap(order -> order.getProducts().stream())
                                .collect(Collectors.toList());

                log.info("\n\n >>>>> 4. Fetching products ordered by Tier 2 customers between 01-Feb-2021 and 01-Apr-2021: \n"
                                + PrintHelper.printLogContent(productsOrderedByTier2Customers));

                // 5 — Get the cheapest products of “Books” category
                Optional<Product> cheapestBook = macProductService.getListProductsByCategory("Books").stream()
                                .min(Comparator.comparing(Product::getPrice));

                if (cheapestBook.isPresent()) {
                        log.info("\n\n >>>>> 5. Fetching the cheapest products of “Books” category: \n\n\t"
                                        + cheapestBook.get()
                                        + "\n");
                } else {
                        log.info("\n\n >>>>> 5. Fetching the cheapest products of “Books” category: \n\n\t"
                                        + "Nothing in the specified category. \n");
                }

                // 6 — Get the 3 most recent placed order
                List<Order> mostRecentOrders = allOrders.stream()
                                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                                .limit(3)
                                .collect(Collectors.toList());

                if (!mostRecentOrders.isEmpty()) {
                        log.info(" \n\n >>>>> 6. The 3 most recent placed orders: \n"
                                        + PrintHelper.printLogContent(mostRecentOrders));
                } else {
                        log.info(" \n\n >>>>> 6. The 3 most recent placed orders: \n\n\t No recent orders found."
                                        + PrintHelper.printLogContent(mostRecentOrders));
                }

                // 7 — Get a list of orders which were ordered on 15-Mar-2021, log the order
                // records to the console and then return its product list
                LocalDate targetDate = LocalDate.of(2021, 3, 15);

                List<Order> ordersOnTargetDate = allOrders.stream()
                                .filter(order -> order.getOrderDate().isEqual(targetDate))
                                .collect(Collectors.toList());

                List<Product> productsFromOrdersOnTargetDate = ordersOnTargetDate.stream()
                                .flatMap(order -> order.getProducts().stream())
                                .collect(Collectors.toList());

                log.info("\n\n >>>>> 7.1. Fetching a list of Orders ordered on 15-Mar-2021: \n"
                                + PrintHelper.printLogContent(ordersOnTargetDate));

                log.info("\n\n >>>>> 7.2. Fetching a list of Products from Orders ordered on 15-Mar-2021: \n"
                                + PrintHelper.printLogContent(productsFromOrdersOnTargetDate));

                // 8 — Calculate total lump sum of all orders placed in Feb 2021
                LocalDate startFeb2021 = LocalDate.of(2021, 2, 1);
                LocalDate endFeb2021 = LocalDate.of(2021, 2, 28);

                List<Order> orderValueFeb2021 = allOrders.stream()
                                .filter(order -> order.getOrderDate().isAfter(startFeb2021)
                                                && order.getOrderDate().isBefore(endFeb2021)
                                                || order.getOrderDate().isEqual(startFeb2021)
                                                || order.getOrderDate().isEqual(endFeb2021)) // ???
                                .toList();

                List<Product> productsInFeb2021 = orderValueFeb2021.stream()
                                .flatMap(order -> order.getProducts().stream())
                                .collect(Collectors.toList());

                double totalPriceInFeb2021 = productsInFeb2021.stream()
                                .mapToDouble(Product::getPrice)
                                .sum();

                log.info("\n\n >>>>> 8.1. All Orders placed in Feb 2021: \n"
                                + PrintHelper.printLogContent(orderValueFeb2021));

                log.info("\n\n >>>>> 8.2. All Products placed in Feb 2021: \n"
                                + PrintHelper.printLogContent(productsInFeb2021));

                log.info("\n\n >>>>> 8.3. Sum Price of Products placed in Feb 2021: \n\n\t"
                                + totalPriceInFeb2021 + "\n");

                // 9 — Calculate order average payment placed on 14-Mar-2021
                LocalDate targetDate9 = LocalDate.of(2021, 3, 14);

                List<Order> ordersOnTargetDate9 = macOrderService.findOrdersByOrderDate(targetDate9);
                log.info("\n\n >>>>> 9. Order average payment placed on 14-Mar-2021: "
                                + (ordersOnTargetDate9.isEmpty() ? "empty"
                                                : "\n" + PrintHelper.printLogContent(ordersOnTargetDate9)));

                // 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min,
                // count) for all products of category “Books”
                DoubleSummaryStatistics statsCollection = macProductService.getListProductsByCategory("Books").stream()
                                .mapToDouble(Product::getPrice)
                                .summaryStatistics();

                log.info("\n\n >>>>> 10. A collection of statistic figures for all products of category “Books”: "
                                + "\n\n\t" + statsCollection + "\n");

                // 11 — Obtain a data map with order id and order’s product count
                Map<Long, Integer> orderProductCountMap = allOrders.stream()
                                .collect(Collectors.toMap(
                                                order -> order.getId(),
                                                order -> order.getProducts().size()));

                log.info("\n\n >>>>> 11. A data map with order id and order’s product count: "
                                + "\n\n" + PrintHelper.formatMapObAndOb(orderProductCountMap, "idOrder", "orderProduct")
                                + "\n");

                // 12 — Produce a data map with order records grouped by customer
                Map<Customer, List<Order>> ordersByCustomerMap = allOrders.stream()
                                .collect(Collectors.groupingBy(Order::getCustomer));

                log.info("\n\n >>>>> 12. A data map with order records grouped by customer: "
                                + "\n\n"
                                + PrintHelper.formatMapObAndListOb(ordersByCustomerMap, "idCustomer", "Orders")
                                + "\n");

                // 13 — Produce a data map with order record and product total sum
                Map<String, String> orderProductSumMap = allOrders.stream()
                                .collect(Collectors.toMap(
                                                order -> " { idOrder: " + order.getId(),
                                                order -> "sumProducts: " + order.getProducts().stream()
                                                                .mapToDouble(Product::getPrice)
                                                                .sum() + " } \n\n\t"));

                log.info("\n\n >>>>> 13. A data map with order record and product total sum: "
                                + "\n\n" + PrintHelper.formatMapObAndOb(orderProductCountMap, "idOrder", "orderProduct")
                                + "\n");

                // 14 — Obtain a data map with list of product name by category
                Map<String, List<Product>> listProductNameByCategory = allProducts.stream()
                                .collect(Collectors.groupingBy(
                                                product -> "" + product.getCategory(),
                                                Collectors.toList()));

                log.info("\n\n >>>>> 14. A data map with list of product name by category: "
                                + "\n\n"
                                + PrintHelper.formatMapObAndListOb(listProductNameByCategory, "nameCategory",
                                                "Products")
                                + "\n");

                // 15 — Get the most expensive product by category
                Map<String, Product> listExpensiveProductBCategory = allProducts.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)),
                                optionalProduct -> optionalProduct.orElse(null)
                        )
                ));

                log.info("\n\n >>>>> 15. The most expensive product by category: "
                                + "\n\n" + PrintHelper.formatMapObAndOb(listExpensiveProductBCategory, "productCategory", "Product")
                                + "\n");

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
