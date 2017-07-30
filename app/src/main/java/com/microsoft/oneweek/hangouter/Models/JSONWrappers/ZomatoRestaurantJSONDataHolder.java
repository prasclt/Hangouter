package com.microsoft.oneweek.hangouter.Models.JSONWrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsoft.oneweek.hangouter.Models.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prmeno on 7/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoRestaurantJSONDataHolder {
    private List<ZomatoRestaurantWrapper> restaurants;

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        for (ZomatoRestaurantWrapper restaurantWrapper : restaurants) {
            restaurantList.add(restaurantWrapper.getRestaurant());
        }
        return restaurantList;
    }
}

