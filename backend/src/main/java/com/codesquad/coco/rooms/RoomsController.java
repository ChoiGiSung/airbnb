package com.codesquad.coco.rooms;

import com.codesquad.coco.rooms.model.Rooms;
import com.codesquad.coco.rooms.model.dto.*;
import com.codesquad.coco.utils.DTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping("/search/price")
    public PricesDTO searchPrice(SearchPriceDTO searchPriceDTO) {
        logger.debug(searchPriceDTO.toString());
        return roomsService.findPricesBySearchPrice(searchPriceDTO);
    }

    @GetMapping("/search")
    public SearchRoomsListDTO searchRooms(SearchRoomsDTO roomsDTO) {

        List<Rooms> roomsList = roomsService.findRoomsBySearchRooms(roomsDTO);
        List<RoomsListDTO> roomsListDTOs = roomsList.stream().map(DTOConverter::roomsToRoomsListDTO)
                .collect(Collectors.toList());
        for (Rooms rooms : roomsList) {
            DTOConverter.roomsToRoomsListDTO(rooms);
            logger.debug(rooms.toString());
        }
        return new SearchRoomsListDTO(roomsListDTOs);
    }

    @GetMapping("/rooms/{roomsId}")
    public RoomsDetailDTO roomsDetail(@PathVariable Long roomsId) {
        Rooms rooms = roomsService.findRoomsByRoomsId(roomsId);
        return DTOConverter.roomsToRoomsDetailDTO(rooms);
    }
}
