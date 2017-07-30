package com.microsoft.oneweek.hangouter.Models.JSONWrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by prmeno on 7/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoRatingWrapper {
    private float aggregate_rating;
    private String rating_text;

    public float getRating(){
        return aggregate_rating;
    }
}
