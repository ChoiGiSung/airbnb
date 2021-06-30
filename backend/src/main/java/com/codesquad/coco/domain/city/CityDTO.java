package com.codesquad.coco.domain.city;

public class CityDTO {

    private String name;
    private String imageUrl;

    public CityDTO(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static CityDTO of(City city) {
        return new CityDTO(city.getName(), city.getImageUrl());
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
