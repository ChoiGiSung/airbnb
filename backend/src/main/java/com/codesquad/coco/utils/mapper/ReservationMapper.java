package com.codesquad.coco.utils.mapper;

import com.codesquad.coco.domain.room.model.Money;
import com.codesquad.coco.domain.user.model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Reservation.Builder()
                .id(rs.getLong("rv_id"))
                .adult(rs.getInt("rv_adult"))
                .child(rs.getInt("rv_child"))
                .baby(rs.getInt("rv_baby"))
                .checkIn(rs.getDate("rv_check_in").toLocalDate())
                .checkOut(rs.getDate("rv_check_out").toLocalDate())
                .totalPrice(new Money(
                        rs.getInt("rv_total_price")
                ))
                .builder();
    }
}
