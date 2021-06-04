

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

    func configureCell(indexPath: IndexPath, rooms: Rooms) {
        guard let url = URL(string: rooms.getRooms()[indexPath.row].getThumbnailImage()) else { return }
        self.thumbnailImageView.kf.setImage(with: url)
        self.thumbnailImageView.layer.masksToBounds = true
        self.thumbnailImageView.layer.cornerRadius = 10
        self.starLabel.text = rooms.getRooms()[indexPath.row].getStar()
        self.commentLabel.text = "(후기 \(rooms.getRooms()[indexPath.row].getComment())개)"
        self.nameLabel.text = rooms.getRooms()[indexPath.row].getName()
        self.pricePerDateLabel.text = "₩\(rooms.getRooms()[indexPath.row].getPricePerDate())"
        self.totalPriceLabel.text = "총액 ₩\(rooms.getRooms()[indexPath.row].getTotalPrice())"
    }
    
}
