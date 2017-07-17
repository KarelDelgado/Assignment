package com.assignment.example.top10posts.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by karel on 7/15/2017.
 */

public class Settings {

    public static final String SETTINGS = "settings_preferences";
    public static final String SEARCH_DISTANCE = "search_distance";
    public static final int DEFAULT_DISTANCE = 500;
    public static final String SEARCH_LAT = "search_latitude";
    public static final String SEARCH_LNG = "search_longitude";
    public static final long DEFAULT_LAT = 0;
    public static final long DEFAULT_LNG = 0;
    public static final String LOCATION_AVAILABLE = "location_available";
    public static final boolean DEFAULT_LOCATION_AVAILABLE = false;
    private SharedPreferences sharedPreferences;

    public Settings(Context context) {
        sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
    }

    public int getDistance() {
        return sharedPreferences.getInt(SEARCH_DISTANCE, DEFAULT_DISTANCE);
    }

    public int getVisibleDistance() {
        return sharedPreferences.getInt(SEARCH_DISTANCE, DEFAULT_DISTANCE) - DEFAULT_DISTANCE;
    }

    public void saveDistance(int distance) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SEARCH_DISTANCE, distance + DEFAULT_DISTANCE);
        editor.apply();
    }

    public LatLng getLocation() {
        double latitude = Double.longBitsToDouble(sharedPreferences.getLong(SEARCH_LAT, DEFAULT_LAT));
        double longitude = Double.longBitsToDouble(sharedPreferences.getLong(SEARCH_LNG, DEFAULT_LNG));
        return new LatLng(latitude, longitude);
    }

    public void saveLocation(Location location) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SEARCH_LAT, Double.doubleToLongBits(location.getLatitude()));
        editor.putLong(SEARCH_LNG, Double.doubleToLongBits(location.getLongitude()));
        editor.putBoolean(LOCATION_AVAILABLE, true);
        editor.apply();
    }

    public boolean isLocationAvailable() {
        return sharedPreferences.getBoolean(LOCATION_AVAILABLE, DEFAULT_LOCATION_AVAILABLE);
    }
}
