package com.codesquad.coco.service;

import com.codesquad.coco.domain.image.ImageDAO;
import com.codesquad.coco.domain.room.RoomDAO;
import com.codesquad.coco.domain.room.model.Room;
import com.codesquad.coco.domain.room.model.dto.*;
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

        return rooms.stream()
                .map(room -> RoomPreviewDTO.of(room, fewNights))
                .collect(Collectors.toList());
    }

    public RoomDetailDTO findRoomDetailDTO(Long roomId) {
        Room room = roomDAO.findById(roomId);
        return RoomDetailDTO.of(room);
    }
}
