package com.seta.remote.interview.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Director {
    private int id;
    private String name;
    private String imdb;
    private List<Movie> movies = new ArrayList<>();
}
