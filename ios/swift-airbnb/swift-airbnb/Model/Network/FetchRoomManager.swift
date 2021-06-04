

import Foundation

class FetchRoomManager {
    
    private let network: NetworkManager
    private let endPoint: EndPoint
    private(set) var rooms: Rooms
    
    init() {
        self.network = NetworkManager()
        self.endPoint = EndPoint()
        self.rooms = Rooms()
    }
    
    func fetchData(checkIn: String, checkOut: String, city: String, adult: String, child: String, baby: String, minPrice: String, maxPrice: String) {
        guard let url = endPoint.searchRoomURL(checkIn: checkIn, checkOut: checkOut, city: city, adult: adult, child: child, baby: baby, minPrice: minPrice, maxPrice: maxPrice) else { return }
        print(url.absoluteString)
        network.reequest(url: url, completionHandler: { (result: Result<RoomsDTO, Error>) in
            switch result {
            case .success(let data):
                for index in 0..<data.rooms.count {
                    let room = Room(thumbnailImage: data.rooms[index].thumbnailImage,
                                    star: String(data.rooms[index].review.star),
                                    comment: String(data.rooms[index].review.review),
                                    name: data.rooms[index].name,
                                    pricePerDate: String(data.rooms[index].pricePerDate.withComma),
                                    totalPrice: String(data.rooms[index].totalPrice.withComma))
                    self.rooms.appendRoom(room: room)
                }
                NotificationCenter.default.post(name: Notification.Name.fetchRoom, object: self)
            case .failure(let error):
                print("error")
                print(error.localizedDescription)
            }
        })
    }
    
    func getRooms() -> Rooms {
        return self.rooms
    }
}
