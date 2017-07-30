package com.microsoft.oneweek.hangouter.Presenters;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.microsoft.oneweek.hangouter.Enums.POIType;
import com.microsoft.oneweek.hangouter.Interactors.GroupUsersInteractor;
import com.microsoft.oneweek.hangouter.Interactors.POIInteractor;
import com.microsoft.oneweek.hangouter.Interfaces.IMapPresenter;
import com.microsoft.oneweek.hangouter.Interfaces.IMapView;
import com.microsoft.oneweek.hangouter.Interfaces.IPOIInteractor;
import com.microsoft.oneweek.hangouter.Interfaces.IUsersInteractor;
import com.microsoft.oneweek.hangouter.Interfaces.POI;
import com.microsoft.oneweek.hangouter.Models.User;
import com.microsoft.oneweek.hangouter.Utils.HangouterUtils;

import java.util.List;

/**
 * Created by prmeno on 7/24/2017.
 */

public class HangouterMapPresenter implements IMapPresenter {

    private IMapView mMapView;
    private IUsersInteractor mUserInteractor;
    private IPOIInteractor mPOIInteractor;

    public HangouterMapPresenter(IMapView mapView) {
        mMapView = mapView;
        mUserInteractor = new GroupUsersInteractor();
        mPOIInteractor = new POIInteractor(this);
    }

    @Override
    public void fetchUserLocations() {
        for (User user : mUserInteractor.getUserQuery()) {
            mMapView.showUserLocation(user);
        }
        mMapView.zoomToShowAll();
    }

    @Override
    public void loadPOISuggestions(LatLng location, POIType poi) {
        mMapView.showFindingPOIs();
        mPOIInteractor.findPOIsQuery(location, poi);
    }

    @Override
    public void onPOISuggestionsLoaded(final List<? extends POI> pois) {
        HangouterUtils.runDelayed(new Runnable() {
            @Override
            public void run() {
                mMapView.hideFindingPOIs();
                mMapView.showPOIsReady(pois);
            }
        }, 2000);

    }
}
