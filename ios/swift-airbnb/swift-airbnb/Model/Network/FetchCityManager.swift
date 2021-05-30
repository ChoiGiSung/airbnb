
import Foundation

class FetchCityManager {
    
    static let shared = FetchCityManager()
    private let network: NetworkManager
    private let endPoint: EndPoint
    var cities: CitiesDTO
    
    init() {
        self.network = NetworkManager()
        self.endPoint = EndPoint()
        self.cities = CitiesDTO(cities: [])
    }
    
    func fetchData() {
        network.reequest(url: endPoint.searchCityURL(), completionHandler: { (result: Result<CitiesDTO, Error>) in
            switch result {
            case .success(let data):
                self.cities = data
                NotificationCenter.default.post(name: Notification.Name.fetchTravel, object: self)
            case .failure(let error):
                print(error.localizedDescription)
            }
        })
    }
}
