

import UIKit

class SelectNumberViewController: UIViewController {
    
    @IBOutlet weak var adultPlusButton: UIButton!
    @IBOutlet weak var adultMinusButton: UIButton!
    @IBOutlet weak var adultNumberLabel: UILabel!
    @IBOutlet weak var childrenPlusButton: UIButton!
    @IBOutlet weak var childrenMinusButton: UIButton!
    @IBOutlet weak var childrenNumberLabel: UILabel!
    @IBOutlet weak var infantsPlusButton: UIButton!
    @IBOutlet weak var infantsMinusButton: UIButton!
    @IBOutlet weak var infantsNumberLabel: UILabel!
    @IBOutlet weak var searchButton: UIButton!
    
    @IBOutlet weak var informationView: InformationView!
    private let information = Information.shared
    
    private var numberOfAdult = 0
    private var numberOfChildren = 0
    private var numberOfInfants = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setMinusButtonState()
        configureInformationView()
        configureNavigationItem()
    }
    
    func configureNavigationItem()  {
        navigationItem.title = "숙소 찾기"
        self.navigationItem.backButtonTitle = "뒤로"
    }
    
    func configureInformationView()  {
        informationView.configureLocationLabel(name: information.getName())
        informationView.configureCheckInLabel(checkIn: information.getCheckIn())
        informationView.configureDayHyphenLabel()
        informationView.configureCheckOutLabel(checkOut: information.getCheckOut())
        informationView.configureMinimumPriceLabel(price: "₩\(information.getMinimumPrice().withComma)")
        informationView.configurePriceHyphenLabel()
        informationView.configureMaximumPriceLabel(price: "₩\(information.getMaximumPrice().withComma)")
    }
    
    func setMinusButtonState() {
        adultMinusButton.isEnabled = false
        childrenMinusButton.isEnabled = false
        infantsMinusButton.isEnabled = false
    }
    
    func calculatorNumber() -> String {
        return String(numberOfAdult + numberOfChildren + numberOfInfants)
    }
    
    
    //MARK: - 인원 버튼 액션
    @IBAction func pressedAdultPlusButton(_ sender: Any){
        numberOfAdult += 1
        adultNumberLabel.text = "\(numberOfAdult)"
        adultMinusButton.isEnabled = true
        informationView.configureNumberOfPeopleLabel(number: calculatorNumber())
    }
    
    @IBAction func pressedAdultMinusButton(_ sender: Any){
        if numberOfAdult > 1 {
            numberOfAdult -= 1
            adultMinusButton.isEnabled = true
        }else {
            adultMinusButton.isEnabled = false
            numberOfAdult = 0
        }
        adultNumberLabel.text = "\(numberOfAdult)"
        informationView.configureNumberOfPeopleLabel(number: calculatorNumber())
    }
    
    @IBAction func pressedChildrenPlusButton(_ sender: Any){
        numberOfChildren += 1
        childrenNumberLabel.text = "\(numberOfChildren)"
        childrenMinusButton.isEnabled = true
        informationView.configureNumberOfPeopleLabel(number: calculatorNumber())
    }
    
    @IBAction func pressedChildrenMinusButton(_ sender: Any){
        if numberOfChildren > 1 {
            numberOfChildren -= 1
            childrenMinusButton.isEnabled = true
        }else{
            childrenMinusButton.isEnabled = false
            numberOfChildren = 0
        }
        childrenNumberLabel.text = "\(numberOfChildren)"
        informationView.configureNumberOfPeopleLabel(number: calculatorNumber())
    }
    
    @IBAction func pressedInfantsPlusButton(_ sender: Any){
        numberOfInfants += 1
        infantsNumberLabel.text = "\(numberOfInfants)"
        infantsMinusButton.isEnabled = true
        informationView.configureNumberOfPeopleLabel(number: calculatorNumber())
    }
    
    @IBAction func pressedInfantsMinusButton(_ sender: Any){
        if numberOfInfants > 1 {
            numberOfInfants -= 1
            infantsMinusButton.isEnabled = true
        }else{
            infantsMinusButton.isEnabled = false
            numberOfInfants = 0
        }
        infantsNumberLabel.text = "\(numberOfInfants)"
        informationView.configureNumberOfPeopleLabel(number: calculatorNumber())
    }
    
    //MARK: - 검색 버튼 액션
    @IBAction func pressedSearchButton(_ sender: Any){
        information.setNumberOfPeople(number: calculatorNumber())
        information.setAdult(adult: String(numberOfAdult))
        information.setChildren(children: String(numberOfChildren))
        information.setInfants(infants: String(numberOfInfants))
        guard let roomsViewController = self.storyboard?.instantiateViewController(identifier: "roomsViewController") as? RoomsViewController else { return }
        self.navigationController?.pushViewController(roomsViewController, animated: true)
    }
 
}

