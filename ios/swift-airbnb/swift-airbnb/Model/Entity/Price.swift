

import Foundation

class Price {
    
    private(set) var prices: [Int]
    
    init() {
        self.prices = []
    }
    
    func setPrices(prices: [Int])  {
        self.prices = prices
    }
    
    func getPrices() -> [Int] {
        return self.prices
    }
    
    func calculatorPriceAverage() -> Int {
        var sum = 0
        for index in 0..<prices.count {
             sum += prices[index]
        }
        return sum / prices.count
    }
    
    func getMaximumPrice() -> Int {
        let price = prices.sorted()
        return price[price.count - 1]
    }
    
    func getMinimumPrice() -> Int {
        let price = prices.sorted()
        return price[0]
    }
}
