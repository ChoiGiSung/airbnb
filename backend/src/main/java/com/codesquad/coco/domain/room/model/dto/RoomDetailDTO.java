package com.codesquad.coco.domain.room.model.dto;

import com.codesquad.coco.domain.host.HostDTO;
import com.codesquad.coco.domain.image.ImageDTO;
import com.codesquad.coco.domain.room.model.Room;
import com.codesquad.coco.domain.user.model.dto.ReservationDateDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDetailDTO {

    private Long id;
    private String name;
    private HostDTO host;
    private RoomOptionDTO homeDetails;
    private String type;
    private int pricePerDate;
    private String description;
    private boolean wish;
    private LocationDTO coordinate;
    private List<String> images;
    private ReviewDTO review;
    private AdditionalCostDTO additionalCost;
    private List<ReservationDateDTO> nonReservationDate;

    private RoomDetailDTO(Long id, String name, HostDTO host, RoomOptionDTO homeDetails, String type, int pricePerDate,
                          String description, boolean wish, LocationDTO coordinate, List<String> images, ReviewDTO review,
                          AdditionalCostDTO additionalCost, List<ReservationDateDTO> reservationDateDTOs) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.homeDetails = homeDetails;
        this.type = type;
        this.pricePerDate = pricePerDate;
        this.description = description;
        this.wish = wish;
        this.coordinate = coordinate;
        this.images = images;
        this.review = review;
        this.additionalCost = additionalCost;
        this.nonReservationDate = reservationDateDTOs;
    }

    public static RoomDetailDTO of(Long id, String name, HostDTO host, RoomOptionDTO homeDetails, String type,
                                   int pricePerDate, String description, boolean wish, LocationDTO coordinate, List<ImageDTO> images, ReviewDTO review
            , AdditionalCostDTO additionalCost, List<ReservationDateDTO> reservationDateDTOs) {
        List<String> imageURIs = images.stream()
                .map(ImageDTO::getUrl)
                .collect(Collectors.toList());
        return new RoomDetailDTO(id, name, host, homeDetails, type, pricePerDate, description, wish, coordinate, imageURIs, review, additionalCost, reservationDateDTOs);
    }

    public static RoomDetailDTO of(Room room) {
        return RoomDetailDTO.of(
                room.getId(),
                room.getName(),
                HostDTO.of(room.getHost()),
                RoomOptionDTO.of(room.getRoomOption()),
                room.getType(),
                room.getPricePerDate(),
                room.getDescription(),
                room.getWish(),
                LocationDTO.of(room.getLocation()),
                ImageDTO.of(room.getImages()),
                ReviewDTO.of(room.getReview()),
                AdditionalCostDTO.of(room.getAdditionalCost()),
                ReservationDateDTO.of(room.getReservations().getReservations())
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HostDTO getHost() {
        return host;
    }

    public RoomOptionDTO getHomeDetails() {
        return homeDetails;
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

    public boolean isWish() {
        return wish;
    }

    public LocationDTO getCoordinate() {
        return coordinate;
    }

    public List<String> getImages() {
        return images;
    }

    public ReviewDTO getReview() {
        return review;
    }

    public AdditionalCostDTO getAdditionalCost() {
        return additionalCost;
    }

    public List<ReservationDateDTO> getNonReservationDate() {
        return nonReservationDate;
    }
}
