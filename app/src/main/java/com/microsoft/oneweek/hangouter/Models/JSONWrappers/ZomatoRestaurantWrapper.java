package com.microsoft.oneweek.hangouter.Models.JSONWrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsoft.oneweek.hangouter.Models.Restaurant;

/**
 * Created by prmeno on 7/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoRestaurantWrapper {
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
