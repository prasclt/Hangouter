package com.microsoft.oneweek.hangouter.Interactors;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.microsoft.oneweek.hangouter.Enums.POIType;
import com.microsoft.oneweek.hangouter.Interfaces.IMapPresenter;
import com.microsoft.oneweek.hangouter.Interfaces.IPOIInteractor;
import com.microsoft.oneweek.hangouter.Interfaces.POI;
import com.microsoft.oneweek.hangouter.Models.JSONWrappers.ZomatoRestaurantJSONDataHolder;
import com.microsoft.oneweek.hangouter.Models.Restaurant;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by prmeno on 7/25/2017.
 */

public class POIInteractor implements IPOIInteractor {

    private IMapPresenter mPresenter;

    public POIInteractor(IMapPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public List<POI> findPOIsQuery(LatLng latLng, POIType poiType) {
        switch (poiType) {
            case Restaurant:
                findRestaurants(latLng);
        }
        return null;
    }

    private void findRestaurants(LatLng latLng) {

        AndroidNetworking.get("https://developers.zomato.com/api/v2.1/search")
                .addQueryParameter("lat", String.valueOf(latLng.latitude))
                .addQueryParameter("lon", String.valueOf(latLng.longitude))
                .addQueryParameter("radius", "500")
                .addHeaders("user-key", "59f30e06069b0b578362b7005520d27f")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {

                    @Override
                    public void onResponse(String response) {
                        ZomatoRestaurantJSONDataHolder dataholder = new Gson().fromJson(response, ZomatoRestaurantJSONDataHolder.class);
                        List<Restaurant> restaurants = dataholder.getRestaurants();
                        Collections.sort(restaurants);
                        mPresenter.onPOISuggestionsLoaded(restaurants);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
