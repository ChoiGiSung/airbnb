package com.codesquad.coco.domain.image;

import java.util.List;
import java.util.stream.Collectors;

public class ImageDTO {

    private String url;
    private String type;

    public ImageDTO(String url, String type) {
        this.url = url;
        this.type = type;
    }

    public static ImageDTO of(Image image) {
        return new ImageDTO(
                image.getUrl(),
                image.getType()
        );
    }

    public static List<ImageDTO> of(List<Image> images) {
        return images.stream()
                .map(ImageDTO::of)
                .collect(Collectors.toList());
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }
}
