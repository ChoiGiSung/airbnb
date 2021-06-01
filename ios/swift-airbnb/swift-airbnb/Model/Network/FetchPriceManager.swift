

import Foundation

class FetchPriceManager {

    static let shared = FetchPriceManager()
    private let network: NetworkManager
    private let endPoint: EndPoint
    private var price: Price
    
    init() {
        self.network = NetworkManager()
        self.endPoint = EndPoint()
        self.price = Price()
    }
    
    
    func fetchData(checkIn: String, checkOut: String, city: String) {
        guard let url = endPoint.searchPriceURL(checkIn: checkIn, checkOut: checkOut, city: city) else { return }
        network.reequest(url: url, completionHandler: { (result: Result<PriceDTO, Error>) in
            switch result {
            case .success(let data):
                self.price.setPrices(prices: data.prices)
            case .failure(let error):
                print(error.localizedDescription)
            }
        })
    }
    
    func returnPrice() -> [Int] {
        return price.prices
    }
}
