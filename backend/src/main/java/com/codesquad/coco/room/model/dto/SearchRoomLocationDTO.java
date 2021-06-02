package com.codesquad.coco.room.model.dto;

import java.beans.ConstructorProperties;

public class SearchRoomLocationDTO extends SearchRoomDTO {

    private double latitude;
    private double longitude;

    @ConstructorProperties({"check-in", "check-out", "city-name", "adult", "child", "baby", "price-min", "price-max", "latitude", "longitude"})

    public SearchRoomLocationDTO(String checkIn, String checkOut, String cityName, Integer adult, Integer child, Integer baby, Integer priceMin, Integer priceMax, double latitude, double longitude) {
        super(checkIn, checkOut, cityName, adult, child, baby, priceMin, priceMax);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
