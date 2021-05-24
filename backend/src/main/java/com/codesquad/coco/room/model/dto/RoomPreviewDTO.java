package com.codesquad.coco.room.model.dto;

public class RoomPreviewDTO {

    private Long id;
    private String name;
    private int pricePerDate;
    private RoomOptionDTO homeDetails;
    private String type;
    private boolean wish;
    private String thumbnailImage;
    private LocationDTO coordinate;
    private ReviewDTO review;
    private int totalPrice;

    private RoomPreviewDTO(Long id, String name, int pricePerDate, RoomOptionDTO homeDetails, String type, boolean wish, String thumbnailImage, LocationDTO coordinate, ReviewDTO reviewDTO, int totalPrice) {
        this.id = id;
        this.name = name;
        this.pricePerDate = pricePerDate;
        this.homeDetails = homeDetails;
        this.type = type;
        this.wish = wish;
        this.thumbnailImage = thumbnailImage;
        this.coordinate = coordinate;
        this.review = reviewDTO;
        this.totalPrice = totalPrice;
    }

    public static RoomPreviewDTO of(Long id, String name, int pricePerDate, RoomOptionDTO homeDetails, String type, boolean wish, String thumbnailImage, LocationDTO coordinate, ReviewDTO reviewDTO, int totalPrice) {
        return new RoomPreviewDTO(id, name, pricePerDate, homeDetails, type, wish, thumbnailImage, coordinate, reviewDTO, totalPrice);
    }


    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPricePerDate() {
        return pricePerDate;
    }

    public RoomOptionDTO getHomeDetails() {
        return homeDetails;
    }

    public String getType() {
        return type;
    }

    public boolean isWish() {
        return wish;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public LocationDTO getCoordinate() {
        return coordinate;
    }

    public ReviewDTO getReviewDTO() {
        return review;
    }

    public ReviewDTO getReview() {
        return review;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
