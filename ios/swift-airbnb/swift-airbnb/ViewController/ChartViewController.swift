
import UIKit
import SwiftCharts

class ChartViewController: UIViewController {

    @IBOutlet weak var informationView: InformationView!
    @IBOutlet weak var minimumPriceLabel: UILabel!
    @IBOutlet weak var maximumPriceLabel: UILabel!
    @IBOutlet weak var priceAverageLabel: UILabel!
    
    private let information = Information.shared
    private var fetchPriceManager = FetchPriceManager.shared
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = "숙소 찾기"
       configureInformationView()
        
    }
    
    
    func configureInformationView() {
        self.informationView.locationLabel.text = information.returnName()
        self.informationView.checkInLabel.text = information.returnCheckIn()
        self.informationView.checkOutLabel.text = information.returnCheckOut()
    }
}
