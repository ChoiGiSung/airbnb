
import UIKit
import GooglePlaces

class SearchViewController: UIViewController {
    
    private var fetchCityManager = FetchCityManager.shared
    private lazy var travelCollectionViewDataSource = TravelCollectionViewDataSource(fetchCityManager: fetchCityManager)
    private var resultController = GMSAutocompleteResultsViewController()
    @IBOutlet weak var travelCollectionView: UICollectionView!
    private var information = Information.shared
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureNavigationItem()
        configureDataSource()
        resultController.delegate = self
        self.searchController.searchBar.delegate = self
    }
    
    private lazy var cancelButton: UIBarButtonItem = {
        let cancelButton = UIBarButtonItem(title: "지우기", style: .plain, target: self, action: #selector(clearSearchBar))
        return cancelButton
    }()
    
    private lazy var searchController: UISearchController = {
        let searchController = UISearchController(searchResultsController: resultController)
        searchController.searchBar.placeholder = "어디로 여행가세요?"
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.automaticallyShowsCancelButton = false
        searchController.searchBar.searchTextField.clearButtonMode = .never
        return searchController
    }()

    
    func configureNavigationItem()  {
        self.navigationController?.isNavigationBarHidden = false
        self.navigationItem.searchController = searchController
        self.navigationItem.searchController?.searchResultsUpdater = resultController
        self.navigationItem.title = "숙소 찾기"
        self.navigationItem.backButtonTitle = "뒤로"
        self.navigationItem.hidesSearchBarWhenScrolling = false
    }
    
    func configureDataSource() {
        travelCollectionView.dataSource = travelCollectionViewDataSource
        travelCollectionView.register(TravelCollectionViewCell.nib, forCellWithReuseIdentifier: TravelCollectionViewCell.identifier)
    }
    
    @objc func clearSearchBar()  {
        self.searchController.searchBar.text = ""
    }
}

extension SearchViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if searchBar.text == nil || searchBar.text == "" {
            self.navigationItem.rightBarButtonItem = .none
        } else {
            self.navigationItem.rightBarButtonItem = cancelButton
        }
    }
}

extension SearchViewController: GMSAutocompleteResultsViewControllerDelegate {
    func resultsController(_ resultsController: GMSAutocompleteResultsViewController, didAutocompleteWith place: GMSPlace) {
        dismiss(animated: true, completion: nil)
        guard let calendarViewController = self.storyboard?.instantiateViewController(identifier: "calendarViewController") as? CalendarViewController else { return }
        guard let name = place.name else { return }
        print(name)
        information.setName(name: name)
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5, execute: {
            self.navigationController?.pushViewController(calendarViewController, animated: true)
        })
    }
    
    func resultsController(_ resultsController: GMSAutocompleteResultsViewController, didFailAutocompleteWithError error: Error) {
        print(error.localizedDescription)
    }
}
