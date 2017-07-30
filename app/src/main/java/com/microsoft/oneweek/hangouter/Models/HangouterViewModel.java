package com.microsoft.oneweek.hangouter.Models;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by prmeno on 7/25/2017.
 */

public class HangouterViewModel {
    private HashMap<User, Marker> userMarkers;
    private List<Polyline> polyLines;
    private LatLng centre;

    public HangouterViewModel() {
        userMarkers = new HashMap<>();
        polyLines = new ArrayList<>();
    }

    public void addUserMarker(User user, Marker marker) {
        userMarkers.put(user, marker);
    }

    public LatLngBounds getLatLngBoundsForAll() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator iterator = userMarkers.entrySet().iterator();

        while (iterator.hasNext()) {
            builder.include(
                    ((Marker) ((Map.Entry) iterator.next()).getValue()).getPosition());
        }

        LatLngBounds bounds = builder.build();
        centre = bounds.getCenter();
        return bounds;
    }

    public LatLng getCentre() {
        return centre;
    }

    public List<LatLng> getUserPositions() {
        List<LatLng> userPositions = new ArrayList<>();
        Iterator iterator = userMarkers.entrySet().iterator();

        while (iterator.hasNext()) {
            userPositions.add(((Marker) ((Map.Entry) iterator.next()).getValue()).getPosition());
        }

        return userPositions;
    }

    public void addLine(Polyline line) {
        polyLines.add(line);
    }

    public void clearLines() {
        for (Polyline line : polyLines) {
            line.remove();
        }
        polyLines.clear();
    }
}
