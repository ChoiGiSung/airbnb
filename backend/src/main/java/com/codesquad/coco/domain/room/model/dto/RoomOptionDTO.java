package com.codesquad.coco.domain.room.model.dto;

import com.codesquad.coco.domain.room.model.RoomOption;

public class RoomOptionDTO {

    private int bed;
    private int maxGuest;
    private int bathRoom;

    public RoomOptionDTO(int bed, int maxGuest, int bathRoom) {
        this.bed = bed;
        this.maxGuest = maxGuest;
        this.bathRoom = bathRoom;
    }

    public static RoomOptionDTO of(RoomOption roomOption) {
        return new RoomOptionDTO(
                roomOption.getBed(),
                roomOption.getMaxGuest(),
                roomOption.getBathRoom()
        );
    }

    public int getBed() {
        return bed;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public int getBathRoom() {
        return bathRoom;
    }

}
