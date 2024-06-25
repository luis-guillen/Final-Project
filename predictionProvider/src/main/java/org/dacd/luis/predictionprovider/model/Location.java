package org.dacd.luis.predictionprovider.model;

public class Location {
    private String name;
    private double lat;
    private double longitude;

    public Location(String name, double lat, double longitude) {
        this.name = name;
        this.lat = lat;
        this.longitude = longitude;
    }

    public double getLat() {
        return lat;
    }

    public double getLongitude() {
        return longitude;
    }

}
