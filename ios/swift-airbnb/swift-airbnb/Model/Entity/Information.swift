

import Foundation

class Information {
    
    static let shared = Information()
    private var name: String
    private var checkIn: String
    private var checkOut: String
    private var minimumPrice: String
    private var maximumPrice: String
    private var numberOfPeople: String
    private var numberOfAdult: String
    private var numberOfChildren: String
    private var numberOfInfants: String
    
    init() {
        self.name = ""
        self.checkIn = ""
        self.checkOut = ""
        self.minimumPrice = ""
        self.maximumPrice = ""
        self.numberOfPeople = ""
        self.numberOfAdult = ""
        self.numberOfChildren = ""
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
            self.name = name
        }
    }
    
    func getName() -> String {
        return self.name
    }
    
    func setCheckInAndCheckOut(checkIn: String, checkOut: String) {
        self.checkIn = checkIn
        self.checkOut = checkOut
    }
    
    func setAdult(adult: String)  {
        self.numberOfAdult = adult
    }
    
    func setChildren(children: String)  {
        self.numberOfChildren = children
    }
    
    func setInfants(infants: String)  {
        self.numberOfInfants = infants
    }
    
    func getCheckIn() -> String {
        return self.checkIn
    }
    
    func getCheckOut() -> String {
        return self.checkOut
    }
    
    func getMinimumPrice() -> Int {
        return Int(self.minimumPrice)!
    }
    
    func getMaximumPrice() -> Int {
        return Int(self.maximumPrice)!
    }
    
    func getNumberOfPeople() -> String {
        return self.numberOfPeople
    }
    
    func getNumberOfAdult() -> String {
        return self.numberOfAdult
    }
    
    func getNumberOfChildren() -> String {
        return self.numberOfChildren
    }
    
    func getNumberOfInfants() -> String {
        return self.numberOfInfants
    }
    
    func setMinimumPriceAndMaximumPrice(minimumPrice: String, maximumPrice: String) {
        self.minimumPrice = minimumPrice
        self.maximumPrice = maximumPrice
    }
    
    func setNumberOfPeople(number: String) {
        self.numberOfPeople = number
    }
    
}
