
import UIKit

class InformationView: UIView {
    
    @IBOutlet weak var locationLabel: UILabel!
    @IBOutlet weak var checkLabel: UILabel!
    @IBOutlet weak var feeLabel: UILabel!
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
        self.checkLabel.text = "\(days[0])\(days[1])"
        self.checkLabel.textColor = UIColor.systemGray2
    }
    
    func configureLocationLabel(name: String) {
        self.locationLabel.text = name
    }
    
}
