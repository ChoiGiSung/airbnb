
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
