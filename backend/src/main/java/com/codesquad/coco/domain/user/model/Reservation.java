package com.codesquad.coco.domain.user.model;

import com.codesquad.coco.domain.room.model.Money;
import com.codesquad.coco.utils.LocalDateUtil;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Reservation {

    @Id
    private Long id;

    private User user;
    private int adult;
    private int child;
    private int baby;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Money totalPrice;

    private Reservation(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.adult = builder.adult;
        this.child = builder.child;
        this.baby = builder.baby;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.totalPrice = builder.totalPrice;
    }

    public boolean reservationDateCheck(LocalDate checkIn, LocalDate checkOut) {
        //todo : 체크 아웃 날에는 체크인이 가능하다?
        return (LocalDateUtil.betweenCheck(this.checkIn, checkIn, checkOut) ||
                LocalDateUtil.betweenCheck(this.checkOut, checkIn, checkOut) ||
                this.checkIn.isEqual(checkIn) ||
                this.checkOut.isEqual(checkOut));
    }


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getAdult() {
        return adult;
    }

    public int getChild() {
        return child;
    }

    public int getBaby() {
        return baby;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public int getTotalPrice() {
        return totalPrice.getMoney();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", adult=" + adult +
                ", child=" + child +
                ", baby=" + baby +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private Long id;
        private User user;
        private int adult;
        private int child;
        private int baby;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Money totalPrice;

        public Builder id(Long val) {
            this.id = val;
            return this;
        }

        public Builder user(User val) {
            this.user = val;
            return this;
        }

        public Builder adult(int val) {
            this.adult = val;
            return this;
        }

        public Builder child(int val) {
            this.child = val;
            return this;
        }

        public Builder baby(int val) {
            this.baby = val;
            return this;
        }

        public Builder checkIn(LocalDate val) {
            this.checkIn = val;
            return this;
        }

        public Builder checkOut(LocalDate val) {
            this.checkOut = val;
            return this;
        }

        public Builder totalPrice(Money val) {
            this.totalPrice = val;
            return this;
        }

        public Reservation builder() {
            return new Reservation(this);
        }
    }
}
