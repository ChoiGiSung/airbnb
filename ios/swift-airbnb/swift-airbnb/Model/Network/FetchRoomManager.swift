

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
    
    func fetchData(checkIn: String, checkOut: String, city: String) {
        guard let url = endPoint.searchPriceURL(checkIn: checkIn, checkOut: checkOut, city: city) else { return }
        network.reequest(url: url, completionHandler: { (result: Result<RoomsDTO, Error>) in
            switch result {
            case .success(let data):
                for index in 0..<data.rooms.count {
                    let room = Room(thumbnailImage: data.rooms[index].thumbnailImage, star: String(data.rooms[index].review.star), comment: String(data.rooms[index].review.comment), name: data.rooms[index].name, pricePerDate: String(data.rooms[index].pricePerDate), totalPrice: String(data.rooms[index].totalPrice))
                    self.rooms.appendRoom(room: room)
                }
                NotificationCenter.default.post(name: Notification.Name.fetchRoom, object: self)
            case .failure(let error):
                print(error.localizedDescription)
            }
        })
    }
    
    func getRooms() -> Rooms {
        return self.rooms
    }
}
