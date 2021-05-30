
import UIKit
import FSCalendar

class CalendarViewController: UIViewController {

    @IBOutlet weak var calendarView: FSCalendar!
    @IBOutlet weak var informationView: InformationView!
    @IBOutlet weak var tabBarView: TabBarView!
    
    private let dateFormatter = DateFormatter()
    private let information = Information.shared
    
    private var dayArray = [Date]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCalendar()
        dateFormatter.dateFormat = "MM월 dd일"
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
    func convertDateToString(date: Date) -> [String] {
        dayArray.append(date)
        let resultArr = dayArray.sorted()
        return [dateFormatter.string(from: resultArr[0]), "-\(dateFormatter.string(from: resultArr[dayArray.count - 1]))"]
    }
    
    func deleteSelectedDate(date: Date) -> [String] {
        if dayArray.count >= 2 {
            dayArray.remove(at: dayArray.firstIndex(of: date) ?? 0)
            let resultArr = dayArray.sorted()
            return [dateFormatter.string(from: resultArr[0]), "-\(dateFormatter.string(from: resultArr[dayArray.count - 1]))"]
        } else if dayArray.count == 0 {
            dayArray.remove(at: dayArray.firstIndex(of: date) ?? 0)
            return ["",""]
        } else {
            let resultArr = dayArray.sorted()
            return [dateFormatter.string(from: resultArr[0]), ""]
        }
    }
    
    func setLocationLabel() {
        informationView.locationLabel.text = information.returnName()
        informationView.locationLabel.textColor = .systemGray2
    }
    
    
    //MARK: - Notification Method
//    @objc private func setPlaceName(_ notification: Notification) {
//        
//        guard let name = notification.userInfo?["placeName"] as? String else { return }
//        informationView.configureLocationLabel(name: name)
//    }

}

extension CalendarViewController: FSCalendarDelegate, FSCalendarDataSource, FSCalendarDelegateAppearance {
    
    func calendar(_ calendar: FSCalendar, didSelect date: Date, at monthPosition: FSCalendarMonthPosition) {
        informationView.configureCheckLabel(days: convertDateToString(date: date))
    }
    
    func calendar(_ calendar: FSCalendar, didDeselect date: Date, at monthPosition: FSCalendarMonthPosition) {
        informationView.configureCheckLabel(days: deleteSelectedDate(date: date))
    }
}
