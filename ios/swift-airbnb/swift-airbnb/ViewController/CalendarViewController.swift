
import UIKit
import FSCalendar

class CalendarViewController: UIViewController {

    @IBOutlet weak var calendarView: FSCalendar!
    @IBOutlet weak var informationView: InformationView!
    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var skipButton: UIButton!
    
    
    private let dateFormatter = DateFormatter()
    private let information = Information.shared
    
    var dayArray = [Date]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCalendar()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        navigationItem.title = "숙소 찾기"
        configureDelegateAndDataSource()
        setLocationLabel()
    }

    
    func configureCalendar() {
        calendarView.appearance.weekdayTextColor = UIColor.black
        calendarView.appearance.headerTitleColor = UIColor.black
        calendarView.appearance.selectionColor = UIColor(red: 38/255, green: 153/255, blue: 251/255, alpha: 1)
        calendarView.allowsMultipleSelection = true
        calendarView.swipeToChooseGesture.isEnabled = true
        calendarView.scrollEnabled = true
        calendarView.scrollDirection = .vertical
    }
    
    func configureDelegateAndDataSource() {
        calendarView.delegate = self
        calendarView.dataSource = self
    }
    
    //MARK: - InformationView Update Method
    func convertDateToString() -> [String] {
        let resultArr = dayArray.sorted()
        if resultArr.count >= 2 {
            return [dateFormatter.string(from: resultArr[0]), dateFormatter.string(from: resultArr[dayArray.count - 1])]
        } else if resultArr.count  == 1 {
            return [dateFormatter.string(from: resultArr[0]), ""]
        } else {
            return ["", ""]
        }
    }
    
    func deleteSelectedDate(date: Date) {
        if dayArray.count > 0 {
            dayArray.remove(at: dayArray.firstIndex(of: date) ?? 0)
        }
    }
    
    func setLocationLabel() {
        informationView.locationLabel.text = information.returnName()
        informationView.locationLabel.textColor = .systemGray2
    }
    
    @IBAction func ButtonAction(_ sender: Any){
        guard let chartViewController = self.storyboard?.instantiateViewController(identifier: "chartViewController") as? ChartViewController else { return }
        self.navigationController?.pushViewController(chartViewController, animated: true)
        // 네트워크 처리를 통한 가격 불러오기
    }
    
}

extension CalendarViewController: FSCalendarDelegate, FSCalendarDataSource, FSCalendarDelegateAppearance {
    
    func calendar(_ calendar: FSCalendar, didSelect date: Date, at monthPosition: FSCalendarMonthPosition) {
        dayArray.append(date)
        informationView.configureCheckLabel(days: convertDateToString())
        
    }
    
    func calendar(_ calendar: FSCalendar, didDeselect date: Date, at monthPosition: FSCalendarMonthPosition) {
        self.deleteSelectedDate(date: date)
        informationView.configureCheckLabel(days: convertDateToString())
    }
}
