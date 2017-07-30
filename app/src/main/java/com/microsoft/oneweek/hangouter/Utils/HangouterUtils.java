package com.microsoft.oneweek.hangouter.Utils;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;

import com.androidnetworking.AndroidNetworking;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.microsoft.oneweek.hangouter.Models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by prmeno on 7/24/2017.
 */

public class HangouterUtils {
    public static String KEY_POI_NAME = "name";
    public static String KEY_POI_LAT = "lat";
    public static String KEY_POI_LON = "lon";
    public static String KEY_POI_RATING = "rating";
    public static String KEY_POI_ADDRESS = "address";

    public static List<User> fetchDummyUsers() {
        ArrayList<User> dummyUsers = new ArrayList<>();

        dummyUsers.add(generateUser("Tony", 12.934, 77.612));
        dummyUsers.add(generateUser("Mark", 12.930, 77.684));
        dummyUsers.add(generateUser("Shane", 12.999, 77.672));
        return dummyUsers;
    }

    public static MarkerOptions generateMarkerOpetions(User user) {
        LatLng latLng = new LatLng(user.getLocation().getLatitude(), user.getLocation().getLongitude());
        return new MarkerOptions()
                .position(latLng)
                .title(user.getName());
    }

    public static void InitializeNetworkClient(Context context) {
        AndroidNetworking.initialize(context);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(context, okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
    }

    public static PolylineOptions generateLineOptions(LatLng fromPoint, LatLng toPoint) {
        List<PatternItem> pattern = Arrays.<PatternItem>asList(
                new Dot(), new Gap(20), new Dash(30), new Gap(20));
        return new PolylineOptions()
                .add(fromPoint, toPoint)
                .width(5)
                .color(Color.BLUE)
                .pattern(pattern)
                .geodesic(true)
                .jointType(JointType.ROUND);
    }

    public static void runDelayed(Runnable runnable, long millis) {
        final Handler handler = new Handler();
        handler.postDelayed(runnable, millis);
    }

    private static Location generateLocation(double latitude, double longitude) {
        Location location1 = new Location("");
        location1.setLatitude(latitude);
        location1.setLongitude(longitude);
        return location1;
    }

    private static User generateUser(String name, Double latitude, Double longitude) {
        User user = new User();
        user.setName(name);
        user.setLocation(generateLocation(latitude, longitude));
        return user;
    }
}
