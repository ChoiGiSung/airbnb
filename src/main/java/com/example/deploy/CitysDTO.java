package com.example.deploy;

import java.util.ArrayList;
import java.util.List;

public class CitysDTO {

    private List<City> city = new ArrayList<>();

    public void addCity(City city){
        this.city.add(city);
    }

    public List<City> getCity() {
        return city;
    }



    public static class City{
        private String name;
        private String image;

        public City(String name, String image) {
            this.name = name;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }
    }
}
