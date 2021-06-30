package com.codesquad.coco.domain.user;

import com.codesquad.coco.domain.user.model.ReservationStatus;
import com.codesquad.coco.domain.user.model.dto.ReservationDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import static com.codesquad.coco.utils.sql.ReservationSQLKt.DELETE_RESERVATION;
import static com.codesquad.coco.utils.sql.ReservationSQLKt.INSERT_RESERVATION;

@Component
public class ReservationDAO {

    private NamedParameterJdbcTemplate template;

    public ReservationDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void reservation(Long roomId, Long userId, ReservationDTO reservationDTO, ReservationStatus status) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("room_id", roomId)
                .addValue("check_in", reservationDTO.getCheckIn())
                .addValue("check_out", reservationDTO.getCheckOut())
                .addValue("total_price", reservationDTO.getTotalPrice())
                .addValue("adult", reservationDTO.getAdult())
                .addValue("child", reservationDTO.getChild())
                .addValue("baby", reservationDTO.getBaby())
                .addValue("user_id", userId)
                .addValue("status", status.name());
        //todo : oauth 완료하고 userId도 넣어야함
        template.update(INSERT_RESERVATION, parameter);
    }

    public void cancelReservation(Long roomId, Long reservationId, Long userId, ReservationStatus status) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("room_id", roomId)
                .addValue("reservation_id", reservationId)
                .addValue("user_id", userId)
                .addValue("status", status.name());

        template.update(DELETE_RESERVATION, parameter);
    }
}
