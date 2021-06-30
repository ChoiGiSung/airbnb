package com.codesquad.coco.domain.user.model;

import org.springframework.data.annotation.Id;

public class WishList {

    @Id
    private Long id;

    private Long userId;
    private Long roomId;
    private boolean wish;

    public WishList(Long userId, Long roomId, boolean wish) {
        this.userId = userId;
        this.roomId = roomId;
        this.wish = wish;
    }

    public boolean isWish() {
        return wish;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoomId() {
        return roomId;
    }
}
