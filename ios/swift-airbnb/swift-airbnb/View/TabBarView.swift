
import UIKit

class TabBarView: UIView {
    
    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var skipButton: UIButton!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        let nib = UINib(nibName: "TabBarView", bundle: Bundle.main)
        
        guard let xibView = nib.instantiate(withOwner: self, options: nil).first as? UIView else { return }
        
        xibView.frame = self.bounds
        xibView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.addSubview(xibView)
        configureButton(check: false)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        let nib = UINib(nibName: "TabBarView", bundle: Bundle.main)
        
        guard let xibView = nib.instantiate(withOwner: self, options: nil).first as? UIView else { return }
        
        xibView.frame = self.bounds
        xibView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.addSubview(xibView)
        configureButton(check: false)
    }
    
    func configureButton(check: Bool) {
        if check == false {
            self.nextButton.isEnabled = false
        } else {
            self.nextButton.isEnabled = true
        }
    }


}
