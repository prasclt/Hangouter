package com.microsoft.oneweek.hangouter.Models.JSONWrappers;

import android.location.Location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by prmeno on 7/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoLocationWrapper {
    private Double latitude;
    private Double longitude;
    private String address;

    public Location getLocation() {
        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }

    public String getAddress() {
        return address;
    }
}
