package com.codesquad.coco.domain.user.model.dto;

import com.codesquad.coco.domain.user.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDateDTO {

    private LocalDate checkIn;
    private LocalDate checkOut;

    public ReservationDateDTO(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public static ReservationDateDTO of(Reservation reservation) {
        return new ReservationDateDTO(
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );
    }

    public static List<ReservationDateDTO> of(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationDateDTO::of)
                .collect(Collectors.toList());
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
}
