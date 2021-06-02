
import UIKit

class InformationView: UIView {
    
    @IBOutlet weak var locationLabel: UILabel!
    @IBOutlet weak var checkInLabel: UILabel!
    @IBOutlet weak var checkOutLabel: UILabel!
    @IBOutlet weak var dayHyphenLabel: UILabel!
    @IBOutlet weak var minimumPriceLabel: UILabel!
    @IBOutlet weak var maximumPriceLabel: UILabel!
    @IBOutlet weak var priceHyphenLabel: UILabel!
    @IBOutlet weak var personnelLabel: UILabel!

    override init(frame: CGRect) {
        super.init(frame: frame)
        let nib = UINib(nibName: "InformationView", bundle: Bundle.main)
        guard let xibView = nib.instantiate(withOwner: self, options: nil).first as? UIView else { return }
        xibView.frame = self.bounds
        xibView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.addSubview(xibView)
    }

    required init?(coder: NSCoder) {
            super.init(coder: coder)
            let nib = UINib(nibName: "InformationView", bundle: Bundle.main)
            guard let xibView = nib.instantiate(withOwner: self, options: nil).first as? UIView else { return }
            xibView.frame = self.bounds
            xibView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
            self.addSubview(xibView)
        }

    func configureCheckLabel(days: [String]) {
        self.checkInLabel.text = "\(days[0])"
        self.checkOutLabel.text = "\(days[1])"
    }
    
    func configureCheckInLabel(checkIn: String) {
        self.checkInLabel.text = checkIn
    }
    
    func configureCheckOutLabel(checkOut: String) {
        self.checkOutLabel.text = checkOut
    }
    
    func configureLocationLabel(name: String) {
        self.locationLabel.text = name
    }
    
    func configureDayHyphenLabel() {
        self.dayHyphenLabel.text = "~"
    }
    
    func configurePriceHyphenLabel() {
        self.dayHyphenLabel.text = "~"
    }
}
