package com.example.deploy;

import java.util.ArrayList;
import java.util.List;

public class RoomsDetail {

    private Long id;
    private String name;
    private Host host;
    private Rooms.HomeDetail homeDetail;
    private String type;
    private int pricePerDate;
    private String description;
    private String cityName;
    private Rooms.Coordinate coordinate;
    private List<String> image = new ArrayList<>();
    private Review review;
    private AdditionalCost additionalCost;


    public RoomsDetail(Long id, String name, Host host, Rooms.HomeDetail homeDetail,
                       String type, int pricePerDate, String description, String cityName,
                       Rooms.Coordinate coordinate, Review review, AdditionalCost additionalCost) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.homeDetail = homeDetail;
        this.type = type;
        this.pricePerDate = pricePerDate;
        this.description = description;
        this.cityName = cityName;
        this.coordinate = coordinate;
        this.review = review;
        this.additionalCost = additionalCost;
    }

    public Review getReview() {
        return review;
    }

    public AdditionalCost getAdditionalCost() {
        return additionalCost;
    }

    public void imageAdd(String image){
        this.image.add(image);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Host getHost() {
        return host;
    }

    public Rooms.HomeDetail getHomeDetail() {
        return homeDetail;
    }

    public String getType() {
        return type;
    }

    public int getPricePerDate() {
        return pricePerDate;
    }

    public String getDescription() {
        return description;
    }

    public String getCityName() {
        return cityName;
    }

    public Rooms.Coordinate getCoordinate() {
        return coordinate;
    }

    public List<String> getImage() {
        return image;
    }
}
