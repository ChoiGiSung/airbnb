
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
    private var selectedDayArray = [Date]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCalendar()
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
        selectedDayArray.sort()
        if selectedDayArray.count >= 2 {
            informationView.configureDayHyphenLabel()
            return [formatter.string(from: selectedDayArray[0]), formatter.string(from: selectedDayArray[selectedDayArray.count - 1])]
        } else if selectedDayArray.count  == 1 {
            informationView.removeDayHyphenLabel()
            return [formatter.string(from: selectedDayArray[0]), ""]
        } else {
            informationView.removeDayHyphenLabel()
            return ["", ""]
        }
    }
    
    func deleteSelectedDate(date: Date) {
        if selectedDayArray.count > 0 {
            selectedDayArray.remove(at: selectedDayArray.firstIndex(of: date) ?? 0)
        }
    }
    
    func setLocationLabel() {
        informationView.locationLabel.text = information.getName()
    }
    
    @IBAction func ButtonAction(_ sender: Any){
        guard let chartViewController = self.storyboard?.instantiateViewController(identifier: "chartViewController") as? ChartViewController else { return }
        self.navigationController?.pushViewController(chartViewController, animated: true)
        information.setCheckInAndCheckOut(checkIn: formatter.string(from: selectedDayArray[0]), checkOut: formatter.string(from: selectedDayArray[selectedDayArray.count - 1]))
    }
}

extension CalendarViewController: FSCalendarDelegate, FSCalendarDataSource, FSCalendarDelegateAppearance {
    
    func calendar(_ calendar: FSCalendar, didSelect date: Date, at monthPosition: FSCalendarMonthPosition) {
        selectedDayArray.append(date)
        informationView.configureCheckLabel(days: convertDateToString())
    }
    
    func calendar(_ calendar: FSCalendar, didDeselect date: Date, at monthPosition: FSCalendarMonthPosition) {
        self.deleteSelectedDate(date: date)
        informationView.configureCheckLabel(days: convertDateToString())
    }
}
