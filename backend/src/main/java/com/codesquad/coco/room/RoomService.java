package com.codesquad.coco.room;

import com.codesquad.coco.image.ImageDAO;
import com.codesquad.coco.room.model.Room;
import com.codesquad.coco.room.model.dto.*;
import com.codesquad.coco.utils.DTOConverter;
import com.codesquad.coco.utils.LocalDateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomDAO roomDAO;
    private final ImageDAO imageDAO;

    public RoomService(RoomDAO roomDAO, ImageDAO imageDAO) {
        this.roomDAO = roomDAO;
        this.imageDAO = imageDAO;
    }

    public PricesDTO findAllPriceDTO(SearchPriceDTO searchPriceDTO) {
        List<Integer> prices = roomDAO.findAllPriceBySearchPriceDTO(searchPriceDTO);
        return new PricesDTO(prices);
    }

    public List<RoomPreviewDTO> findAllRoomPreviewDTO(SearchRoomDTO roomDTO) {
        List<Room> rooms = roomDAO.findAllBySearchRoomDTO(roomDTO);

        int fewNights = LocalDateUtil.getAccommodationDay(roomDTO.getCheckIn(), roomDTO.getCheckOut());

        //fixme : ? 해당 로직 괜찮을까요?
        return rooms.stream()
                .map(room -> DTOConverter.roomToRoomPreviewDTO(room, fewNights))
                .collect(Collectors.toList());
    }

    public List<RoomPreviewDTO> findAllRoomLocationPreviewDTO(SearchRoomLocationDTO roomDTO) {
        List<Room> rooms = roomDAO.findAllBySearchLocationRoomDTO(roomDTO);

        int fewNights = LocalDateUtil.getAccommodationDay(roomDTO.getCheckIn(), roomDTO.getCheckOut());

        //fixme : ? 해당 로직 괜찮을까요?
        return rooms.stream()
                .map(room -> DTOConverter.roomToRoomPreviewDTO(room, fewNights))
                .collect(Collectors.toList());
    }

    public RoomDetailDTO findRoomDetailDTO(Long roomId) {
        Room room = roomDAO.findById(roomId);
        return DTOConverter.roomToRoomDetailDTO(room);
    }
}
