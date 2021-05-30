

import UIKit

class RecommendTravelCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var recommendTravelImageView: UIImageView!
    @IBOutlet weak var descriptionLabel: UILabel!
    static let identifier = "RecommendTravelCollectionViewCell"
    
    
    private let imageArr = ["withNature.jpg", "FancySpace.jpg", "WholeHouse.jpg", "withPet.jpg"]
    private let textArr = ["자연 생활을 만끽할 수 있는 숙소", "독특한 공간", "집 전체", "반려동물 동반 가능"]
    
    override func prepareForReuse() {
        self.recommendTravelImageView.image = nil
        self.descriptionLabel.text = ""
    }
    
    static var nib: UINib {
        return UINib(nibName: identifier, bundle: nil)
    }
    
    func configureTravelCell(indexPath: Int) {
        self.recommendTravelImageView.image = UIImage(named: imageArr[indexPath])
        self.descriptionLabel.text = textArr[indexPath]
    }
}
