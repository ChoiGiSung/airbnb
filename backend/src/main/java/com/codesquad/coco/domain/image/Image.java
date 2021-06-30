package com.codesquad.coco.domain.image;

import org.springframework.data.annotation.Id;

public class Image {

    @Id
    private Long id;

    private String url;
    private String type;

    public Image(String url, String type) {
        this.url = url;
        this.type = type;
    }

    public Image(Long id, String url, String type) {
        this.id = id;
        this.url = url;
        this.type = type;
    }

    public boolean isThumbnail() {
        return type.equals("thumb");
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
