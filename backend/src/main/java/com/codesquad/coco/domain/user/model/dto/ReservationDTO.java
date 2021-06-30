package com.codesquad.coco.domain.user.model.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservationDTO {

    @NotNull
    private LocalDate checkIn;
    @NotNull
    private LocalDate checkOut;
    @NotNull
    private int totalPrice;
    @NotNull
    private int adult;
    @NotNull
    private int child;
    @NotNull
    private int baby;


    public int accommodationDay() {
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public int getTotalPrice() {
        return totalPrice;
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

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalPrice=" + totalPrice +
                ", adult=" + adult +
                ", child=" + child +
                ", baby=" + baby +
                '}';
    }
}
