package com.codesquad.coco.domain.room.model;

import com.codesquad.coco.domain.city.City;
import org.springframework.data.annotation.Id;

public class Location {

    @Id
    private Long id;

    private Coordinates latitude;
    private Coordinates longitude;
    private City city;

    public Location(double latitude, double longitude) {
        this.latitude = new Coordinates(latitude);
        this.longitude = new Coordinates(longitude);
    }

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude.getCoordinates();
    }

    public double getLongitude() {
        return longitude.getCoordinates();
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city=" + city +
                '}';
    }
}
