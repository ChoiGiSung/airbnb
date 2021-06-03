
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
    
    @IBOutlet weak var rangeSlider: RangeSlider!
    
    private var minPrice = 0
    private var maxPrice = 0
    
    
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
    
    func convertPrice(minimumPrice: Int, maximumPrice: Int) -> [Int] {
        var resultMinPrice = [String]()
        var resultMaxPrice = [String]()
        let minPriceArr = String(minimumPrice).map{String($0)}
        let maxPriceArr = String(maximumPrice).map{String($0)}
        for i in 0..<minPriceArr.count - 3 {
            resultMinPrice.append(minPriceArr[i])
        }
        for i in 0..<maxPriceArr.count - 3 {
            resultMaxPrice.append(maxPriceArr[i])
        }
        
        return [Int(resultMinPrice.joined())!, Int(resultMaxPrice.joined())!]
    }
    
    func configureRangeSlider(minimumPrice: Int, maximumPrice: Int)  {
        let prices = convertPrice(minimumPrice: minimumPrice, maximumPrice: maximumPrice)
        rangeSlider.setRangeValues(Array(prices[0]...prices[1]))
        rangeSlider.setMinAndMaxRange(prices[0], maxRange: prices[1])
        rangeSlider.setMinAndMaxValue(prices[0], maxValue: prices[1])
        rangeSlider.setValueChangedCallback { (minValue, maxValue) in
            self.informationView.minimumPriceLabel.text = "₩\((minValue * 1000).withComma)"
            self.minPrice = minValue * 1000
            self.informationView.priceHyphenLabel.text = "~"
            self.informationView.maximumPriceLabel.text = "₩\((maxValue * 1000).withComma)"
            self.maxPrice = maxValue * 1000
        }
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
        configureRangeSlider(minimumPrice: fetchPriceManager.getMinimumPrice(), maximumPrice: fetchPriceManager.getMaximumPrice())
    }
    
    @IBAction func pressedNextButton(_ sender: Any){
        guard let selectNumberViewController = self.storyboard?.instantiateViewController(identifier: "selectNumberViewController") as? SelectNumberViewController else { return }
        self.navigationController?.pushViewController(selectNumberViewController, animated: true)
        information.setMinimumPriceAndMaximumPrice(minimumPrice: String(minPrice), maximumPrice: String(maxPrice))
    }
}

