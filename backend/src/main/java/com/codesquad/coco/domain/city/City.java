package com.codesquad.coco.domain.city;

public class City {

    private String name;
    private String imageUrl;

    public City(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
