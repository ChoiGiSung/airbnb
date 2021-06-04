

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
    
    func searchRoomURL(checkIn: String, checkOut: String, city: String, adult: String, child: String, baby: String, minPrice: String, maxPrice: String) -> URL? {
        var componenets = URLComponents()
        componenets.scheme = scheme
        componenets.host = host
        componenets.path = searchPath
        let checkIn = URLQueryItem(name: "check-in", value: checkIn)
        let checkOut = URLQueryItem(name: "check-out", value: checkOut)
        let city = URLQueryItem(name: "city-name", value: city)
        let adult = URLQueryItem(name: "adult", value: adult)
        let child = URLQueryItem(name: "child", value: child)
        let baby = URLQueryItem(name: "baby", value: baby)
        let minPrice = URLQueryItem(name: "price-min", value: minPrice)
        let maxPrice = URLQueryItem(name: "price-max", value: maxPrice)
        componenets.queryItems = [checkIn,checkOut,city,adult,child,baby,minPrice,maxPrice]
        return componenets.url
    }
    
}
