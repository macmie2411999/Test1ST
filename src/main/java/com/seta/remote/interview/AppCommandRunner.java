package com.seta.remote.interview;

import com.seta.remote.interview.models.City;
import com.seta.remote.interview.models.Director;
import com.seta.remote.interview.models.Movie;
import com.seta.remote.interview.service.CountryDao;
import com.seta.remote.interview.service.InMemoryMovieService;
import com.seta.remote.interview.service.InMemoryWorldDao;
import com.seta.remote.interview.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("running runner");
        basicStreamApi();
        advanceStreamApi();

    }

    private void basicStreamApi() {
//        1 — Obtain a list of products belongs to category “Books” with price > 100
//        2 — Obtain a list of order with products belong to category “Baby”
//        3 — Obtain a list of product with category = “Toys” and then apply 10% discount
//        4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
//        5 — Get the cheapest products of “Books” category
//        6 — Get the 3 most recent placed order
//        7 — Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
//        8 — Calculate total lump sum of all orders placed in Feb 2021
//        9 — Calculate order average payment placed on 14-Mar-2021
//        10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
//        11 — Obtain a data map with order id and order’s product count
//        12 — Produce a data map with order records grouped by customer
//        13 — Produce a data map with order record and product total sum
//        14 — Obtain a data map with list of product name by category
//        15 — Get the most expensive product by category
    }

    private void advanceStreamApi() {
//        Find the highest populated city of each country:
        CountryDao countryDao = InMemoryWorldDao.getInstance();
        List<City> highPopulatedCitiesOfCountries = countryDao.findAllCountries()
                .stream()
                .map(country -> country.getCities().stream().max(Comparator.comparing(City::getPopulation)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        highPopulatedCitiesOfCountries.forEach(System.out::println);
//        Find the most populated city of each continent:
//        Find the number of movies of each director: Try to solve this problem by assuming that Director class has not the member movies.
//        Find the number of genres of each director's movies:
//        Find the highest populated capital city:
//        Find the highest populated capital city of each continent:
//        Sort the countries by number of their cities in desending order:
//        Find the list of movies having the genres "Drama" and "Comedy" only:
//        Group the movies by the year and list them:
//        Sort the countries by their population densities in descending order ignoring zero population countries:
        MovieService movieService = InMemoryMovieService.getInstance();
        Collection<Movie> movies = movieService.findAllMovies();
        Map<String, Long> directorMovieCounts =
                movies.stream()
                        .map(Movie::getDirectors)
                        .flatMap(List::stream)
                        .collect(Collectors.groupingBy(Director::getName, Collectors.counting()));
        directorMovieCounts.entrySet().forEach(System.out::println);
    }

}
