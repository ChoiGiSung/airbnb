package com.codesquad.coco.domain.city;

import java.util.ArrayList;
import java.util.List;

public class CitiesDTO {

    private List<CityDTO> cities = new ArrayList<>();

    public CitiesDTO(List<CityDTO> city) {
        this.cities = city;
    }

    public List<CityDTO> getCities() {
        return cities;
    }
}
