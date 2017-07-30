package com.microsoft.oneweek.hangouter.Interfaces;

import com.microsoft.oneweek.hangouter.Models.User;

import java.util.List;

/**
 * Created by prmeno on 7/24/2017.
 */

public interface IMapView {
    public void showUserLocation(User user);
    public void showLoadingUsers();
    public void hideLoadingUsers();
    public void showFindingPOIs();
    public void hideFindingPOIs();
    public void showPOIsReady(List<? extends POI> pois);
    public void zoomToShowAll();
}
