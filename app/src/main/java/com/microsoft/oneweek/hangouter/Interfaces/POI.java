package com.microsoft.oneweek.hangouter.Interfaces;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;

/**
 * Created by prmeno on 7/25/2017.
 */

public interface POI {
    public String getName();
    public float getRating();
    public Location getLocation();
    public String getInfoOnType();
    public Uri getThumbnailUri();
    public Intent getAsIntent();
}
