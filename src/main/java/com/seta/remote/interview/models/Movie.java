package com.seta.remote.interview.models;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private int id;
    private String title;
    private int year;
    private String imdb;
    private List<Genre> genres;
    private List<Director> directors;
}
