package com.seta.remote.interview.models;

import lombok.Data;

import java.util.List;

@Data
public class Country {

    private String code;
    private String name;
    private String continent;
    private double surfaceArea;
    private int population;
    private double gnp;
    private int capital;
    private List<City> cities;
}
