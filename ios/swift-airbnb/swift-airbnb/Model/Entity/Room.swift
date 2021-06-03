

import Foundation

class Room {
    
    private var thumbnailImage: String
    private var star: String
    private var comment: String
    private var name: String
    private var pricePerDate: String
    private var totalPrice: String
    
    init(thumbnailImage: String, star: String, comment: String, name: String, pricePerDate: String, totalPrice: String) {
        self.thumbnailImage = thumbnailImage
        self.star = star
        self.comment = comment
        self.name = name
        self.pricePerDate = pricePerDate
        self.totalPrice = totalPrice
    }
    
    func getThumbnailImage() -> String {
        return self.thumbnailImage
    }
    
    func getStar() -> String {
        return self.star
    }
    
    func getComment() -> String {
        return self.comment
    }
    
    func getName() -> String {
        return self.name
    }
    
    func getPricePerDate() -> String {
        return self.pricePerDate
    }
    
    func getTotalPrice() -> String {
        return self.totalPrice
    }
}
