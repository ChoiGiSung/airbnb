

import UIKit
import Kingfisher

class RoomCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var thumbnailImageView: UIImageView!
    @IBOutlet weak var starLabel: UILabel!
    @IBOutlet weak var commentLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var pricePerDateLabel: UILabel!
    @IBOutlet weak var totalPriceLabel: UILabel!
    
    static let identifier = "RoomCollectionViewCell"
    
    static var nib: UINib {
        return UINib(nibName: identifier, bundle: nil)
    }

//    func configureCell(indexPath: IndexPath, data: ) {
//        guard let url = URL(string: ) else { return }
//        self.thumbnailImageView.kf.setImage(with: url)
//    }
}
