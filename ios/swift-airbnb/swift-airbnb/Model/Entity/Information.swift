

import Foundation

class Information {
    
    static let shared = Information()
    private var name: String
    private var checkIn: String
    private var checkOut: String
    private var minimumPrice: String
    private var maximumPrice: String
    private var numberOfAdult: String
    private var numberOfChild: String
    private var numberOfInfants: String
    
    init() {
        self.name = ""
        self.checkIn = ""
        self.checkOut = ""
        self.minimumPrice = ""
        self.maximumPrice = ""
        self.numberOfAdult = ""
        self.numberOfChild = ""
        self.numberOfInfants = ""
    }
    
    func setName(name: String) {
        switch name {
        case "Gangwon-do":
            self.name = "강원"
        case "Gyeonggi-do":
            self.name = "경기"
        case "Gwangju":
            self.name = "광주"
        case "Daejeon":
            self.name = "대전"
        case "Busan":
            self.name = "부산"
        case "Seoul":
            self.name = "서울"
        case "Jeonju-si":
            self.name = "전주"
        case "Jeju-do":
            self.name = "제주"
        default:
            break
        }
    }
    
    func returnName() -> String {
        return self.name
    }
    
    func setCheckInAndCheckOut(checkIn: String, checkOut: String) {
        self.checkIn = checkIn
        self.checkOut = checkOut
    }
    
    func returnCheckIn() -> String {
        return self.checkIn
    }
    
    func returnCheckOut() -> String {
        return self.checkOut
    }
    
    func setMinimumPriceAndMaximumPrice(minimumPrice: String, maximumPrice: String) {
        self.minimumPrice = minimumPrice
        self.maximumPrice = maximumPrice
    }
    
    func setNumberOfPeople(number: String) {
        self.numberOfAdult = number
    }
    
}
