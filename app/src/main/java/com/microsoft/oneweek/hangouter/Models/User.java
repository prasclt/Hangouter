package com.microsoft.oneweek.hangouter.Models;

import android.location.Location;
import android.net.Uri;

/**
 * Created by prmeno on 7/24/2017.
 */

public class User {
    private String id;
    private String name;
    private Location location;
    private Uri profilePicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Uri getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Uri profilePicture) {
        this.profilePicture = profilePicture;
    }
}
