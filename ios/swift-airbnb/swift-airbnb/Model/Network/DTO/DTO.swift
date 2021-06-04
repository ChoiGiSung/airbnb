
import Foundation

struct CitiesDTO: Decodable {
    let cities: [CityDTO]
}

struct CityDTO: Decodable {
    let name: String
    let imageURL: String

    enum CodingKeys: String, CodingKey {
        case name
        case imageURL = "image_url"
    }
}

struct PriceDTO: Decodable {
    let prices: [Int]
}

struct RoomsDTO: Decodable {
    let rooms: [RoomDTO]
}

struct RoomDTO: Decodable {
    let id: Int
    let name: String
    let pricePerDate: Int
    let homeDetails: HomeDetailsDTO
    let type: String
    let wish: Bool
    let thumbnailImage: String
    let coordinate: CoordinateDTO
    let review: ReviewDTO
    let totalPrice: Int
    let roomDescription: String

    enum CodingKeys: String, CodingKey {
        case id, name
        case pricePerDate = "price_per_date"
        case homeDetails = "home_details"
        case type, wish
        case thumbnailImage = "thumbnail_image"
        case coordinate, review
        case totalPrice = "total_price"
        case roomDescription = "description"
    }
}

struct CoordinateDTO: Decodable {
    let latitude, longitude: Double
}

struct HomeDetailsDTO: Decodable {
    let bed, maxGuest, bathRoom: Int

    enum CodingKeys: String, CodingKey {
        case bed
        case maxGuest = "max_guest"
        case bathRoom = "bath_room"
    }
}

struct ReviewDTO: Decodable {
    let star: Double
    let review: Int
}
