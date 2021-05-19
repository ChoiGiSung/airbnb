package com.example.deploy;

public class Host {
    private String name;
    private String image;

    public Host(String name, String image) {
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
