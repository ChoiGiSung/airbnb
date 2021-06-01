
import UIKit
import SwiftCharts

class ChartViewController: UIViewController {

    @IBOutlet weak var informationView: InformationView!
    @IBOutlet weak var minimumPriceLabel: UILabel!
    @IBOutlet weak var maximumPriceLabel: UILabel!
    @IBOutlet weak var priceAverageLabel: UILabel!
    
    private let information = Information.shared
    private var fetchPriceManager = FetchPriceManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = "숙소 찾기"
        configureInformationView()
        fetchPriceData()
        NotificationCenter.default.addObserver(self, selector: #selector(configurePriceLabel), name: Notification.Name.fetchPrice, object: nil)
    }
    
    
    func configureInformationView() {
        informationView.configureCheckInLabel(checkIn: information.getCheckIn())
        informationView.configureCheckOutLabel(checkOut: information.getCheckOut())
        informationView.configureLocationLabel(name: information.getName())
    }
    
    func fetchPriceData() {
        self.fetchPriceManager.fetchData(checkIn: self.information.getCheckIn(), checkOut: self.information.getCheckOut(), city: self.information.getName())
    }


    @objc func configurePriceLabel() {
        let priceArr = fetchPriceManager.getPrice().sorted()
        minimumPriceLabel.text = String(priceArr[0])
        maximumPriceLabel.text = String(priceArr[priceArr.count - 1])
    }
}
