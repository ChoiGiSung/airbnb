package com.codesquad.coco.domain.room.model.dto;

import com.codesquad.coco.domain.room.model.Location;

public class LocationDTO {

    private double latitude;
    private double longitude;

    public LocationDTO(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static LocationDTO of(Location location) {
        return new LocationDTO(
                location.getLatitude(),
                location.getLongitude()
        );
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
