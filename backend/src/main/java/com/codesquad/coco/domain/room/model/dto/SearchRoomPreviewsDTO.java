package com.codesquad.coco.domain.room.model.dto;

import java.util.List;

public class SearchRoomPreviewsDTO {

    private List<RoomPreviewDTO> rooms;

    public SearchRoomPreviewsDTO(List<RoomPreviewDTO> rooms) {
        this.rooms = rooms;
    }

    public List<RoomPreviewDTO> getRooms() {
        return rooms;
    }
}
