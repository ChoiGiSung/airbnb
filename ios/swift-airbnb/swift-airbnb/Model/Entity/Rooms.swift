

import Foundation

class Rooms {
    
    private var rooms: [Room]
    
    init() {
        self.rooms = []
    }
    
    func setRooms(rooms: [Room])  {
        self.rooms = rooms
    }
    
    func appendRoom(room: Room)  {
        self.rooms.append(room)
    }
    
    func getRooms() -> [Room] {
        return self.rooms
    }
}
