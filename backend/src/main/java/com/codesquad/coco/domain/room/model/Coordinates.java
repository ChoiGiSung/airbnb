package com.codesquad.coco.domain.room.model;

public class Coordinates {

    private final double coordinates;

    public Coordinates(double coordinates) {
        this.coordinates = coordinates;
    }

    public double getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coordinates=" + coordinates +
                '}';
    }
}
