package com.seta.remote.interview.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private int id;
    private String name;
    private int population;
    private String countryCode;
}
