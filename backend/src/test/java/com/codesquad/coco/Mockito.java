package com.codesquad.coco;

import com.codesquad.coco.global.ApplicationContextServe;
import com.codesquad.coco.image.ImageDAO;
import com.codesquad.coco.oauth.ServerKey;
import com.codesquad.coco.room.RoomDAO;
import com.codesquad.coco.room.RoomService;
import com.codesquad.coco.room.model.*;
import com.codesquad.coco.room.model.dto.PricesDTO;
import com.codesquad.coco.room.model.dto.RoomPreviewDTO;
import com.codesquad.coco.room.model.dto.SearchPriceDTO;
import com.codesquad.coco.room.model.dto.SearchRoomDTO;
import com.codesquad.coco.user.model.WishList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@WebMvcTest(RoomService.class)
public class Mockito {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomDAO roomDAO;

    @MockBean
    private ImageDAO imageDAO;

    @MockBean
    private ServerKey serverKey;

    @MockBean
    private ApplicationContextServe applicationContextServe;


    @Test
    @DisplayName("검색시 값 목록을 얻어야함")
    void findPriceDTO() throws Exception {
        //given
        SearchPriceDTO search = new SearchPriceDTO("2021-03-04", "2021-03-04", "서울");
        List<Integer> prices = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        given(roomDAO.findAllPriceBySearchPriceDTO(search)).willReturn(prices);

        //when
        PricesDTO allPriceDTO = roomService.findAllPriceDTO(search);

        //then
        assertThat(allPriceDTO.getPrices().get(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("")
    void findAllRoomPreviewDTO() {
        //given
        SearchRoomDTO seoul = new SearchRoomDTO("2021-03-04", "2021-03-04", "서울", 1, 1, 1, 100, 10000);
        RoomOption roomOption = new RoomOption(1, 2, 3);
        AdditionalCost additionalCost = new AdditionalCost(1, 2, 3, 4);
        WishList wishList = new WishList(1L, 1L, true);
        Location location = new Location(2.3, 23.321);
        Review review = new Review(2.4, 2.4);
        Room room1 = new Room.Builder().id(1L).description("안녕하세여").name("우리 집").review(review).location(location).wishList(wishList).pricePerDate(new Money(100)).additionalCost(additionalCost).roomOption(roomOption).build();
        Room room2 = new Room.Builder().id(2L).description("안녕하세여").name("우리 집").review(review).location(location).wishList(wishList).pricePerDate(new Money(100)).additionalCost(additionalCost).roomOption(roomOption).build();
        Room room3 = new Room.Builder().id(3L).description("안녕하세여").name("우리 집").review(review).location(location).wishList(wishList).pricePerDate(new Money(100)).additionalCost(additionalCost).roomOption(roomOption).build();


        given(roomDAO.findAllBySearchRoomDTO(seoul)).willReturn(Arrays.asList(room1, room2, room3));

        //when
        List<RoomPreviewDTO> allRoomPreviewDTO = roomService.findAllRoomPreviewDTO(seoul);

        //then
        for (int i = 0; i < allRoomPreviewDTO.size(); i++) {
            RoomPreviewDTO roomPreviewDTO = allRoomPreviewDTO.get(i);
            assertThat(roomPreviewDTO.getId()).isEqualTo(i + 1);
        }
    }


}
