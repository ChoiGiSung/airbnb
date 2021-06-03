

import Foundation

struct EndPoint {
    
    private let scheme = "http"
    private let host = "13.125.140.183"
    private let citypath = "/cities"
    private let searchPath = "/search"
    private let pricePath = "/search/prices"
    
    func searchCityURL() -> URL? {
        var componenets = URLComponents()
        componenets.scheme = scheme
        componenets.host = host
        componenets.path = citypath
        return componenets.url
    }
    
    func searchPriceURL(checkIn: String, checkOut: String, city: String) -> URL? {
        var componenets = URLComponents()
        componenets.scheme = scheme
        componenets.host = host
        componenets.path = pricePath
        let checkIn = URLQueryItem(name: "check-in", value: checkIn)
        let checkOut = URLQueryItem(name: "check-out", value: checkOut)
        let city = URLQueryItem(name: "city-name", value: city)
        componenets.queryItems = [checkIn,checkOut,city]
        return componenets.url
    }
    
    func searchRoomURL() -> URL? {
        var componenets = URLComponents()
        componenets.scheme = scheme
        componenets.host = host
        
        
        return componenets.url
    }
    
}
