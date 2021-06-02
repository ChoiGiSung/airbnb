package com.codesquad.coco.room;

import com.codesquad.coco.room.model.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    //todo 버저닝 알려주기
    @GetMapping("/search/prices")
    public PricesDTO searchAllPrice(SearchPriceDTO searchPriceDTO) {
        logger.debug(searchPriceDTO.toString());
        return roomService.findAllPriceDTO(searchPriceDTO);
    }

    @GetMapping("/search")
    public SearchRoomPreviewsDTO searchAllRoom(SearchRoomDTO roomDTO) {
        List<RoomPreviewDTO> roomPreviewDTOs = roomService.findAllRoomPreviewDTO(roomDTO);
        return new SearchRoomPreviewsDTO(roomPreviewDTOs);
    }

    @GetMapping("/search/location")
    public SearchRoomPreviewsDTO searchAllRoomByLocation(SearchRoomLocationDTO roomDTO) {
        List<RoomPreviewDTO> roomPreviewDTOs = roomService.findAllRoomLocationPreviewDTO(roomDTO);
        return new SearchRoomPreviewsDTO(roomPreviewDTOs);
    }

    @GetMapping("/rooms/{roomId}")
    public RoomDetailDTO findRoomDetail(@PathVariable Long roomId) {
        return roomService.findRoomDetailDTO(roomId);
    }
}
