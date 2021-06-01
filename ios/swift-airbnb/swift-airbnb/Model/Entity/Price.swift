

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
}
