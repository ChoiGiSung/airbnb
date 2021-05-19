package com.example.deploy;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("/search/price")
    public SearchPriceDTO searchPrice(@RequestParam(value = "check-in", required = false) String checkIn,
                                     @RequestParam(value = "check-out", required = false) String checkOut,
                                     @RequestParam(value = "city-name", required = false) String cityName) {

        if(checkIn != null){
            return new SearchPriceDTO(checkIn,checkOut);
        }
        if(cityName != null){
            return new SearchPriceDTO(cityName);
        }

        return new SearchPriceDTO(null);
    }

    @GetMapping("/search")
    public RoomsDTO searchReal(@RequestParam(value = "check_in", required = false) String checkIn,
                                     @RequestParam(value = "check_out", required = false) String checkOut,
                                     @RequestParam(value = "city_name", required = false) String cityName,
                                     @RequestParam(value = "adult", required = false) String adult,
                                     @RequestParam(value = "child", required = false) String child,
                                     @RequestParam(value = "baby", required = false) String baby,
                                     @RequestParam(value = "price_min", required = false) String price_min,
                                     @RequestParam(value = "price_max", required = false) String price_max) {

        //todo 검증 로직 4가지 경우에 대해 일단은 목이니 그냥 보여주자

        Rooms rooms1 = new Rooms(1L,"살며시귀기울이면#심장소리만큼 하이얀 파도소리가 들려요 #영진",960200,new Rooms.HomeDetail(3,1,2)
        ,"아파트",true,"https://a0.muscache.com/im/pictures/d9fb7454-fd02-4faf-8712-aa1f84f0f60f.jpg?im_w=720",
                new Rooms.Coordinate(37.94032,128.78549),
                new Review(4.8,124));
        Rooms room2 = new Rooms(2L,"[블루테라] 오션 #5, 속초더블루마크, 오션뷰, 속초해수욕장 도보 5분",960200,new Rooms.HomeDetail(3,1,2)
                ,"아파트",false,"https://a0.muscache.com/im/pictures/d9fb7454-fd02-4faf-8712-aa1f84f0f60f.jpg?im_w=720",
                new Rooms.Coordinate(37.55222,126.97341),
                new Review(4.8,124));
        Rooms room3 = new Rooms(3L,"JJ워커스",960200,new Rooms.HomeDetail(3,1,2)
                ,"아파트",true,"https://a0.muscache.com/im/pictures/d9fb7454-fd02-4faf-8712-aa1f84f0f60f.jpg?im_w=720",
                new Rooms.Coordinate(128.60255,128.60255),new Review(4.8,124));

        RoomsDTO roomsDTO = new RoomsDTO();

        roomsDTO.add(rooms1);
        roomsDTO.add(room2);
        roomsDTO.add(room3);

        return roomsDTO;
    }

    @GetMapping("/rooms/{roomId}")
    public RoomsDetail roomDetail(@PathVariable Long roomId){
        RoomsDetail roomsDetail = new RoomsDetail(roomId,"살며시귀기울이면#심장소리만큼 하이얀 파도소리가 들려요 #영진#주문진#도깨비촬영지#커피거리",
                new Host("Mr.Kime","https://a0.muscache.com/im/pictures/user/7822f895-df8a-4b0f-9035-0d3b3afbdc3d.jpg?aki_policy=profile_x_medium")
                ,new Rooms.HomeDetail(3,2,3),"원룸",96000,"2018년 10월 신규 건물로서 속초 시내에 위치하여 로데오거리, 수산관광시장, 중앙시장,크루즈모항 등 관광지 식당가가 모두 도보로 5분이내로 접근이 가능하고, 속초항 바다부터 설악산까지 조망되는 훌륭한 조망권도 일품입니다. 실내 취사가 가능하며 최신식 최고급 소재의 실내 인테리어까지 갖추고있습니다. 감사합니다.* 다수의 객실보유중입니다.\n 예약달력에서 만실이더라도 문의주시면 다른 객실로 안내해 드리겠습니다.\n^^*** 주차는 지하주차장에 하실 수 있습니다.다만, 주차장이 꽉 찰 경우, 유료 주차장인 로데오주차장이 있습니다.주차비는 지원드릴 수 없다는 점을 참고해주시길 바랍니다.**",
                "서울",new Rooms.Coordinate(37.94032,128.78549),new Review(4.8,127),
                new AdditionalCost(4,20000,4,4));
        roomsDetail.imageAdd("https://a0.muscache.com/im/pictures/d62aed28-5b79-439d-b26d-b065b2f1a25d.jpg?im_w=720");
        roomsDetail.imageAdd("https://a0.muscache.com/im/pictures/787a4846-b5ba-4ab2-be52-82ac556ab984.jpg?im_w=720");
        roomsDetail.imageAdd("https://a0.muscache.com/im/pictures/51d1beb3-95d3-4f83-8aaf-cb59e3ac01d0.jpg?im_w=720");
        return roomsDetail;
    }

    @GetMapping("/citys")
    public CitysDTO citys(){
        CitysDTO citysDTO = new CitysDTO();
        citysDTO.addCity(new CitysDTO.City("서울","https://a0.muscache.com/im/pictures/71e23854-a3c7-491c-b715-6e86233a293f.jpg?im_q=medq&im_w=240"));
        citysDTO.addCity(new CitysDTO.City("인천","https://a0.muscache.com/im/pictures/71e23854-a3c7-491c-b715-6e86233a293f.jpg?im_q=medq&im_w=240"));
        citysDTO.addCity(new CitysDTO.City("대구","https://a0.muscache.com/im/pictures/71e23854-a3c7-491c-b715-6e86233a293f.jpg?im_q=medq&im_w=240"));
        citysDTO.addCity(new CitysDTO.City("광주","https://a0.muscache.com/im/pictures/71e23854-a3c7-491c-b715-6e86233a293f.jpg?im_q=medq&im_w=240"));
        citysDTO.addCity(new CitysDTO.City("부산","https://a0.muscache.com/im/pictures/71e23854-a3c7-491c-b715-6e86233a293f.jpg?im_q=medq&im_w=240"));
        citysDTO.addCity(new CitysDTO.City("춘천","https://a0.muscache.com/im/pictures/71e23854-a3c7-491c-b715-6e86233a293f.jpg?im_q=medq&im_w=240"));
        citysDTO.addCity(new CitysDTO.City("여수","https://a0.muscache.com/im/pictures/71e23854-a3c7-491c-b715-6e86233a293f.jpg?im_q=medq&im_w=240"));
        return citysDTO;
    }

    @PostMapping("/rooms/{roomId}/reservations")
    public void reservations(@PathVariable Long roomId){
    }


    @DeleteMapping("/rooms/{roomId}/reservations")
    public void reservationsCancel(@PathVariable Long roomId){

    }

    @PatchMapping("/rooms/{roomId}/wish")
    public void wishTogle(@PathVariable Long roomId){

    }











    private static class RoomsDTO{
        private List<Rooms> rooms = new ArrayList<>();


        public void add(Rooms rooms){
            this.rooms.add(rooms);
        }

        public List<Rooms> getRooms() {
            return rooms;
        }
    }


    private static class SearchPriceDTO {
        private List<Integer> price = new ArrayList<>();

        public SearchPriceDTO(String checkIn,String checkOut) {
            price.add(1000);
            price.add(2000);
            price.add(3000);
            price.add(5000);
            price.add(6000);
        }
        public SearchPriceDTO(String cityName) {
            price.add(7000);
            price.add(8000);
            price.add(9000);
            price.add(10000);
            price.add(11000);
        }

        public List<Integer> getPrice() {
            return price;
        }
    }



}
