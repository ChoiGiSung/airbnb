

import Foundation

struct EndPoint {
    
    private let scheme = "http"
    private let host = "13.125.140.183"
    private let citypath = "/cities"
    private let searchPath = "/search"
    
    func searchCityURL() -> String {
        return "\(scheme)://\(host)\(citypath)"
    }
    
}
