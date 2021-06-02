

import Foundation

class FetchPriceManager {

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
        print(url.absoluteString)
        network.reequest(url: url, completionHandler: { (result: Result<PriceDTO, Error>) in
            switch result {
            case .success(let data):
                self.price.setPrices(prices: data.prices)
                NotificationCenter.default.post(name: Notification.Name.fetchPrice, object: self)
            case .failure(let error):
                print(error.localizedDescription)
            }
        })
    }
    
    func getPrice() -> [Int] {
        return price.getPrices()
    }
    
    func getMaximumPrice() -> Int {
        return price.getMaximumPrice()
    }
    
    func getMinimumPrice() -> Int {
        return price.getMinimumPrice()
    }
    
    func getPriceAverage() -> Int {
        return price.calculatorPriceAverage()
    }
    
}
