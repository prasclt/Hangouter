package com.microsoft.oneweek.hangouter.Interfaces;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.microsoft.oneweek.hangouter.Enums.POIType;

import java.util.List;

/**
 * Created by prmeno on 7/25/2017.
 */

public interface IPOIInteractor {
    public List<POI> findPOIsQuery(LatLng location, POIType poiType);
}
