package com.codesquad.coco.domain.room.model;

import com.codesquad.coco.global.exception.business.OvercapacityException;

public class RoomOption {

    private int bed;
    private int maxGuest;
    private int bathRoom;

    public RoomOption(int bed, int maxGuest, int bathRoom) {
        this.bed = bed;
        this.maxGuest = maxGuest;
        this.bathRoom = bathRoom;
    }

    public boolean capacityCheck(int adult, int child) {
        if (maxGuest < adult + child) {
            throw new OvercapacityException();
        }
        return true;
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


    @Override
    public String toString() {
        return "RoomOption{" +
                "bed=" + bed +
                ", maxGuest=" + maxGuest +
                ", bathRoom=" + bathRoom +
                '}';
    }


}
