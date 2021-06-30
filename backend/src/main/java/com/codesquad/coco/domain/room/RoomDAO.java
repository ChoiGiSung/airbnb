package com.codesquad.coco.domain.room;

import com.codesquad.coco.global.exception.business.notfound.NotFoundRoomException;
import com.codesquad.coco.domain.image.ImageDAO;
import com.codesquad.coco.domain.room.model.Room;
import com.codesquad.coco.domain.room.model.dto.SearchPriceDTO;
import com.codesquad.coco.domain.room.model.dto.SearchRoomDTO;
import com.codesquad.coco.domain.user.model.Reservation;
import com.codesquad.coco.utils.mapper.ReservationMapper;
import com.codesquad.coco.utils.mapper.RoomMapper;
import com.codesquad.coco.utils.mapper.SearchPriceMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codesquad.coco.utils.sql.PriceSQLKt.FIND_ALL_PRICE_BY_SEARCH_REQUIREMENT;
import static com.codesquad.coco.utils.sql.ReservationSQLKt.FIND_ALL_RESERVATION_BY_ROOM_ID;
import static com.codesquad.coco.utils.sql.RoomSQLKt.FIND_ALL_ROOM_BY_SEARCH_REQUIREMENT;
import static com.codesquad.coco.utils.sql.RoomSQLKt.FIND_ROOM_BY_ROOM_ID;


@Component
public class RoomDAO {

    private NamedParameterJdbcTemplate template;
    private ImageDAO imageDAO;

    public RoomDAO(NamedParameterJdbcTemplate template, ImageDAO imageDAO) {
        this.template = template;
        this.imageDAO = imageDAO;
    }

    public List<Integer> findAllPriceBySearchPriceDTO(SearchPriceDTO dto) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("check_in", dto.getCheckIn())
                .addValue("check_out", dto.getCheckOut())
                .addValue("city_name", dto.getCityName());
        return template.query(FIND_ALL_PRICE_BY_SEARCH_REQUIREMENT, parameter, new SearchPriceMapper());

    }

    public List<Room> findAllBySearchRoomDTO(SearchRoomDTO roomDTO) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("check_in", roomDTO.getCheckIn())
                .addValue("check_out", roomDTO.getCheckOut())
                .addValue("city_name", roomDTO.getCityName())
                .addValue("adult", roomDTO.getAdult())
                .addValue("child", roomDTO.getChild())
                .addValue("baby", roomDTO.getBaby())
                .addValue("price_min", roomDTO.getPriceMin())
                .addValue("price_max", roomDTO.getPriceMax());
        List<Room> rooms = template.query(FIND_ALL_ROOM_BY_SEARCH_REQUIREMENT, parameter, new RoomMapper());
        for (Room room : rooms) {
            //todo n+1 을 만들것인가?
            fillRoomWithImage(room);
            fillRoomWithReservation(room);
        }
        return rooms;
    }


    public Room findById(Long roomId) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("room_id", roomId);
        List<Room> rooms = template.query(FIND_ROOM_BY_ROOM_ID, parameter, new RoomMapper());
        Room room = rooms.stream().findFirst().orElseThrow(NotFoundRoomException::new);
        fillRoomWithImage(room);
        fillRoomWithReservation(room);
        return room;
    }

    private void fillRoomWithImage(Room room) {
        Long id = room.getId();
        imageDAO.findAllImageByRoomId(id).forEach(room::addImages);
    }

    private void fillRoomWithReservation(Room room) {
        Long id = room.getId();
        findAllReservationByRoomId(id).forEach(room::addReservation);
    }

    public List<Reservation> findAllReservationByRoomId(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("room_id", id);
        return template.query(FIND_ALL_RESERVATION_BY_ROOM_ID, parameter, new ReservationMapper());
    }

}
