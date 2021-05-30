
import Foundation

class City {
    let name: String
    let image: String
    
    init() {
        self.name = ""
        self.image = ""
    }
}

class Cities {
    
    var cities: [City]
    
    init() {
        self.cities = []
    }
//    
//    func convertDTOToEntity(DTO: CitiesDTO)  {
//        for index in 0..<DTO.cities.count {
//            self.cities[index] = DTO.cities
//        }
//    }
}

class Information {
    
    static let shared = Information()
    private var name: String
    private var checkIn: String
    private var checkOut: String
    private var fee: String
    private var numberOfPeople: String
    
    init() {
        self.name = ""
        self.checkIn = ""
        self.checkOut = ""
        self.fee = ""
        self.numberOfPeople = ""
    }
    
    func setName(name: String) {
        self.name = name
    }
    
    func returnName() -> String {
        return self.name
    }
    
}
