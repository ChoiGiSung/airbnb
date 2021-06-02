
import UIKit
import ZMSwiftRangeSlider

class ChartViewController: UIViewController {

    @IBOutlet weak var informationView: InformationView!
    @IBOutlet weak var minimumPriceLabel: UILabel!
    @IBOutlet weak var maximumPriceLabel: UILabel!
    @IBOutlet weak var priceAverageLabel: UILabel!
    @IBOutlet weak var nextButton: UIButton!
    
    private let information = Information.shared
    private var fetchPriceManager = FetchPriceManager()
    
    @IBOutlet weak var rangeSlider1: RangeSlider!
    
    @IBOutlet weak var rangeSlider2: RangeSlider!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureNavigationItem()
        configureInformationView()
        fetchPriceData()
        NotificationCenter.default.addObserver(self, selector: #selector(configurePriceLabel), name: Notification.Name.fetchPrice, object: nil)
        
        rangeSlider1.setValueChangedCallback { (minValue, maxValue) in
            print("rangeSlider1 min value:\(minValue)")
            print("rangeSlider1 max value:\(maxValue)")
        }
        rangeSlider1.setMinValueDisplayTextGetter { (minValue) -> String? in
            return "$\(minValue)"
        }
        rangeSlider1.setMaxValueDisplayTextGetter { (maxValue) -> String? in
            return "$\(maxValue)"
        }
        rangeSlider1.setMinAndMaxRange(0, maxRange: 1000)

//        rangeSlider2.setValueChangedCallback { (minValue, maxValue) in
//            print("rangeSlider2 min value:\(minValue)")
//            print("rangeSlider2 max value:\(maxValue)")
//        }
//        rangeSlider2.setMinValueDisplayTextGetter { (minValue) -> String? in
//            return "¥\(minValue)"
//        }
//        rangeSlider2.setMaxValueDisplayTextGetter { (maxValue) -> String? in
//            return "¥\(maxValue)"
//        }
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
    
    @IBAction func ButtonAction(_ sender: Any){
        guard let selectNumberViewController = self.storyboard?.instantiateViewController(identifier: "selectNumberViewController") as? SelectNumberViewController else { return }
        self.navigationController?.pushViewController(selectNumberViewController, animated: true)
//        information.setMinimumPriceAndMaximumPrice(minimumPrice: <#T##String#>, maximumPrice: <#T##String#>)
    }
}

