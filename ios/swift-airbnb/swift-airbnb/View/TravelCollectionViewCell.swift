

import UIKit
import Kingfisher

class TravelCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var travelImageView: UIImageView!
    @IBOutlet weak var destinationLabel: UILabel!
    @IBOutlet weak var timeLabel: UILabel!
    static let identifier = "TravelCollectionViewCell"
    
    static var nib: UINib {
        return UINib(nibName: identifier, bundle: nil)
    }
    
    override func prepareForReuse() {
        self.travelImageView.image = nil
        self.destinationLabel.text = ""
        self.timeLabel.text = ""
    }
    
    func configureTravelCell(name: String, image: String, time: String) {
        self.destinationLabel.text = name
        guard let url = URL(string: image) else { return }
        self.travelImageView.kf.setImage(with: url)
        self.timeLabel.text = time
    }
    
    func calculatorTime(name: String) -> String {
        switch name {
        case "강원":
            return "차로 3시간 거리"
        case "경기":
            return "차로 1시간 거리"
        case "광주":
            return "차로 4시간 거리"
        case "대전":
            return "차로 2.5시간 거리"
        case "부산":
            return "차로 5시간 거리"
        case "서울":
            return "차로 30분 거리"
        case "전주":
            return "차로 3시간 거리"
        case "제주":
            return "비행기로 1시간 거리"
        default:
            break
        }
        return ""
    }
    
}
