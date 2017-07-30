package com.microsoft.oneweek.hangouter.Models;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsoft.oneweek.hangouter.Interfaces.POI;
import com.microsoft.oneweek.hangouter.Models.JSONWrappers.ZomatoLocationWrapper;
import com.microsoft.oneweek.hangouter.Models.JSONWrappers.ZomatoRatingWrapper;
import com.microsoft.oneweek.hangouter.Utils.HangouterUtils;

/**
 * Created by prmeno on 7/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant implements POI, Comparable<Restaurant> {
    private String id;
    private String name;
    private String cuisines;
    private ZomatoLocationWrapper location;
    private ZomatoRatingWrapper user_rating;
    private String thumb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Location getLocation() {
        return location.getLocation();
    }

    @Override
    public String getInfoOnType() {
        return cuisines;
    }

    @Override
    public Uri getThumbnailUri() {
        return Uri.parse(thumb);
    }

    @Override
    public Intent getAsIntent() {
        return new Intent().putExtra(HangouterUtils.KEY_POI_NAME, name)
                .putExtra(HangouterUtils.KEY_POI_LAT, location.getLocation().getLatitude())
                .putExtra(HangouterUtils.KEY_POI_LON, location.getLocation().getLongitude())
                .putExtra(HangouterUtils.KEY_POI_RATING, getRating())
                .putExtra(HangouterUtils.KEY_POI_ADDRESS, location.getAddress());
    }

    @Override
    public float getRating() {
        return user_rating.getRating();
    }

    @Override
    public int compareTo(@NonNull Restaurant restaurant) {
        return this.user_rating.getRating() > restaurant.user_rating.getRating()
                ? -1 : 1;
    }
}
