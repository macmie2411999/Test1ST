package com.seta.remote.interview.models;

import lombok.Data;

@Data
public class CriteriaBean {
    private boolean yearRangeSelected;
    private int fromYear;
    private int toYear;
    private boolean genreSelected;
    private int genre;
    private boolean directorSelected;
    private int director;
}
