package com.microsoft.oneweek.hangouter.Interfaces;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.microsoft.oneweek.hangouter.Enums.POIType;

import java.util.List;

/**
 * Created by prmeno on 7/24/2017.
 */

public interface IMapPresenter {
    public void fetchUserLocations();
    public void loadPOISuggestions(LatLng location, POIType poi);
    public void onPOISuggestionsLoaded(List<? extends POI> pois);
}
