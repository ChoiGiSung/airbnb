
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
        configureNavigationItem()
        configureInformationView()
        fetchPriceData()
        NotificationCenter.default.addObserver(self, selector: #selector(configurePriceLabel), name: Notification.Name.fetchPrice, object: nil)
    }
    
    func configureNavigationItem()  {
        navigationItem.title = "숙소 찾기"
        self.navigationItem.backButtonTitle = "뒤로"
    }
    
    
    func configureInformationView() {
        informationView.configureCheckInLabel(checkIn: information.getCheckIn())
        informationView.configureCheckOutLabel(checkOut: information.getCheckOut())
        informationView.configureLocationLabel(name: information.getName())
        if informationView.checkInLabel.text != "" && informationView.checkOutLabel.text != ""  {
            informationView.configureDayHyphenLabel()
        }
    }
    
    func fetchPriceData() {
        self.fetchPriceManager.fetchData(checkIn: self.information.getCheckIn(), checkOut: self.information.getCheckOut(), city: self.information.getName())
    }


    @objc func configurePriceLabel() {
        minimumPriceLabel.text = "₩\(String(fetchPriceManager.getMinimumPrice().withComma))"
        maximumPriceLabel.text = "₩\(String(fetchPriceManager.getMaximumPrice().withComma))"
        informationView.minimumPriceLabel.text = "₩\(String(fetchPriceManager.getMinimumPrice().withComma))"
        informationView.maximumPriceLabel.text = "₩\(String(fetchPriceManager.getMaximumPrice().withComma))"
        informationView.priceHyphenLabel.text = "~"
        priceAverageLabel.text = "평균 1박 요금은 ₩\(String(fetchPriceManager.getPriceAverage().withComma))입니다."
    }
}

