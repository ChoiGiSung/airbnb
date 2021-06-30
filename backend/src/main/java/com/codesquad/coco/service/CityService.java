package com.codesquad.coco.service;

import com.codesquad.coco.domain.city.City;
import com.codesquad.coco.domain.city.CityDAO;
import com.codesquad.coco.domain.city.CityDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private CityDAO cityDAO;

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<CityDTO> findAllCityDTOs() {
        List<City> cities = cityDAO.findAll();
        return cities.stream()
                .map(CityDTO::of)
                .collect(Collectors.toList());
    }

}
