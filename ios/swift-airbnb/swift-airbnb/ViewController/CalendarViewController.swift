
import UIKit
import FSCalendar

class CalendarViewController: UIViewController {

    @IBOutlet weak var calendarView: FSCalendar!
    @IBOutlet weak var informationView: InformationView!
    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var skipButton: UIButton!
    
    private let formatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        return formatter
    }()
    
    private let information = Information.shared
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCalendar()
        configureNavigationItem()
        configureDelegateAndDataSource()
        setLocationLabel()
        configureButton()
    }
    func configureNavigationItem()  {
        navigationItem.title = "숙소 찾기"
        self.navigationItem.backButtonTitle = "뒤로"
    }
    
    func configureButton()  {
        self.nextButton.setTitleColor(.systemGray2, for: .normal)
        self.nextButton.isEnabled = false
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
        if calendarView.selectedDates.count >= 2 {
            informationView.configureDayHyphenLabel()
            return [formatter.string(from: calendarView.selectedDates.min()!), formatter.string(from: calendarView.selectedDates.max()!)]
        } else if calendarView.selectedDates.count  == 1 {
            informationView.removeDayHyphenLabel()
            return [formatter.string(from: calendarView.selectedDates[0]), ""]
        } else {
            informationView.removeDayHyphenLabel()
            return ["", ""]
        }
    }
    
    func setLocationLabel() {
        informationView.locationLabel.text = information.getName()
    }
    
    @IBAction func pressedNextButton(_ sender: Any){
        guard let chartViewController = self.storyboard?.instantiateViewController(identifier: "chartViewController") as? ChartViewController else { return }
        self.navigationController?.pushViewController(chartViewController, animated: true)
        information.setCheckInAndCheckOut(checkIn: formatter.string(from:  calendarView.selectedDates[0]), checkOut: formatter.string(from: calendarView.selectedDates[calendarView.selectedDates.count - 1]))
    }
    
    func checkSelectedDayArrayCount() -> Bool {
        return self.calendarView.selectedDates.isEmpty
    }
    
    func changeNextButton(check: Bool)  {
        if check == true {
            self.nextButton.isEnabled = false
            self.skipButton.setTitle("건너띄기", for: .normal)
            self.nextButton.setTitleColor(.systemGray2, for: .normal)
        } else {
            self.nextButton.isEnabled = true
            self.skipButton.setTitle("지우기", for: .normal)
            self.nextButton.setTitleColor(.black, for: .normal)
        }
    }

}

extension CalendarViewController: FSCalendarDelegate, FSCalendarDataSource, FSCalendarDelegateAppearance {
    
    func calendar(_ calendar: FSCalendar, didSelect date: Date, at monthPosition: FSCalendarMonthPosition) {
        if calendar.selectedDates.count > 2{
            for _ in 0 ..< calendar.selectedDates.count - 1{
                calendar.deselect(calendar.selectedDates[0])
            }
        }
        
        var startTemp: Date!
        if calendar.selectedDates.count == 2{
            if calendar.selectedDates[0] < calendar.selectedDates[1]{
                startTemp = calendar.selectedDates[0]
                while startTemp < calendar.selectedDates[1] - 86400{
                    startTemp += 86400
                    calendar.select(startTemp)
                }
                startTemp = nil
            }
            else{
                startTemp = calendar.selectedDates[1]
                while startTemp < calendar.selectedDates[0] - 86400{
                    startTemp += 86400
                    calendar.select(startTemp)
                }
            }
        }
        changeNextButton(check: checkSelectedDayArrayCount())
        informationView.configureCheckLabel(days: convertDateToString())
    }
    
    func calendar(_ calendar: FSCalendar, didDeselect date: Date, at monthPosition: FSCalendarMonthPosition) {
        for _ in 0 ..< calendar.selectedDates.count {
            calendar.deselect(calendar.selectedDates[0])
        }
        calendar.select(date)
        changeNextButton(check: checkSelectedDayArrayCount())
        informationView.configureCheckLabel(days: convertDateToString())
    }
}
