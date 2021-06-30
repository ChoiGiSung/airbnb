package com.codesquad.coco.domain.room.model;

import com.codesquad.coco.global.exception.business.AlreadyReserved;
import com.codesquad.coco.domain.user.model.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationList {

    private List<Reservation> reservations = new ArrayList<>();

    public ReservationList(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean reservationDateCheck(LocalDate checkIn, LocalDate checkOut) {
        for (Reservation reservation : reservations) {
            if (reservation.reservationDateCheck(checkIn, checkOut)) {
                throw new AlreadyReserved();
            }
        }
        return true;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }


}
